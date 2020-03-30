// 条码打印页面
!function (global) {
    'use strict';
    //页面总布局
    var Layout = {
        obj: null,

        config: {
            pattern: "3T",
            offsets: {
                top: 0,
                right: 0,
                bottom: 0,
                left: 0
            },
            cells: [
                {
                    id: "a",
                    text: "查询条件",
                    header: false,      // 隐藏标题
                    collapsed_text: "查询条件",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    height:50
                },
                {
                    id: "b",
                    text: "患者列表",
                    header: true,      // 显示标题
                    collapsed_text: "患者列表",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    width: 350
                },
                {
                    id: "c",
                    text: "检验申请列表",
                    header: true,      // 显示标题
                    collapsed_text: "检验申请列表",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true]
                }
            ]
        },

        initObj: function () {
            Layout.obj = SystemHome.Layout.obj.cells("c").attachLayout(Layout.config);
        }
    };

    //操作栏，查询和打印
    var OperationForm = {
        obj:null,
        config: [
            {type: "input", name: "patientId", label: "就诊卡号：", width: 100, offsetLeft: 50, offsetTop: 12, maxLength: 20},
            {type: "newcolumn"},
            {type: "input", name: "patientName", label: "患者姓名：", width: 100, offsetLeft: 10, offsetTop: 12, maxLength: 20},
            {type: "newcolumn"},
            {type: "calendar", name: "startDate", label: "开单时间：", inputWidth:100, offsetLeft: 10, offsetTop: 12},
            {type: "newcolumn"},
            {type: "calendar", name: "endDate", label: "~", inputWidth: 100, offsetTop: 12},
            {type: "newcolumn"},
            {type: "button", name: "searchBtn", value: "查询", offsetLeft: 20},
            {type: "newcolumn"},
            {type: "button", name: "printBarCodeBtn", value: "打印条码", offsetLeft: 10}
        ],
        initObj: function () {
            OperationForm.obj = Layout.obj.cells("a").attachForm(OperationForm.config);
        },
        initEvent:function () {
            //关联按钮
            OperationForm.obj.attachEvent("onButtonClick", function (name) {
                switch (name) {
                    case "searchBtn":
                        OperationForm.searchBtnEvent();
                        break;
                    case "printBarCodeBtn":
                        OperationForm.printBarCodeBtnEvent();
                        break;
                    default:
                }
            });
            //按回车时搜索
            OperationForm.obj.attachEvent("onEnter",function () {
                OperationForm.searchBtnEvent();
            });
        },
        //查询按钮功能
        searchBtnEvent:function () {
            //查询患者
            PatientListGrid.loadData();
            //查找患者对应的检验申请
            CheckApplicationGrid.loadData();
        },
        //打印条码按钮功能
        printBarCodeBtnEvent:function () {
            var checkApplicationIdList = dhtmlxUtils.getCheckedRowIds(CheckApplicationGrid.obj,0);
            ajaxUtils.postBody('barCodePrint/getPrintBarCode.json',
                checkApplicationIdList
            ).then(function (barCodeData) {
                if (JSUtils.isEmpty(barCodeData)){
                    dhtmlxAlert.alertWarningMsg("请选择需要打印的行");
                    return;
                }
                printBarCode(barCodeData);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //患者列表
    var PatientListGrid = {
        obj:null,
        initObj: function () {
            PatientListGrid.obj = Layout.obj.cells("b").attachGrid();
            PatientListGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            PatientListGrid.obj.setHeader("选择,就诊卡号,患者姓名,性别,年龄", null, ["text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            PatientListGrid.obj.setColumnIds("ch,patientId,patientName,sex,age");
            PatientListGrid.obj.setColAlign("center,center,center,center,center");   //设置列中数据居中
            PatientListGrid.obj.setInitWidths("50,*,80,50,50");          //列宽
            PatientListGrid.obj.setColTypes("ch,ro,ro,ro,ro");
            PatientListGrid.obj.init();
            PatientListGrid.loadData();
        },
        initEvent: function () {
            PatientListGrid.obj.attachEvent("onRowSelect", function (id, ind) {
                //自动勾选上本行或取消勾选
                var flag = PatientListGrid.obj.cells(id, 0).getValue() == '1' ? 0 : 1;
                PatientListGrid.obj.cells(id, 0).setValue(flag);
                //查找患者对应的检验申请
                CheckApplicationGrid.loadData();
            });
            PatientListGrid.obj.attachEvent("onCheck", function (rId, cInd, state) {
                PatientListGrid.obj.selectRowById(rId, true, true, false);
                //查找患者对应的检验申请
                CheckApplicationGrid.loadData();
            });
        },
        loadData: function () {
            var oldCheckedIdList = dhtmlxUtils.getCheckedRowIds(PatientListGrid.obj,0);
            var formData = OperationForm.obj.getFormData();
            ajaxUtils.postBody('barCodePrint/getPatientInfo.json',
                formData
            ).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(PatientListGrid.obj, data, "patientId");  //删除所有行，加载数据
                PatientListGrid.obj.sortRows(1,"int","asc");

                if (JSUtils.isEmpty(oldCheckedIdList)) {
                    return;
                }
                var rowsNum = PatientListGrid.obj.getRowsNum();
                for (var i = 0; i < rowsNum; i++) {
                    for (var j = 0; j < oldCheckedIdList.length; j++) {
                        var rowId = PatientListGrid.obj.getRowId(i);
                        if (rowId == oldCheckedIdList[j]) {
                            PatientListGrid.obj.cells(rowId,0).setValue("1");
                        }
                    }
                }
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //检验申请列表
    var CheckApplicationGrid = {
        obj:null,
        gridData:null,
        initObj: function () {
            CheckApplicationGrid.obj = Layout.obj.cells("c").attachGrid();
            CheckApplicationGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            CheckApplicationGrid.obj.setHeader("<input id='allSelect' type='checkbox' value='0' style='padding-left: 15px' />,姓名,检验项目,样本类型,采集容器,送检科室,急诊,开单医生,开单时间,条码打印状态,检验申请id,病人id,检验项目组合id", null,  //
                ["text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            CheckApplicationGrid.obj.setColumnIds("ch,patientName,checkItemGroup,sampleType,collectionContainer,submitDepartment,isEmergency,billingDoctor,billingTime,printStatu,itemId,patientId,checkItemGroupId");
            CheckApplicationGrid.obj.setColAlign("center,center,center,center,center,center,center,center,center,center");   //设置列中数据居中
            CheckApplicationGrid.obj.setInitWidths("50,100,*,100,100,100,100,80,100,120,0,0,0");          //列宽
            CheckApplicationGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro,ch,ro,ro,ro,ro,ro,ro");
            CheckApplicationGrid.obj.init();
            CheckApplicationGrid.obj.setColumnHidden(10, true);
            CheckApplicationGrid.obj.setColumnHidden(11,true);
            CheckApplicationGrid.obj.setColumnHidden(12, true);
            CheckApplicationGrid.obj.enableColumnAutoSize(true);

            Layout.obj.cells("c").attachStatusBar({
                text: '<div style="height:20px; line-height:20px;margin: 7px 0 5px 25px;"><span id="Pagination" ></span></div>',
                height: 40
            });

        },
        initEvent: function () {
            //全选按钮
            $("#allSelect").click(function () {
                if ($("#allSelect").val()==="0") {
                    CheckApplicationGrid.obj.forEachRow(function(id){
                        CheckApplicationGrid.obj.cells(id, 0).setValue("1");
                    });
                    $("#allSelect").val("1");
                }else if ($("#allSelect").val()==="1"){
                    CheckApplicationGrid.obj.forEachRow(function(id){
                        CheckApplicationGrid.obj.cells(id, 0).setValue("0");
                    });
                    $("#allSelect").val("0");
                }
            });
            //点击某行时自动勾选上本行或取消勾选
            CheckApplicationGrid.obj.attachEvent("onRowSelect", function (id, ind) {
                var flag = CheckApplicationGrid.obj.cells(id, 0).getValue() == '1' ? 0 : 1;
                CheckApplicationGrid.obj.cells(id, 0).setValue(flag);
            });
        },
        loadData: function () {
            var patientIdList = dhtmlxUtils.getCheckedRowIds(PatientListGrid.obj,0);
            //如果勾选患者行为空，则清空申请列表并且不查询
            if (JSUtils.isEmpty(patientIdList)) {
                CheckApplicationGrid.obj.clearAll();
                return;
            }
            var checkApplicationSearch = {
                patientIdList : patientIdList,
                startDate : OperationForm.obj.getItemValue("startDate",true),
                endDate : OperationForm.obj.getItemValue("endDate",true)
            };
            /*ajaxUtils.postBody('barCodePrint/getCheckApplication.json',
                checkApplicationSearch
            ).then(function (data) {
                CheckApplicationGrid.gridData = data;
                $("#Pagination").pagination(data.length, {
                    num_edge_entries: $('#pageIndex').val(), //边缘页数
                    num_display_entries: 10, //主体页数
                    callback: CheckApplicationGrid.pageselectCallback,
                    items_per_page: 20, //每页显示个数
                    prev_text: "前一页",
                    next_text: "后一页"
                });
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });*/
        },
        pageselectCallback:function () {
            var data = CheckApplicationGrid.gridData;
            for (var i = 0 ; i < data.length ;i++){
                data[i].billingTime = new Date(data[i].billingTime).toLocaleDateString();
            }
            dhtmlxUtils.clearAndLoadJsonListData(CheckApplicationGrid.obj, data, "itemId");  //删除所有行，加载数据
            //CheckApplicationGrid.obj.sortRows(1,"int","asc");
            //将急诊栏禁用
            CheckApplicationGrid.obj.forEachRow(function (id) {
                CheckApplicationGrid.obj.cells(id, 6).setDisabled(true);
            });
        }
    };

    var init = function () {
        Layout.initObj();
        OperationForm.initObj();
        OperationForm.initEvent();
        PatientListGrid.initObj();
        PatientListGrid.initEvent();
        CheckApplicationGrid.initObj();
        CheckApplicationGrid.initEvent();
    };

    var barCodePrint = function(){};
    barCodePrint.init = init;
    global.barCodePrint = barCodePrint||{};
}(this);