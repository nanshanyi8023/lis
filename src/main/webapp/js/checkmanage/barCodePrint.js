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
                top: 2,
                right: 2,
                bottom: 2,
                left: 2
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
                    text: "病人列表",
                    header: true,      // 显示标题
                    collapsed_text: "病人列表",   // 折叠栏标题
                    collapse: false,       // 初始不折叠
                    fix_size: [true, true],
                    width:250
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
            Layout.obj = new dhtmlXLayoutObject(Layout.config);
        }
    };

    //操作栏，查询和打印
    var OperationForm = {
        obj:null,
        config: [
            {type: "input", name: "patientId", label: "病人id：", width: 100, offsetLeft: 50, offsetTop: 12, maxLength: 20},
            {type: "newcolumn"},
            {type: "input", name: "patientName", label: "病人姓名：", width: 100, offsetLeft: 10, offsetTop: 12, maxLength: 20},
            {type: "newcolumn"},
            {type: "calendar", name: "startDate", label: "申请时间：", inputWidth:100, offsetLeft: 10, offsetTop: 12},
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
            PatientListGrid.obj.loadData(formData);
            //清空医嘱列表并选择病人列表第一个

        },
        //打印条码按钮功能
        printBarCodeBtnEvent:function () {

        }
    };

    //病人列表
    var PatientListGrid = {
        obj:null,
        initObj: function () {
            PatientListGrid.obj = Layout.obj.cells("b").attachGrid();
            PatientListGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            PatientListGrid.obj.setHeader("选择,病人id,病人姓名",null, ["text-align:center;","text-align:center;","text-align:center;"]);  //设置标题内容居中
            PatientListGrid.obj.setColumnIds("ch,patientId,patientName");
            PatientListGrid.obj.setColAlign("center,center,center");   //设置列中数据居中
            PatientListGrid.obj.setInitWidths("40,*,130");          //列宽
            PatientListGrid.obj.setColTypes("ch,ro,ro");
            PatientListGrid.obj.init();
        },
        initEvent: function () {
            PatientListGrid.obj.attachEvent("onRowSelect",function () {
                //自动勾选上本行，并查找该病人对应的检验医嘱

            });
        },
        loadData: function () {
            ajaxUtils.get('barCodePrint/getPatientInfo.json', {

            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(PatientListGrid.obj, data, "patientId");  //删除所有行，加载数据
                PatientListGrid.obj.sortRows(1,"int","asc");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //检验申请列表
    var checkApplicationGrid = {
        obj:null,
        initObj: function () {
            checkApplicationGrid.obj = Layout.obj.cells("c").attachGrid();
            checkApplicationGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            checkApplicationGrid.obj.setHeader("选择,姓名,检验项目,价格,",null, ["text-align:center;","text-align:center;","text-align:center;"]);  //设置标题内容居中
            checkApplicationGrid.obj.setColumnIds("ch,patientName,doctorOrderItem,itemPrice,");
            checkApplicationGrid.obj.setColAlign("center,center,center");   //设置列中数据居中
            checkApplicationGrid.obj.setInitWidths("40,*,130");          //列宽
            checkApplicationGrid.obj.setColTypes("ch,ro,ro");
            checkApplicationGrid.obj.init();
        },
        initEvent: function () {
            checkApplicationGrid.obj.attachEvent("onRowSelect",function () {
                //自动勾选上本行，并查找该病人对应的检验医嘱

            });
        },
        loadData: function () {
            ajaxUtils.get('barCodePrint/getPatientInfo.json', {

            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(checkApplicationGrid.obj, data, "patientId");  //删除所有行，加载数据
                checkApplicationGrid.obj.sortRows(1,"int","asc");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    var init = function () {
        Layout.initObj();
        OperationForm.initObj();
        PatientListGrid.initObj();
        PatientListGrid.initEvent();
    };

    var barCodePrint = function(){};
    barCodePrint.init = init;
    global.barCodePrint = barCodePrint||{};
}(this);