// 样本接收页面
!function (global) {
    'use strict';
    //页面总布局
    var Layout = {
        obj: null,
        config: {
            pattern: "2E",
            offsets: {
                top: 5,
                right: 5,
                bottom: 5,
                left: 5
            },
            cells: [
                {
                    id: "a",
                    text: "操作栏",
                    header: false,      // 隐藏标题
                    collapsed_text: "操作栏",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    height: 50
                },
                {
                    id: "b",
                    text: "已退回样本",
                    header: true,      // 显示标题
                    collapsed_text: "已退回样本",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true]
                }
            ]
        },

        initObj: function () {
            Layout.obj = SystemHome.Layout.obj.cells("a").attachLayout(Layout.config);
        }
    };

    //操作栏,查询和退回样本
    var OperationForm = {
        obj: null,
        config: [
            {type: "newcolumn"},
            {
                type: "input",
                name: "barCodeNumber",
                label: "条码号：",
                width: 100,
                offsetLeft: 10,
                offsetTop: 12,
                maxLength: 20
            },
            {type: "newcolumn"},
            {type: "calendar", name: "startDate", label: "退回时间：", inputWidth: 100, offsetLeft: 10, offsetTop: 12},
            {type: "newcolumn"},
            {type: "calendar", name: "endDate", label: "~", inputWidth: 100, offsetTop: 12},
            {type: "newcolumn"},
            {type: "button", name: "searchBtn", value: "查询", offsetLeft: 20}
        ],
        initObj: function () {
            OperationForm.obj = Layout.obj.cells("a").attachForm(OperationForm.config);
            OperationForm.obj.setItemValue('endDate',JSUtils.getToday());
        },
        initEvent: function () {
            //关联按钮
            OperationForm.obj.attachEvent("onButtonClick", function (name) {
                switch (name) {
                    case "searchBtn":
                        OperationForm.searchBtnEvent();
                        break;
                    default:
                }
            });
            //按回车时搜索
            OperationForm.obj.attachEvent("onEnter", function () {
                OperationForm.searchBtnEvent();
            });
        },
        //查询按钮功能
        searchBtnEvent: function () {
            var barCodeNumber = OperationForm.obj.getItemValue("barCodeNumber");
            if(JSUtils.isNotEmpty(barCodeNumber) && JSUtils.isNotNum(barCodeNumber)){
                dhtmlxAlert.alertMsg("条码号请输入数字");
                return;
            }
            ReturnedSampleGrid.loadData();
        }
    };
    
    //已接收样本列表
    var ReturnedSampleGrid = {
        obj: null,
        initObj: function () {
            ReturnedSampleGrid.obj = Layout.obj.cells("b").attachGrid();
            ReturnedSampleGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ReturnedSampleGrid.obj.setHeader("<input id='allSelect' type='checkbox' value='0' /><label for='allSelect'>全选</label>," +
                "就诊卡号,患者姓名,条码号,检验项目组合,样本状态,退回原因,退回时间,退回医生,接收时间,开单医生,开单时间,检验申请id", null,
                ["text-align:center;", "text-align:center;","text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            ReturnedSampleGrid.obj.setColumnIds("ch,patientId,patientName,barcodeNumber,checkItemGroup,sampleReceptionStatu,sampleReturnReason,sampleReturnTime,sampleReturnDoctor,sampleReceptionTime,billingDoctor,billingTime,itemId");
            ReturnedSampleGrid.obj.setColAlign("center,center,center,center,center,center,center,center,center,center,center,center");   //设置列中数据居中
            ReturnedSampleGrid.obj.setInitWidths("80,100,100,150,*,150,150,100,100,100,100,100,0");          //列宽
            ReturnedSampleGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
            ReturnedSampleGrid.obj.init();
            ReturnedSampleGrid.obj.setColumnHidden(12, true);

            //底部分页栏
            Layout.obj.cells("b").attachStatusBar({
                text: "<div id='exampaging'></div>",
                height: 30
            });
            ReturnedSampleGrid.obj.enablePaging(true,18,5,"exampaging",true);
            ReturnedSampleGrid.obj.i18n.paging = i18n_paging;
            ReturnedSampleGrid.obj.setPagingSkin("toolbar");

            ReturnedSampleGrid.loadData();
        },
        initEvent: function () {
            //全选按钮
            $("#allSelect").click(function () {
                if ($("#allSelect").val() === "0") {
                    ReturnedSampleGrid.obj.forEachRow(function (id) {
                        ReturnedSampleGrid.obj.cells(id, 0).setValue("1");
                    });
                    $("#allSelect").val("1");
                } else if ($("#allSelect").val() === "1") {
                    ReturnedSampleGrid.obj.forEachRow(function (id) {
                        ReturnedSampleGrid.obj.cells(id, 0).setValue("0");
                    });
                    $("#allSelect").val("0");
                }
            });
            //点击某行时自动勾选上本行或取消勾选
            ReturnedSampleGrid.obj.attachEvent("onRowSelect", function (id, ind) {
                var flag = ReturnedSampleGrid.obj.cells(id, 0).getValue() == '1' ? 0 : 1;
                ReturnedSampleGrid.obj.cells(id, 0).setValue(flag);
            });
        },
        loadData: function () {
            var formData = OperationForm.obj.getFormData();
            formData.startDate = OperationForm.obj.getInput("startDate").value;
            formData.endDate = OperationForm.obj.getInput("endDate").value;
            ajaxUtils.postBody('sampleReturn/getReturnedSample.json',
                formData
            ).then(function (data) {
                for (var i = 0 ; i < data.length ;i++){
                    if (JSUtils.isNotEmpty(data[i].sampleReturnTime)) {
                        data[i].sampleReturnTime = new Date(data[i].sampleReturnTime).toLocaleDateString();
                    }
                    if (JSUtils.isNotEmpty(data[i].sampleReceptionTime)) {
                        data[i].sampleReceptionTime = new Date(data[i].sampleReceptionTime).toLocaleDateString();
                    }
                    if (JSUtils.isNotEmpty(data[i].billingTime)) {
                        data[i].billingTime = new Date(data[i].billingTime).toLocaleDateString();
                    }
                }
                dhtmlxUtils.clearAndLoadJsonListData(ReturnedSampleGrid.obj, data, "itemId");  //删除所有行,加载数据
                //ReturnedSampleGrid.obj.sortRows(1,"int","asc");
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
        ReturnedSampleGrid.initObj();
        ReturnedSampleGrid.initEvent();
    };

    var sampleReturn = function () {
    };
    sampleReturn.init = init;
    global.sampleReturn = sampleReturn || {};
}(this);