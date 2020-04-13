//报告录入页面
!function (global) {
    'use strict';
    //页面总布局
    var Layout = {
        obj: null,
        config: {
            pattern: "4A",
            offsets: {
                top: 5,
                right: 5,
                bottom: 5,
                left: 5
            },
            cells: [
                {
                    id: "a",
                    text: "textA",
                    header: false,
                    collapsed_text: "textA",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    height:110
                },
                {
                    id: "b",
                    text: "已接收样本列表",
                    header: true,
                    collapsed_text: "已接收样本列表",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    width:600
                },
                {
                    id: "c",
                    text: "样本详细信息",
                    header: true,      // 显示标题
                    collapsed_text: "样本详细信息",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    width:400
                },
                {
                    id: "d",
                    text: "检验结果录入",
                    header: true,      // 显示标题
                    collapsed_text: "检验结果录入",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true]
                }
            ]
        },
        initObj: function () {
            Layout.obj = SystemHome.Layout.obj.cells("a").attachLayout(Layout.config);
        }
    };

    var OperationForm = {
        obj: null,
        config: [
            {type: "settings", position: "label-left", offsetLeft: 0, offsetTop: 6},
            {
                type: "block", list: [
                    {type: "input", name: "barCodeNumber", label: "条&nbsp;码&nbsp;号&nbsp;：", width: 120},
                    {type: "newcolumn"},
                    {type: "calendar", name: "startDate", label: "接收时间：", inputWidth: 120,offsetLeft: 10},
                    {type: "newcolumn"},
                    {type: "calendar", name: "endDate", label: "~", inputWidth: 120 }
                ]
            },
            {
                type: "block", list: [
                    {type: "combo", name: "resultEntryStatu", label: "录入状态：", width: 120,
                        options:[
                            {text: "未录入", value: "未录入"},
                            {text: "已录入", value: "已录入"}
                        ]},
                    {type: "newcolumn"},
                    {type: "combo", name: "resultAuditStatu", label: "审核状态：", width: 120,offsetLeft: 10,
                        options:[
                            {text: "未审核", value: "未审核"},
                            {text: "已审核", value: "已审核"}
                        ]},
                    {type: "newcolumn"},
                    {type: "button", name: "searchBtn", value: "查询", offsetLeft: 20,offsetTop: 0}
                ]
            }
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
        //查询功能
        searchBtnEvent: function () {
            var barCodeNumber = OperationForm.obj.getItemValue("barCodeNumber");
            if(JSUtils.isNotEmpty(barCodeNumber) && JSUtils.isNotNum(barCodeNumber)){
                dhtmlxAlert.alertMsg("条码号请输入数字");
                return;
            }
            var resultEntryStatu = OperationForm.obj.getItemValue("resultEntryStatu");
            var resultAuditStatu = OperationForm.obj.getItemValue("resultAuditStatu");
            if (resultEntryStatu === "未录入" && resultAuditStatu === "已审核") {
                dhtmlxAlert.alertWarningMsg("未录入结果时不可审核，请重新选择条件");
                OperationForm.obj.getCombo("resultEntryStatu").setComboText("");
                OperationForm.obj.getCombo("resultAuditStatu").setComboText("");
                return;
            }
            ReceivedSampleGrid.loadData();
        }
    };


    //已接收样本列表
    var ReceivedSampleGrid = {
        obj: null,
        initObj: function () {
            ReceivedSampleGrid.obj = Layout.obj.cells("b").attachGrid();
            ReceivedSampleGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ReceivedSampleGrid.obj.setHeader("条码号,患者姓名,检验项目组合,录入状态,审核状态,检验申请id", null,
                ["text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            ReceivedSampleGrid.obj.setColumnIds("barcodeNumber,patientName,checkItemGroup,resultEntryStatu,resultAuditStatu,itemId");
            ReceivedSampleGrid.obj.setColAlign("center,center,center,center,center");   //设置列中数据居中
            ReceivedSampleGrid.obj.setInitWidths("100,100,*,100,100,0");          //列宽
            ReceivedSampleGrid.obj.setColTypes("ro,ro,ro,ro,ro,ro");
            ReceivedSampleGrid.obj.init();
            ReceivedSampleGrid.obj.setColumnHidden(5, true);

            //底部分页栏
            Layout.obj.cells("b").attachStatusBar({
                text: "<div id='exampaging'></div>",
                height: 30
            });
            ReceivedSampleGrid.obj.enablePaging(true,18,5,"exampaging",true);
            ReceivedSampleGrid.obj.i18n.paging = i18n_paging;
            ReceivedSampleGrid.obj.setPagingSkin("toolbar");

            ReceivedSampleGrid.loadData();
        },
        initEvent: function () {
            //点击某行时显示样本详细信息
            ReceivedSampleGrid.obj.attachEvent("onRowSelect", function (id, ind) {

            });
        },
        loadData: function () {
            var formData = OperationForm.obj.getFormData();
            formData.startDate = OperationForm.obj.getInput("startDate").value;
            formData.endDate = OperationForm.obj.getInput("endDate").value;
            ajaxUtils.postBody('sampleRecept/getReceptedSample.json',
                formData
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


    var reportEntry = function () {};
    reportEntry.init = init;
    global.reportEntry = reportEntry || {};
}(this);