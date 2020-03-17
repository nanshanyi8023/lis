// 条码打印页面
!function (global) {
    'use strict';
    //页面总布局
    var Layout = {
        obj: null,

        config: {
            parent: "RightLayoutObj",
            pattern: "3T",
            offsets: {
                top: 1,
                right: 1,
                bottom: 1,
                left: 1
            },
            cells: [
                {
                    id: "a",
                    text: "查询条件",
                    header: false,      // 显示标题
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
                    width:300
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
            {type: "input", name: "patientId", label: "患者id：", width: 100, offsetLeft: 50, offsetTop: 12, maxLength: 20},
            {type: "newcolumn"},
            {type: "input", name: "patientName", label: "患者姓名：", width: 100, offsetLeft: 10, offsetTop: 12, maxLength: 20},
            {type: "newcolumn"},
            {type: "calendar", name: "startDate", label: "开单时间：", inputWidth:100, offsetLeft: 10, offsetTop: 12},
            {type: "newcolumn"},
            {type: "calendar", name: "endDate", label: "~", inputWidth:100, offsetTop: 12},  //, enableTime: true, enableTodayButton: true, calendarPosition: "right"
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
            var formData = OperationForm.obj.getFormData();
            PatientListGrid.loadData(formData);


        },
        //打印条码按钮功能
        printBarCodeBtnEvent:function () {

        }
    };

    //患者列表
    var PatientListGrid = {
        obj:null,
        initObj: function () {
            PatientListGrid.obj = Layout.obj.cells("b").attachGrid();
            PatientListGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            PatientListGrid.obj.setHeader("选择,患者id,患者姓名",null, ["text-align:center;","text-align:center;","text-align:center;"]);  //设置标题内容居中
            PatientListGrid.obj.setColumnIds("ch,patientId,patientName");
            PatientListGrid.obj.setColAlign("center,center,center");   //设置列中数据居中
            PatientListGrid.obj.setInitWidths("60,*,130");          //列宽
            PatientListGrid.obj.setColTypes("ch,ro,ro");
            PatientListGrid.obj.init();
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
        loadData: function (formData) {
            ajaxUtils.postBody('barCodePrint/getPatientInfo.json',
                formData
            ).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(PatientListGrid.obj, data, "patientId");  //删除所有行，加载数据
                PatientListGrid.obj.sortRows(1,"int","asc");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //检验申请列表
    var CheckApplicationGrid = {
        obj:null,
        initObj: function () {
            CheckApplicationGrid.obj = Layout.obj.cells("c").attachGrid();
            CheckApplicationGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            CheckApplicationGrid.obj.setHeader("选择,姓名,检验项目,采集容器,价格,送检科室,急诊,开单医生,开单时间,条码打印状态", null,
                ["text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            CheckApplicationGrid.obj.setColumnIds("ch,patientName,checkItemGroupName,collectionContainer,itemPrice,submitDepartment,isEmergency,billingDoctor,billingTime,printStatu");
            CheckApplicationGrid.obj.setColAlign("center,center,center,center,center,center,center,center,center,center");   //设置列中数据居中
            //CheckApplicationGrid.obj.setInitWidths("60,100,*,100,100,100,100,100,100,100");          //列宽
            CheckApplicationGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro,ch,ro,ro,ro");
            CheckApplicationGrid.obj.init();
            CheckApplicationGrid.obj.enableAutoWidth(true);

        },
        initEvent: function () {
            CheckApplicationGrid.obj.attachEvent("onRowSelect",function () {


            });
        },
        loadData: function () {
            var checkApplicationSearch = {
                patientIdList : dhtmlxUtils.getCheckedRowIds(PatientListGrid.obj, 0),
                startDate : OperationForm.obj.getItemValue("startDate"),
                endDate : OperationForm.obj.getItemValue("endDate")
            };
            ajaxUtils.postBody('barCodePrint/getCheckApplication.json',
                checkApplicationSearch
            ).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(CheckApplicationGrid.obj, data, "");  //删除所有行，加载数据
                //CheckApplicationGrid.obj.sortRows(1,"int","asc");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
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