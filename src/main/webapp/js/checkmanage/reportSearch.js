//报告查询页面
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
                    height: 150
                },
                {
                    id: "b",
                    text: "已审核样本列表",
                    header: true,
                    collapsed_text: "已审核样本列表",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    width: 450
                },
                {
                    id: "c",
                    text: "样本详细信息",
                    header: true,      // 显示标题
                    collapsed_text: "样本详细信息",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    width: 300
                },
                {
                    id: "d",
                    text: "检验结果查询",
                    header: true,      // 显示标题
                    collapsed_text: "检验结果查询",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true]
                }
            ]
        },
        initObj: function () {
            Layout.obj = SystemHome.Layout.obj.cells("a").attachLayout(Layout.config);
        }
    };

    //查询已审核样本操作栏
    var OperationForm = {
        obj: null,
        config: [
            {type: "settings", position: "label-left", offsetLeft: 0, offsetTop: 2},
            {
                type:"block", list: [
                    {type: "input", name: "patientName", label: "姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：", width: 120},
                    {type: "newcolumn"},
                    {type: "input", name: "patientId", label: "就诊卡号：", width: 120, offsetLeft: 10}
                ]
            },
            {
                type: "block", list: [
                    {type: "calendar", name: "checkStartDate", label: "检验时间：", inputWidth: 120},
                    {type: "newcolumn"},
                    {type: "calendar", name: "checkEndDate", label: "&nbsp;&nbsp;~&nbsp;&nbsp;", inputWidth: 120 }
                ]
            },
            {
                type: "block", list: [
                    {type: "calendar", name: "auditStartDate", label: "审核时间：", inputWidth: 120},
                    {type: "newcolumn"},
                    {type: "calendar", name: "auditEndDate", label: "&nbsp;&nbsp;~&nbsp;&nbsp;", inputWidth: 120 }
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
            OperationForm.obj.setItemValue('checkEndDate',JSUtils.getToday());
            OperationForm.obj.setItemValue('auditEndDate',JSUtils.getToday());
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
            var patientId = OperationForm.obj.getItemValue("patientId");
            if(JSUtils.isNotEmpty(patientId) && JSUtils.isNotNum(patientId)){
                dhtmlxAlert.alertMsg("就诊卡号请输入数字");
                return;
            }
            var barCodeNumber = OperationForm.obj.getItemValue("barCodeNumber");
            if(JSUtils.isNotEmpty(barCodeNumber) && JSUtils.isNotNum(barCodeNumber)){
                dhtmlxAlert.alertMsg("条码号请输入数字");
                return;
            }

           /* //清空样本详细信息表单和检验结果录入表格
            SampleDetailedInfoForm.obj.clear();
            SampleDetailedInfoForm.obj.setItemValue("isEmergency",-1);
            CheckResultGrid.obj.clearAll();*/
            //加载数据
            AuditedSampleGrid.loadData();
        }
    };

    //已审核样本列表
    var AuditedSampleGrid = {
        obj: null,
            initObj: function () {
            AuditedSampleGrid.obj = Layout.obj.cells("b").attachGrid();
            AuditedSampleGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            AuditedSampleGrid.obj.setHeader("就诊卡号,患者姓名,条码号,检验项目组合,检验申请id", null,
                ["text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            AuditedSampleGrid.obj.setColumnIds("patientId,patientName,barcodeNumber,checkItemGroup,itemId");
            AuditedSampleGrid.obj.setColAlign("center,center,center,center");   //设置列中数据居中
            AuditedSampleGrid.obj.setInitWidths("100,100,100,*,0");          //列宽
            AuditedSampleGrid.obj.setColTypes("ro,ro,ro,ro,ro");
            AuditedSampleGrid.obj.init();
            AuditedSampleGrid.obj.setColumnHidden(4, true);

            //底部分页栏
            Layout.obj.cells("b").attachStatusBar({
                text: "<div id='exampaging'></div>",
                height: 30
            });
            AuditedSampleGrid.obj.enablePaging(true,18,5,"exampaging",true);
            AuditedSampleGrid.obj.i18n.paging = i18n_paging;
            AuditedSampleGrid.obj.setPagingSkin("toolbar");

            AuditedSampleGrid.loadData();
        },
        initEvent: function () {
            //点击某行时显示样本详细信息和默认检验结果
            AuditedSampleGrid.obj.attachEvent("onRowSelect", function(id,ind) {
                SampleDetailedInfoForm.setForm(id);
                CheckResultGrid.loadData(id);
            });
        },
        loadData: function () {
            var formData = OperationForm.obj.getFormData();
            formData.checkStartDate = OperationForm.obj.getInput("checkStartDate").value;
            formData.checkEndDate = OperationForm.obj.getInput("checkEndDate").value;
            formData.auditStartDate = OperationForm.obj.getInput("auditStartDate").value;
            formData.auditEndDate = OperationForm.obj.getInput("auditEndDate").value;
            ajaxUtils.postBody('reportSearch/getAuditedSample.json',
                formData
            ).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(AuditedSampleGrid.obj, data, "itemId");  //删除所有行,加载数据
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
                if (JSUtils.isNotEmpty(data.sampleReceptionTime)) {
                    data.sampleReceptionTime = data.sampleReceptionTime.substring(0,16)
                }
                if (JSUtils.isNotEmpty(data.resultEntryTime)) {
                    data.resultEntryTime = data.resultEntryTime.substring(0,16)
                }
                if (JSUtils.isNotEmpty(data.resultAuditTime)) {
                    data.resultAuditTime = data.resultAuditTime.substring(0,16)
                }
                SampleDetailedInfoForm.obj.setFormData(data);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //检验结果表格
    var CheckResultGrid = {
        obj: null,
        initObj: function () {
            CheckResultGrid.obj = Layout.obj.cells("d").attachGrid();
            CheckResultGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            CheckResultGrid.obj.setHeader("序号,检验项目,检验结果,参考值,单位,检验项目id", null,
                ["text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;", "text-align:center;"]);  //设置标题内容居中
            CheckResultGrid.obj.setColumnIds("serialNumber,itemName,defaultValue,referenceValue,unit,itemId");
            CheckResultGrid.obj.setColAlign("center,center,center,center,center");   //设置列中数据居中
            CheckResultGrid.obj.setInitWidths("50,150,*,150,100,0");          //列宽
            CheckResultGrid.obj.setColTypes("ro,ro,ro,ro,ro,ro");
            CheckResultGrid.obj.init();
            CheckResultGrid.obj.setColumnHidden(5,true);
        },
        //加载检验项目及结果
        loadData: function (checkApplicationId) {
            ajaxUtils.get('reportEntry/getCheckItemAndDefaultValue.json', {
                checkApplicationId: checkApplicationId
            }).then(function (data) {
                for (var i = 0; i < data.length; i++) {
                    data[i].serialNumber = i+1;
                }
                dhtmlxUtils.clearAndLoadJsonListData(CheckResultGrid.obj, data, "itemId");  //删除所有行,加载数据
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //检验结果操作菜单栏
    var CheckResultToolbar = {
        obj: null,
        config: {
            align: "left",
            icon_path: "images/reportSearch/",
            items: [
                {
                    id: "printButton",
                    type: "button",
                    text: "<span style=\"font-weight: bold;font-size: 12px\">打 印</span>",
                    img: "print.png"
                }
            ]
        },
        initobj: function () {
            CheckResultToolbar.obj = Layout.obj.cells("d").attachToolbar(CheckResultToolbar.config);
        },
        initEvent: function () {
            CheckResultToolbar.obj.attachEvent("onClick", function (name) {
                switch (name) {
                    case "printButton":
                        CheckResultToolbar.printButtonEvent();
                        break;
                    default:
                }
            });
        },
        printButtonEvent:function () {
            var selectedRowId = AuditedSampleGrid.obj.getSelectedRowId();
            if (JSUtils.isEmpty(selectedRowId)){
                dhtmlxAlert.alertWarningMsg("请先选择要打印的报告！");
                return;
            }

            var sampleInfo = SampleDetailedInfoForm.obj.getFormData();
            sampleInfo.hosnName = "测试医院";
            var checkResultArr = [];
            var i = 0;
            CheckResultGrid.obj.forEachRow(function(id){
                checkResultArr[i++] = CheckResultGrid.obj.getRowData(id);
            });

            printCheckResult(sampleInfo,checkResultArr);
        }
    };

    var init = function () {
        Layout.initObj();
        OperationForm.initObj();
        OperationForm.initEvent();
        AuditedSampleGrid.initObj();
        AuditedSampleGrid.initEvent();
        SampleDetailedInfoForm.initObj();
        CheckResultGrid.initObj();
        CheckResultToolbar.initobj();
        CheckResultToolbar.initEvent();
    };

    var reportSearch = function () {};
    reportSearch.init = init;
    global.reportSearch = reportSearch || {};
}(this);