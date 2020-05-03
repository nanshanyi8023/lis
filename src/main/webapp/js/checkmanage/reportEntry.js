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
                    width:450
                },
                {
                    id: "c",
                    text: "样本详细信息",
                    header: true,      // 显示标题
                    collapsed_text: "样本详细信息",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    width:300
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

    //查询已接收样本操作栏
    var OperationForm = {
        obj: null,
        config: [
            {type: "settings", position: "label-left", offsetLeft: 0, offsetTop: 2},
            {
                type: "block", list: [
                    {type: "calendar", name: "startDate", label: "接收时间：", inputWidth: 120},
                    {type: "newcolumn"},
                    {type: "calendar", name: "endDate", label: "&nbsp;&nbsp;~&nbsp;&nbsp;", inputWidth: 120 }
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
                        ]
                    }
                ]
            },
            {
                type:"block", list: [
                    {type: "input", name: "barCodeNumber", label: "条&nbsp;码&nbsp;号&nbsp;：", width: 120},
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
            //清空样本详细信息表单和检验结果录入表格
            SampleDetailedInfoForm.obj.clear();
            SampleDetailedInfoForm.obj.setItemValue("isEmergency",-1);
            CheckResultEntryGrid.obj.clearAll();
            //加载数据
            ReceivedSampleGrid.loadData();
        }
    };

    //已接收样本列表
    var ReceivedSampleGrid = {
        obj: null,
        initObj: function () {
            ReceivedSampleGrid.obj = Layout.obj.cells("b").attachGrid();
            ReceivedSampleGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ReceivedSampleGrid.obj.setHeader("条码号,患者姓名,检验项目组合,检验申请id", null,
                ["text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            ReceivedSampleGrid.obj.setColumnIds("barcodeNumber,patientName,checkItemGroup,itemId");
            ReceivedSampleGrid.obj.setColAlign("center,center,center");   //设置列中数据居中
            ReceivedSampleGrid.obj.setInitWidths("100,100,*,0");          //列宽
            ReceivedSampleGrid.obj.setColTypes("ro,ro,ro,ro");
            ReceivedSampleGrid.obj.init();
            ReceivedSampleGrid.obj.setColumnHidden(3, true);

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
            //点击某行时显示样本详细信息和默认检验结果
            ReceivedSampleGrid.obj.attachEvent("onRowSelect", function(id,ind) {
                SampleDetailedInfoForm.setForm(id);
                CheckResultEntryGrid.loadData(id);
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

    //样本详细信息表单
    var SampleDetailedInfoForm = {
        obj: null,
        config: [
            {type: "settings", position: "label-left", offsetLeft: 0, offsetTop: 4},
            {
                type: "block", list: [

                    {type: "label", label: '是&nbsp;&nbsp;否&nbsp;&nbsp;急&nbsp;&nbsp;诊:'},
                    {type: "newcolumn"},
                    {
                        type: "radio",
                        name: "isEmergency",
                        value: "1",
                        label: "急诊",
                        position: "label-right",
                        offsetTop: 6,
                        readonly:true
                    },
                    {type: "newcolumn"},
                    {
                        type: "radio",
                        name: "isEmergency",
                        value: "0",
                        label: "非急诊",
                        position: "label-right",
                        readonly:true,
                        offsetTop: 6,
                        offsetLeft: 20
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "equipmentList", label: "检&nbsp;&nbsp;验&nbsp;&nbsp;仪&nbsp;&nbsp;器:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "checkItemGroupNameList", label: "检验项目组合:", width: 150, rows:2, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "barcodeNumber", label: "条&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "patientName", label: "患&nbsp;&nbsp;者&nbsp;&nbsp;姓&nbsp;&nbsp;名:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "patientId", label: "就&nbsp;&nbsp;诊&nbsp;&nbsp;卡&nbsp;&nbsp;号:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "sex", label: "性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "age", label: "年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "submitDepartment", label: "送&nbsp;&nbsp;检&nbsp;&nbsp;科&nbsp;&nbsp;室:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "sampleType", label: "样&nbsp;&nbsp;本&nbsp;&nbsp;类&nbsp;&nbsp;型:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "sampleReceptionTime", label: "样本接收时间:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "resultEntryStatu", label: "结果录入状态:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "resultEntryTime", label: "结果录入时间:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "resultEntryDoctor", label: "结果录入医生:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "resultAuditStatu", label: "结果审核状态:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "resultAuditTime", label: "结果审核时间:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input", name: "resultAuditDoctor", label: "结果审核医生:", width: 150, readonly:true, style: "background:#eaeaea"
                    }
                ]
            }
        ],
        initObj: function () {
            SampleDetailedInfoForm.obj = Layout.obj.cells("c").attachForm(SampleDetailedInfoForm.config);
        },
        setForm: function (checkApplicationId) {
            ajaxUtils.get('reportEntry/getSampleDetailedInfo.json',{
                checkApplicationId:checkApplicationId
            }).then(function (data) {
                SampleDetailedInfoForm.obj.setFormData(data);

                if (data.resultEntryStatu === "未录入" && data.resultAuditStatu === "未审核"){
                    CheckResultEntryToolbar.obj.enableItem("saveButton");
                    CheckResultEntryToolbar.obj.disableItem("auditButton");
                    CheckResultEntryToolbar.obj.disableItem("cancelAuditButton");
                }
                if (data.resultEntryStatu === "已录入" && data.resultAuditStatu === "未审核"){
                    CheckResultEntryToolbar.obj.enableItem("saveButton");
                    CheckResultEntryToolbar.obj.enableItem("auditButton");
                    CheckResultEntryToolbar.obj.disableItem("cancelAuditButton");
                }
                if (data.resultEntryStatu === "已录入" && data.resultAuditStatu === "已审核"){
                    CheckResultEntryToolbar.obj.disableItem("saveButton");
                    CheckResultEntryToolbar.obj.disableItem("auditButton");
                    CheckResultEntryToolbar.obj.enableItem("cancelAuditButton");
                }
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //检验结果录入表格
    var CheckResultEntryGrid = {
        obj: null,
        initObj: function () {
            CheckResultEntryGrid.obj = Layout.obj.cells("d").attachGrid();
            CheckResultEntryGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            CheckResultEntryGrid.obj.setHeader("序号,检验项目,检验结果,参考值,单位,检验项目id", null,
                ["text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            CheckResultEntryGrid.obj.setColumnIds("serialNumber,itemName,defaultValue,referenceValue,unit,itemId");
            CheckResultEntryGrid.obj.setColAlign("center,center,center,center,center");   //设置列中数据居中
            CheckResultEntryGrid.obj.setInitWidths("50,150,*,150,100,0");          //列宽
            CheckResultEntryGrid.obj.setColTypes("ro,ro,ed,ro,ro,ro");
            CheckResultEntryGrid.obj.init();
            CheckResultEntryGrid.obj.setColumnHidden(5,true);
        },
        initEvent: function () {
            
        },
        //加载检验项目及默认结果
        loadData: function (checkApplicationId) {
            ajaxUtils.get('reportEntry/getCheckItemAndDefaultValue.json', {
                checkApplicationId: checkApplicationId
            }).then(function (data) {
                for (var i = 0; i < data.length; i++) {
                    data[i].serialNumber = i+1;
                }
                dhtmlxUtils.clearAndLoadJsonListData(CheckResultEntryGrid.obj, data, "itemId");  //删除所有行,加载数据
                //ReceivedSampleGrid.obj.sortRows(1,"int","asc");
                changeFlag.storeValue();
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //检验结果操作菜单栏
    var CheckResultEntryToolbar = {
        obj: null,
        config: {
            align: "left",
            icon_path: "images/reportEntry/",
            items: [
                {
                    id: "saveButton",
                    type: "button",
                    text: "<span style=\"font-weight: bold;font-size: 12px\">保 存</span>",
                    img: "save.png",
                    img_disabled: "saveDisabled.png"
                },
                {id: "sep1", type: "separator"},
                {
                    id: "auditButton",
                    type: "button",
                    text: "<span style=\"font-weight: bold;font-size: 12px\">审 核</span>",
                    img: "audit.png",
                    img_disabled: "auditDisabled.png"
                },
                {id: "sep1", type: "separator"},
                {
                    id: "cancelAuditButton",
                    type: "button",
                    text: "<span style=\"font-weight: bold;font-size: 12px\">取消审核</span>",
                    img: "cancelAudit.png",
                    img_disabled: "cancelAuditDisabled.png"
                }
            ]
        },
        initobj: function () {
            CheckResultEntryToolbar.obj = Layout.obj.cells("d").attachToolbar(CheckResultEntryToolbar.config);
            CheckResultEntryToolbar.obj.disableItem("saveButton");
            CheckResultEntryToolbar.obj.disableItem("auditButton");
            CheckResultEntryToolbar.obj.disableItem("cancelAuditButton");
        },
        initEvent: function () {
            CheckResultEntryToolbar.obj.attachEvent("onClick", function (name) {
                switch (name) {
                    case "saveButton":
                        CheckResultEntryToolbar.saveButtonEvent();
                        break;
                    case "auditButton":
                        CheckResultEntryToolbar.auditButtonEvent();
                        break;
                    case "cancelAuditButton":
                        CheckResultEntryToolbar.cancelAuditButtonEvent();
                        break;
                    default:
                }
            });
        },
        //保存检验结果
        saveButtonEvent: function () {
            //检查是否有检验结果为空
            var rowsNum = CheckResultEntryGrid.obj.getRowsNum();
            for (var i = 0; i < rowsNum; i++) {
                var itemName = CheckResultEntryGrid.obj.cells2(i, 1).getValue();
                var itemResult = CheckResultEntryGrid.obj.cells2(i, 2).getValue();
                if (itemResult === "" || itemResult === null) {
                    dhtmlxAlert.alertMsg("请输入\"" + itemName + "\"的体检结果");
                    return;
                }
            }
            if (!changeFlag.diff()) {
                dhtmlxAlert.alertMsg("录入的检验结果未发生改变，不需要保存");
                return;
            }

            //检验申请id
            var checkApplicationId = ReceivedSampleGrid.obj.getSelectedRowId();
            var checkResult = [];
            for (var i = 0; i < CheckResultEntryGrid.obj.getRowsNum(); i++) {
                var dto = {};
                dto.checkApplicationId = checkApplicationId;
                dto.checkItemId = CheckResultEntryGrid.obj.getRowId(i);
                dto.result = CheckResultEntryGrid.obj.cells2(i, 2).getValue();
                checkResult[i] = dto;
            }
            ajaxUtils.postBody('reportEntry/saveCheckResult.json',
                checkResult
            ).then(function () {
                OperationForm.searchBtnEvent();
                dhtmlxAlert.alertMsg("保存成功");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        //审核检验结果
        auditButtonEvent: function () {
            if (SampleDetailedInfoForm.obj.getItemValue("resultEntryStatu") === "未录入"){
                dhtmlxAlert.alertWarningMsg("结果未录入，不能审核");
                return;
            }
            if (SampleDetailedInfoForm.obj.getItemValue("resultAuditStatu") === "已审核"){
                dhtmlxAlert.alertWarningMsg("结果已审核");
                return;
            }
            //检验申请id
            var checkApplicationId = ReceivedSampleGrid.obj.getSelectedRowId();
            ajaxUtils.get('reportEntry/auditCheckResult.json', {
                checkApplicationId: checkApplicationId
            }).then(function () {
                OperationForm.searchBtnEvent();
                dhtmlxAlert.alertMsg("审核成功");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        //取消审核结果
        cancelAuditButtonEvent: function () {
            //检验申请id
            var checkApplicationId = ReceivedSampleGrid.obj.getSelectedRowId();
            ajaxUtils.get('reportEntry/cancelAuditCheckResult.json', {
                checkApplicationId: checkApplicationId
            }).then(function () {
                OperationForm.searchBtnEvent();
                dhtmlxAlert.alertMsg("取消审核成功");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //判断检验结果录入表格数据是否变化
    var changeFlag = {
        checkResult: [],

        storeValue: function () {
            changeFlag.checkResult = changeFlag.getCheckResult();
        },
        getCheckResult: function () {
            var checkResult = [];
            var length = CheckResultEntryGrid.obj.getRowsNum();
            for (var i = 0; i < length; i++) {
                checkResult.push(CheckResultEntryGrid.obj.cells2(i, 2).getValue());
            }
            return checkResult;
        },
        compare: function (old, now) {
            if (old.length != now.length) {
                return false;
            }
            for (var i = 0; i < old.length; i++) {
                if (old[i] != now[i]) {
                    return false;
                }
            }
            return true;
        },
        diff: function () {
            return !(changeFlag.compare(changeFlag.checkResult, changeFlag.getCheckResult()));
        }
    };

    var init = function () {
        Layout.initObj();
        OperationForm.initObj();
        OperationForm.initEvent();
        ReceivedSampleGrid.initObj();
        ReceivedSampleGrid.initEvent();
        SampleDetailedInfoForm.initObj();
        CheckResultEntryGrid.initObj();
        CheckResultEntryGrid.initEvent();
        CheckResultEntryToolbar.initobj();
        CheckResultEntryToolbar.initEvent();
    };


    var reportEntry = function () {};
    reportEntry.init = init;
    global.reportEntry = reportEntry || {};
}(this);