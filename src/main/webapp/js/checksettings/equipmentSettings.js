// 检验仪器设置页面
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
                    text: "textA",
                    header: false,      // 隐藏标题
                    collapsed_text: "collapsed_textA",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true],
                    height:50
                },
                {
                    id: "b",
                    text: "textB",
                    header: false,      // 隐藏标题
                    collapsed_text: "collapsed_textB",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true]
                }
            ]
        },

        initObj: function () {
            Layout.obj = SystemHome.Layout.obj.cells("a").attachLayout(Layout.config);
        }
    };

    //顶部单元格内容
    var ItemOperationForm = {
        obj: null,

        config: [
            {type: "input", name: "equipment", label: "检验仪器：", width: 150, offsetLeft: 10, offsetTop: 12, maxLength: 20},
            {type: "newcolumn"},
            {type: "button", name: "itemSearchBtn", value: "查询", offsetLeft: 20},
            {type: "newcolumn"},
            {type: "button", name: "itemAddBtn", value: "新增", offsetLeft: 10},
            {type: "newcolumn"},
            {type: "button", name: "itemDeleteBtn", value: "删除", offsetLeft: 10}
        ],
        initObj: function () {
            ItemOperationForm.obj = Layout.obj.cells("a").attachForm(ItemOperationForm.config);
            //加载表格
            ItemOperationForm.itemSearchBtnEvent();
        },
        initEvent:function () {
            //按回车时搜索
            ItemOperationForm.obj.attachEvent("onEnter",function () {
                ItemOperationForm.itemSearchBtnEvent();
            });
            //关联按钮
            ItemOperationForm.obj.attachEvent("onButtonClick", function (name) {
                switch (name) {
                    case "itemSearchBtn":
                        ItemOperationForm.itemSearchBtnEvent();
                        break;
                    case "itemAddBtn":
                        ItemOperationForm.itemAddBtnEvent();
                        break;
                    case "itemDeleteBtn":
                        var itemIdList = dhtmlxUtils.getCheckedRowIds(ItemGrid.obj);   //获取ItemGrid中checkbox所有被勾选上行的rowId
                        if (itemIdList.length === 0){
                            dhtmlxAlert.alertMsg("需至少选中一个要删除的项目");
                            return;
                        }
                        dhtmlxAlert.confirmWarningMsg("删除检验仪器时，同时会删除检验仪器——检验项目之间的关联，请确认是否删除?", function () {
                            ItemOperationForm.itemDeleteBtnEvent(itemIdList);
                        });
                        break;
                    default:
                }
            });
        },
        //查询功能()
        itemSearchBtnEvent: function () {
            var equipment = ItemOperationForm.obj.getItemValue("equipment");
            ItemGrid.loadData(equipment);
        },
        //添加功能
        itemAddBtnEvent:function () {
            ItemDetailWindow.createObj();
        },
        //删除功能
        itemDeleteBtnEvent: function (itemIdList) {
            ajaxUtils.postBody('equipmentSettings/deleteEquipments.json',
                itemIdList
            ).then(function (data) {
                //重新加载表格
                ItemOperationForm.itemSearchBtnEvent();
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //检验项目列表
    var ItemGrid = {
        obj: null,
        initObj: function () {
            ItemGrid.obj = Layout.obj.cells("b").attachGrid();
            ItemGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ItemGrid.obj.setHeader("选择,编号,检验设备名称,型号,生产厂家,所属科室,备注",null,
                ["text-align:center;","text-align:center;","text-align:center","text-align:center","text-align:center","text-align:center","text-align:center"]);  //设置标题内容居中
            ItemGrid.obj.setColumnIds("ch,itemId,itemName,model,factory,departmentName,note");
            ItemGrid.obj.setColAlign("center,center,center,center,center,center,center");   //设置列中数据居中
            ItemGrid.obj.setInitWidths("50,150,*,200,200,200,200");          //列宽
            ItemGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro,ro");
            ItemGrid.obj.init();

            //底部分页栏
            Layout.obj.cells("b").attachStatusBar({
                text: "<div id='exampaging'></div>",
                height: 30
            });
            ItemGrid.obj.enablePaging(true,18,5,"exampaging",true);
            ItemGrid.obj.i18n.paging = i18n_paging;
            ItemGrid.obj.setPagingSkin("toolbar");
        },
        initEvent: function () {
            ItemGrid.obj.attachEvent("onRowDblClicked",function () {
                var rowData = dhtmlxUtils.getSelectedRowBindingData(ItemGrid.obj);
                ItemDetailWindow.createObj(rowData);
            });
        },
        loadData: function (equipment) {
            ajaxUtils.get('equipmentSettings/getEquipments.json', {
                equipment:equipment
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(ItemGrid.obj, data, "itemId");  //删除所有行，加载数据
                ItemGrid.obj.sortRows(1,"int","asc");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //新增以及查看详细信息窗口
    var ItemDetailWindow = {
        obj: null,
        createObj: function (rowData) {
            var windowFactory = new dhtmlXWindows();
            ItemDetailWindow.obj = windowFactory.createWindow("ItemDetailWindow", 0, 0, 700, 450);   //(id, left, top, width, height)
            ItemDetailWindow.obj.setText("项目详情");  //标题
            ItemDetailWindow.obj.denyResize();  //拒绝调整大小
            ItemDetailWindow.obj.denyPark();
            ItemDetailWindow.obj.setModal(true);
            ItemDetailWindow.obj.centerOnScreen(); //窗口居中显示在屏幕中
            ItemDetailWindow.initWindow(rowData);
        },
        initWindow: function (rowData) {
            ItemDetailWindow.Layout.initObj();
            ItemDetailWindow.Form.initObj();
            ItemDetailWindow.Form.initEvent();
            ItemDetailWindow.Form.loadData(rowData);
        }    
    };

    //详细信息窗口布局设置
    ItemDetailWindow.Layout = {
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
            ItemDetailWindow.Layout.obj = ItemDetailWindow.obj.attachLayout(ItemDetailWindow.Layout.config);
        }
    };

    //详细信息窗口表单设置
    ItemDetailWindow.Form = {
        obj: null,
        config: [
            {type: "settings", position: "label-left", blockOffset: 0, offsetLeft: 30, offsetTop: 13},
            {
                type: "block", list: [
                    {type: "input", name: "itemName", label: "检验设备", value: "", inputWidth: 180,maxLength:15},
                    {type: "newcolumn"},
                    {type: "input", name: "itemId", label: "检验设备编号", value: "",inputWidth: 180,readonly: true,style:"background:#eaeaea"}
                ]
            },
            {
                type: "block", list: [
                    {type: "combo", name:"model",label: "型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号", inputWidth: 180,maxLength:15,
                        options:[
                            {text: "", value: ""},
                            {text: "生化仪", value: "生化仪"},
                            {text: "血球仪", value: "血球仪"},
                            {text: "尿分仪", value: "尿分仪"},
                            {text: "血凝仪", value: "血凝仪"},
                            {text:"血气仪", value:"血气仪"},
                            {text: "免疫定量分析仪", value: "免疫定量分析仪"}
                        ]},
                    {type: "newcolumn"},
                    {type: "input", name:"factory",label: "生&nbsp;&nbsp;产&nbsp;&nbsp;厂&nbsp;&nbsp;家", inputWidth: 180}
                ]
            },
            {
                type: "block", list: [
                    {type: "combo", name:"departmentName",label: "所属科室", inputWidth: 180,maxLength:10,
                        options:[
                            {text: "", value: ""},
                            {text: "检验科", value: "检验科"},
                            {text: "血库", value: "血库"},
                            {text: "重症康复病区", value: "重症康复病区"},
                            {text: "医技辅助", value: "医技辅助"},
                            {text: "临床科室", value: "临床科室"},
                            {text: "外科", value: "外科"},
                            {text: "眼科", value: "眼科"},
                            {text: "耳鼻喉科", value: "耳鼻喉科"},
                            {text:"麻醉科", value:"麻醉科"},
                            {text:"妇产科", value:"妇产科"},
                            {text:"康复科", value:"康复科"},
                            {text:"抢救科室", value:"抢救科室"}
                        ]}
                ]
            },
            {
                type: "block", list: [
                    {type: "input", rows:3,name:"note",label: "备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注", inputWidth: 480}
                ]
            },
            {
                type: "block", list: [
                    {type: "button", name: "itemDetailSaveBtn", value: "保存", offsetLeft: 200, offsetTop: 15},
                    {type: "newcolumn"},
                    {type: "button", name: "itemDetailCancelBtn", value: "取消", offsetLeft: 20, offsetTop: 15}
                ]
            }
        ],
        initObj: function () {
            ItemDetailWindow.Form.obj = ItemDetailWindow.Layout.obj.cells("a").attachForm(ItemDetailWindow.Form.config);
        },
        initEvent: function () {
            ItemDetailWindow.Form.obj.attachEvent("onButtonClick", function (name) {
                switch (name) {
                    case "itemDetailSaveBtn":
                        ItemDetailWindow.Form.itemDetailSaveBtnEvent();
                        break;
                    case "itemDetailCancelBtn":
                        ItemDetailWindow.Form.itemDetailCancelBtnEvent();
                        break;
                    default:
                }
            });
        },
        //保存按钮
        itemDetailSaveBtnEvent: function () {
            var formData = ItemDetailWindow.Form.obj.getFormData();
            if (!formData.itemName) {
                dhtmlxAlert.alertWarningMsg("检验设备名称不可为空");
                return;
            }
            ajaxUtils.postBody('equipmentSettings/saveEquipment.json',
                formData
            ).then(function (data) {
                ItemDetailWindow.obj.close();
                ItemOperationForm.itemSearchBtnEvent();
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        //取消按钮
        itemDetailCancelBtnEvent: function () {
            ItemDetailWindow.obj.close();
        },
        //双击查看项目后回写表单
        loadData: function (rowData) {
            if (!rowData){
                return;
            }
            ItemDetailWindow.Form.obj.setFormData(rowData);
        }
    };

    var init = function () {
    Layout.initObj();
    ItemOperationForm.initObj();
    ItemOperationForm.initEvent();
    ItemGrid.initObj();
    ItemGrid.initEvent();
    };

    var equipmentSettings = function(){};
    equipmentSettings.init = init;
    global.equipmentSettings = equipmentSettings||{};
}(this);