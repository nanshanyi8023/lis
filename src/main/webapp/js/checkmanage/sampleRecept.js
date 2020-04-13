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
                    height: 80
                },
                {
                    id: "b",
                    text: "已接收样本",
                    header: true,      // 显示标题
                    collapsed_text: "已接收样本",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true]
                }
            ]
        },

        initObj: function () {
            Layout.obj = SystemHome.Layout.obj.cells("a").attachLayout(Layout.config);
        }
    };

    //顶部接收样本
    var ReceiveToolbar = {
        obj: null,
        config: {
            align: "left",
            icon_path: "images/samplerecept/",
            items: [
                {id: "text", type: "text", text: "<span style=\"font-weight: bold;font-size: 12px\">条码号:</span>"},
                {id: "barCodeNumber", type: "buttonInput", text: ""},
                {id: "sep1", type: "separator"},
                {
                    id: "receiveButton",
                    type: "button",
                    text: "<span style=\"font-weight: bold;font-size: 12px\">接 收</span>",
                    img: "receiveImg.png"
                }
            ]
        },
        initobj: function () {
            ReceiveToolbar.obj = Layout.obj.cells("a").attachToolbar(ReceiveToolbar.config);
        },
        initEvent: function () {
            //按回车时接收样本
            ReceiveToolbar.obj.attachEvent("onEnter", function () {
                ReceiveToolbar.receiveButtonEvent();
            });
            ReceiveToolbar.obj.attachEvent("onClick", function (name) {
                switch (name) {
                    case "receiveButton":
                        ReceiveToolbar.receiveButtonEvent();
                        break;
                    default:
                }
            });
        },
        //接收样本
        receiveButtonEvent: function () {
            var barCodeNumber = ReceiveToolbar.obj.getValue("barCodeNumber");
            if (JSUtils.isEmpty(barCodeNumber)) {
                dhtmlxAlert.alertMsg("请扫码或输入条码");
                return;
            }
            if(JSUtils.isNotNum(barCodeNumber)){
                dhtmlxAlert.alertMsg("请输入数字");
                return;
            }
            ajaxUtils.get('sampleRecept/receiveSample.json', {
                barCodeNumber:barCodeNumber
            }).then(function (data) {
                ReceiveToolbar.obj.setValue('barCodeNumber', '', false);
                ReceivedSampleGrid.loadData();
                dhtmlxAlert.alertMsg(data);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
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
            {type: "calendar", name: "startDate", label: "接收时间：", inputWidth: 100, offsetLeft: 10, offsetTop: 12},
            {type: "newcolumn"},
            {type: "calendar", name: "endDate", label: "~", inputWidth: 100, offsetTop: 12},
            {type: "newcolumn"},
            {type: "button", name: "searchBtn", value: "查询", offsetLeft: 20},
            {type: "newcolumn"},
            {type: "button", name: "returnSampleBtn", value: "退回样本", offsetLeft: 10}
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
                    case "returnSampleBtn":
                        var sampleIdList = dhtmlxUtils.getCheckedRowIds(ReceivedSampleGrid.obj,0);
                        //如果勾选样本行为空，则提示且不执行退回操作
                        if (JSUtils.isEmpty(sampleIdList)) {
                            dhtmlxAlert.alertMsg("请勾选需要退回的样本");
                            return;
                        }
                        returnSampleWindow.createObj();
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
            ReceivedSampleGrid.loadData();
        }
    };
    
    //已接收样本列表
    var ReceivedSampleGrid = {
        obj: null,
        initObj: function () {
            ReceivedSampleGrid.obj = Layout.obj.cells("b").attachGrid();
            ReceivedSampleGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ReceivedSampleGrid.obj.setHeader("<input id='allSelect' type='checkbox' value='0' /><label for='allSelect'>全选</label>," +
                "就诊卡号,患者姓名,条码号,检验项目,样本状态,样本类型,采集容器,急诊,样本接收时间,开单医生,开单时间,检验申请id", null,
                ["text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            ReceivedSampleGrid.obj.setColumnIds("ch,patientId,patientName,barcodeNumber,checkItemGroup,sampleReceptionStatu,sampleType,collectionContainer,isEmergency,sampleReceptionTime,billingDoctor,billingTime,itemId");
            ReceivedSampleGrid.obj.setColAlign("center,center,center,center,center,center,center,center,center,center,center,center");   //设置列中数据居中
            ReceivedSampleGrid.obj.setInitWidths("80,100,100,150,*,100,100,150,80,150,100,100,0");          //列宽
            ReceivedSampleGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro,ro,ro,ch,ro,ro,ro,ro");
            ReceivedSampleGrid.obj.init();
            ReceivedSampleGrid.obj.setColumnHidden(12, true);

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
            var formData = OperationForm.obj.getFormData();
            formData.startDate = OperationForm.obj.getInput("startDate").value;
            formData.endDate = OperationForm.obj.getInput("endDate").value;
            ajaxUtils.postBody('sampleRecept/getReceptedSample.json',
                formData
            ).then(function (data) {
                for (var i = 0 ; i < data.length ;i++){
                    if (JSUtils.isNotEmpty(data[i].sampleReceptionTime)) {
                        data[i].sampleReceptionTime = new Date(data[i].sampleReceptionTime).toLocaleDateString();
                    }
                    if (JSUtils.isNotEmpty(data[i].billingTime)) {
                        data[i].billingTime = new Date(data[i].billingTime).toLocaleDateString();
                    }
                }
                dhtmlxUtils.clearAndLoadJsonListData(ReceivedSampleGrid.obj, data, "itemId");  //删除所有行,加载数据
                //ReceivedSampleGrid.obj.sortRows(1,"int","asc");
                //将急诊栏禁用
                ReceivedSampleGrid.obj.forEachRow(function (id) {
                    ReceivedSampleGrid.obj.cells(id, 6).setDisabled(true);
                });
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //样本退回原因窗口
    var returnSampleWindow = {
        obj: null,
        createObj: function () {
            var windowFactory = new dhtmlXWindows();
            returnSampleWindow.obj = windowFactory.createWindow("returnSampleWindow", 0, 0, 350, 250);   //(id, left, top, width, height)
            returnSampleWindow.obj.setText("样本退回原因");  //标题
            returnSampleWindow.obj.denyResize();  //拒绝调整大小
            returnSampleWindow.obj.denyPark();
            returnSampleWindow.obj.setModal(true);
            returnSampleWindow.obj.centerOnScreen(); //窗口居中显示在屏幕中
            returnSampleWindow.initWindow();
        },
        initWindow: function () {
            returnSampleWindow.Layout.initObj();
            returnSampleWindow.Form.initObj();
            returnSampleWindow.Form.initEvent();
        }
    };

    //样本退回原因窗口布局设置
    returnSampleWindow.Layout = {
        obj: null,
        config: {
            pattern: "1C",
            offsets: {
                top: 5,
                right: 5,
                bottom: 5,
                left: 5
            },
            cells: [
                {
                    id: "a",
                    text: "Text a",
                    collapsed_text: "Text a",
                    header: false,
                    collapse: false,
                    fix_size: [true, true]
                }
            ]
        },
        initObj: function () {
            returnSampleWindow.Layout.obj = returnSampleWindow.obj.attachLayout(returnSampleWindow.Layout.config);
        }
    };

    //样本退回原因窗口表单
    returnSampleWindow.Form = {
        obj:null,
        config:[
            {type: "settings", position: "label-top", blockOffset: 0, offsetLeft: 70, offsetTop: 12},
            {type: "label", label: "样本退回原因"},
            {
                type: "combo", id: "sampleReturnReason", name: "sampleReturnReason", inputWidth: 180,
                options: [
                    {text: "样本污染", value: "样本污染"},
                    {text: "样本类型不合格", value: "样本类型不合格"},
                    {text: "样本采集量不合格", value: "样本采集量不合格"},
                    {text: "样本容器不合格", value: "样本容器不合格"},
                    {text: "医嘱取消", value: "医嘱取消"}
                ]
            },
            {
                type: "block", list: [
                    {type: "button", name: "confirmBtn", value: "确认", offsetLeft: 10, offsetTop: 30},
                    {type: "newcolumn"},
                    {type: "button", name: "cancelBtn", value: "取消", offsetLeft: 10, offsetTop: 30}
                ]
            }
        ],
        initObj: function () {
            returnSampleWindow.Form.obj = returnSampleWindow.Layout.obj.cells("a").attachForm(returnSampleWindow.Form.config);
        },
        initEvent: function () {
            returnSampleWindow.Form.obj.attachEvent("onButtonClick", function (name) {
                switch (name) {
                    case "confirmBtn":
                        returnSampleWindow.Form.confirmBtnEvent();
                        break;
                    case "cancelBtn":
                        returnSampleWindow.Form.cancelBtnEvent();
                        break;
                    default:
                }
            });
        },
        confirmBtnEvent:function () {
            var sampleIdList = dhtmlxUtils.getCheckedRowIds(ReceivedSampleGrid.obj,0);
            var returnReason = returnSampleWindow.Form.obj.getItemValue("sampleReturnReason");
            var returnDate = {
                sampleIdList: sampleIdList,
                returnReason: returnReason
            };
            ajaxUtils.postBody('sampleRecept/returnSample.json',
                returnDate
            ).then(function (data) {
                returnSampleWindow.obj.close();
                ReceivedSampleGrid.loadData();
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        cancelBtnEvent:function () {
            returnSampleWindow.obj.close();
        }
    };

    var init = function () {
        Layout.initObj();
        ReceiveToolbar.initobj();
        ReceiveToolbar.initEvent();
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