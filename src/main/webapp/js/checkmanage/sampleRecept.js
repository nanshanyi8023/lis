// 样本接收页面
!function (global) {
    'use strict';
    //页面总布局
    var Layout = {
        obj: null,

        config: {
            pattern: "2E",
            offsets: {
                top: 2,
                right: 2,
                bottom: 2,
                left: 2
            },
            cells: [
                {
                    id: "a",
                    text: "查询条件",
                    header: false,      // 隐藏标题
                    collapsed_text: "查询条件",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    height: 50
                },
                {
                    id: "b",
                    text: "已接收样本列表",
                    header: true,      // 显示标题
                    collapsed_text: "已接收样本列表",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true]
                }
            ]
        },

        initObj: function () {
            Layout.obj = SystemHome.Layout.obj.cells("c").attachLayout(Layout.config);
        }
    };

    //操作栏,查询和打印
    var OperationForm = {
        obj: null,
        config: [
            {
                type: "input",
                name: "patientId",
                label: "就诊卡号：",
                width: 100,
                offsetLeft: 50,
                offsetTop: 12,
                maxLength: 20
            },
            {type: "newcolumn"},
            {
                type: "input",
                name: "patientName",
                label: "患者姓名：",
                width: 100,
                offsetLeft: 10,
                offsetTop: 12,
                maxLength: 20
            },
            {type: "newcolumn"},
            {
                type: "input",
                name: "sampleCodeNumber",
                label: "样本条码号：",
                width: 100,
                offsetLeft: 10,
                offsetTop: 12,
                maxLength: 20
            },
            {type: "newcolumn"},
            {type: "calendar", name: "startDate", label: "样本接收时间：", inputWidth: 100, offsetLeft: 10, offsetTop: 12},
            {type: "newcolumn"},
            {type: "calendar", name: "endDate", label: "~", inputWidth: 100, offsetTop: 12},
            {type: "newcolumn"},
            {type: "button", name: "searchBtn", value: "查询", offsetLeft: 20},
            {type: "newcolumn"},
            {type: "button", name: "returnSampleBtn", value: "退回样本", offsetLeft: 10}
        ],
        initObj: function () {
            OperationForm.obj = Layout.obj.cells("a").attachForm(OperationForm.config);
        },
        initEvent: function () {
            //关联按钮
            OperationForm.obj.attachEvent("onButtonClick", function (name) {
                switch (name) {
                    case "searchBtn":
                        OperationForm.searchBtnEvent();
                        break;
                    case "returnSampleBtn":
                        OperationForm.returnSampleBtnEvent();
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
            ReceivedSampleGrid.loadData();
        },
        //退回样本按钮功能
        returnSampleBtnEvent: function () {

        }
    };

    //已接收样本列表
    var ReceivedSampleGrid = {
        obj: null,
        /**
         * 表格还没写好！！！
         */
        initObj: function () {
            ReceivedSampleGrid.obj = Layout.obj.cells("b").attachGrid();
            ReceivedSampleGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ReceivedSampleGrid.obj.setHeader("<input id='allSelect' type='checkbox' value='0' /><label for='allSelect'>全选</label>,条码号,检验项目,样本状态,样本类型,采集容器,急诊,样本接收时间,开单医生,开单时间,检验申请id", null,
                ["text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            ReceivedSampleGrid.obj.setColumnIds("ch,barcodeNumber,checkItemGroupName,sampleReceptionStatu,isEmergency,billingDoctor,billingTime,printStatu,itemId,patientId,checkItemGroupId");
            ReceivedSampleGrid.obj.setColAlign("center,center,center,center,center,center,center,center,center,center");   //设置列中数据居中
            ReceivedSampleGrid.obj.setInitWidths("80,100,*,180,100,100,80,120,120,120,0,0,0");          //列宽
            ReceivedSampleGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro,ch,ro,ro,ro,ro,ro,ro");
            ReceivedSampleGrid.obj.init();
            ReceivedSampleGrid.obj.setColumnHidden(10, true);
            ReceivedSampleGrid.obj.setColumnHidden(11, true);
            ReceivedSampleGrid.obj.setColumnHidden(12, true);
            //ReceivedSampleGrid.obj.enableAutoWidth(true);
        },
        initEvent: function () {
            //全选按钮
            $("#allSelect").click(function () {
                if ($("#allSelect").val() === "0") {
                    ReceivedSampleGrid.obj.forEachRow(function (id) {
                        ReceivedSampleGrid.obj.cells(id, 0).setValue("1");
                    });
                    $("#allSelect").val("1");
                } else if ($("#allSelect").val() === "1") {
                    ReceivedSampleGrid.obj.forEachRow(function (id) {
                        ReceivedSampleGrid.obj.cells(id, 0).setValue("0");
                    });
                    $("#allSelect").val("0");
                }
            });
            //点击某行时自动勾选上本行或取消勾选
            ReceivedSampleGrid.obj.attachEvent("onRowSelect", function (id, ind) {
                var flag = ReceivedSampleGrid.obj.cells(id, 0).getValue() == '1' ? 0 : 1;
                ReceivedSampleGrid.obj.cells(id, 0).setValue(flag);
            });
        },
        loadData: function () {

            ajaxUtils.postBody('barCodePrint/getCheckApplication.json',
            ).then(function (data) {

                dhtmlxUtils.clearAndLoadJsonListData(ReceivedSampleGrid.obj, data, "itemId");  //删除所有行,加载数据
                //ReceivedSampleGrid.obj.sortRows(1,"int","asc");
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
        ReceivedSampleGrid.initObj();
        ReceivedSampleGrid.initEvent();
    };

    var sampleRecept = function () {
    };
    sampleRecept.init = init;
    global.sampleRecept = sampleRecept || {};
}(this);