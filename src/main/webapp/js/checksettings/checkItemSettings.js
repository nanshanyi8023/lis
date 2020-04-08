// 检验项目设置页面
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
                    height: 50
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

    //将操作栏下面的区域重新划分为三个区域
    var BottomLayout = {
        obj: null,

        config: {
            pattern: "3L",
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
                    fix_size: [true, true]
                },
                {
                    id: "b",
                    text: "所关联检验设备",
                    header: true,
                    collapsed_text: "所关联检验设备",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true],
                    width:400,
                    height:300
                },
                {
                    id: "c",
                    text: "所关联检验项目组合",
                    header: true,
                    collapsed_text: "所关联检验项目组合",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true]
                }
            ]
        },

        initObj: function () {
            BottomLayout.obj = Layout.obj.cells("b").attachLayout(BottomLayout.config);
        }
    };

    //顶部单元格内容
    var ItemOperationForm = {
        obj: null,

        config: [
            {type: "combo", name: "equipment", label: "检验设备：", width: 150, offsetLeft: 10, offsetTop: 12, maxLength: 20},
            {type: "newcolumn"},
            {
                type: "combo",
                name: "checkItemGroup",
                label: "检验项目组合：",
                width: 200,
                offsetLeft: 10,
                offsetTop: 12,
                maxLength: 20
            },
            {type: "newcolumn"},
            {
                type: "input",
                name: "checkItem",
                label: "检验项目：",
                width: 150,
                offsetLeft: 10,
                offsetTop: 12,
                maxLength: 20
            },
            {type: "newcolumn"},
            {type: "button", name: "itemSearchBtn", value: "查询", offsetLeft: 20},
            {type: "newcolumn"},
            {type: "button", name: "clearSearchBtn", value: "清空查询", offsetLeft: 20},
            {type: "newcolumn"},
            {type: "button", name: "itemAddBtn", value: "新增", offsetLeft: 10},
            {type: "newcolumn"},
            {type: "button", name: "itemDeleteBtn", value: "删除", offsetLeft: 10}
        ],
        initObj: function () {
            ItemOperationForm.obj = Layout.obj.cells("a").attachForm(ItemOperationForm.config);
            //初始化检验设备下拉框
            ItemOperationForm.getAllEquipment();
            //加载表格
            ItemOperationForm.itemSearchBtnEvent();
        },
        //查找所有工作组
        getAllEquipment: function () {
            ajaxUtils.get('checkItemSettings/getAllEquipment.json'
            ).then(function (data) {
                //初始化检验设备下拉框
                var equipmentCombo = ItemOperationForm.obj.getCombo("equipment");
                equipmentCombo.clearAll();
                var options = data.map(function (e, index, array) {
                    return [e.itemId, e.itemName];
                });
                equipmentCombo.addOption(options);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        //查找所有检验项目组合
        getAllCheckItemGroup: function (equipmentId) {
            ajaxUtils.get('checkItemSettings/getAllCheckItemGroup.json', {
                equipmentId: equipmentId
            }).then(function (data) {
                //初始化检验项目组合下拉框
                var checkItemGroupCombo = ItemOperationForm.obj.getCombo("checkItemGroup");
                checkItemGroupCombo.clearAll();
                checkItemGroupCombo.setComboText("");
                var options = data.map(function (e, index, array) {
                    return [e.groupId, e.groupName];
                });
                checkItemGroupCombo.addOption(options);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        initEvent: function () {
            //初始化检验项目组合下拉框
            ItemOperationForm.obj.attachEvent("onChange", function (name, value, state) {
                //当工作组下拉框变化时，检验项目组合下拉框也变
                if (name == 'equipment') {
                    var equipmentId = ItemOperationForm.obj.getCombo("equipment").getSelectedValue();
                    if (equipmentId !== null && equipmentId !== "") {
                        ItemOperationForm.getAllCheckItemGroup(equipmentId);
                    }
                }
            });
            //按回车时搜索
            ItemOperationForm.obj.attachEvent("onEnter", function () {
                ItemOperationForm.itemSearchBtnEvent();
            });
            ItemOperationForm.obj.getCombo("equipment").attachEvent("onKeyPressed", function (keyCode) {
                if (keyCode == '13') {
                    ItemOperationForm.itemSearchBtnEvent();
                }
            });
            ItemOperationForm.obj.getCombo("checkItemGroup").attachEvent("onKeyPressed", function (keyCode) {
                if (keyCode == '13') {
                    ItemOperationForm.itemSearchBtnEvent();
                }
            });
            //关联按钮
            ItemOperationForm.obj.attachEvent("onButtonClick", function (name) {
                switch (name) {
                    case "itemSearchBtn":
                        ItemOperationForm.itemSearchBtnEvent();
                        break;
                    case "clearSearchBtn":
                        ItemOperationForm.clearSearchBtnEvent();
                        break;
                    case "itemAddBtn":
                        ItemOperationForm.itemAddBtnEvent();
                        break;
                    case "itemDeleteBtn":
                        var itemIdList = dhtmlxUtils.getCheckedRowIds(ItemGrid.obj);   //获取ItemGrid中checkbox所有被勾选上行的rowId
                        if (itemIdList.length === 0) {
                            dhtmlxAlert.alertMsg("需至少选中一个要删除的项目");
                            return;
                        }
                        dhtmlxAlert.confirmWarningMsg("删除检验项目时，同时会删除检验项目——检验仪器，检验项目——检验项目组合之间的关联，请确认是否删除?", function () {
                            ItemOperationForm.itemDeleteBtnEvent(itemIdList);
                        });
                        break;
                    default:
                }
            });
        },
        //查询功能(支持项目编号，项目名称，英文缩写查询)
        itemSearchBtnEvent: function () {
            var equipmentId = ItemOperationForm.obj.getCombo("equipment").getSelectedValue();
            var checkItemGroupId = ItemOperationForm.obj.getCombo("checkItemGroup").getSelectedValue();
            var checkItem = ItemOperationForm.obj.getItemValue("checkItem");
            ItemGrid.loadData(equipmentId, checkItemGroupId, checkItem);
        },
        //清空查询功能
        clearSearchBtnEvent: function () {
            ItemOperationForm.obj.clear();
            ItemOperationForm.obj.getCombo("equipment").unSelectOption();
            ItemOperationForm.obj.getCombo("checkItemGroup").unSelectOption();
            ItemGrid.loadData();
        },
        //添加功能
        itemAddBtnEvent: function () {
            ItemDetailWindow.createObj();
        },
        //删除功能
        itemDeleteBtnEvent: function (itemIdList) {
            ajaxUtils.postBody('checkItemSettings/deleteCheckItems.json',
                itemIdList
            ).then(function (data) {
                //重新加载表格
                ItemOperationForm.itemSearchBtnEvent();
                RightEquipmentGrid.obj.clearAll();
                RightCheckItemGroupGrid.obj.clearAll();
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
            ItemGrid.obj = BottomLayout.obj.cells("a").attachGrid();
            ItemGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ItemGrid.obj.setHeader("选择,编号,项目名称,英文缩写,计量单位,项目类型,参考值,默认值", null,
                ["text-align:center;", "text-align:center;", "text-align:center", "text-align:center", "text-align:center", "text-align:center", "text-align:center", "text-align:center"]);  //设置标题内容居中
            ItemGrid.obj.setColumnIds("ch,itemId,itemName,englishAbbreviation,unit,itemType,referenceValue,defaultValue");
            ItemGrid.obj.setColAlign("center,center,center,center,center,center,center,center");   //设置列中数据居中
            ItemGrid.obj.setInitWidths("50,120,*,120,120,120,120,120");          //列宽
            ItemGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro,ro,ro");
            ItemGrid.obj.init();

            //底部分页栏
            BottomLayout.obj.cells("a").attachStatusBar({
                text: "<div id='exampaging'></div>",
                height: 30
            });
            ItemGrid.obj.enablePaging(true,23,5,"exampaging",true);
            ItemGrid.obj.i18n.paging = i18n_paging;
            ItemGrid.obj.setPagingSkin("toolbar");
        },
        initEvent: function () {
            ItemGrid.obj.attachEvent("onRowDblClicked", function () {
                var rowData = dhtmlxUtils.getSelectedRowBindingData(ItemGrid.obj);
                ItemDetailWindow.createObj(rowData);
            });

            //单击选中行时查找对应的检验设备和检验项目组合
            ItemGrid.obj.attachEvent("onRowSelect", function(id,ind){
                RightEquipmentGrid.loadData(id);
                RightCheckItemGroupGrid.loadData(id);
            });
        },
        loadData: function (equipmentId, checkItemGroupId, checkItem) {
            ajaxUtils.get('checkItemSettings/getCheckItems.json', {
                equipmentId: equipmentId,
                checkItemGroupId: checkItemGroupId,
                checkItem: checkItem
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(ItemGrid.obj, data, "itemId");  //删除所有行，加载数据
                ItemGrid.obj.sortRows(1, "int", "asc");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };
    
    //右侧展示选中的检验项目对应的检验设备
    var RightEquipmentGrid = {
        obj:null,
        initObj: function () {
            RightEquipmentGrid.obj = BottomLayout.obj.cells("b").attachGrid();
            RightEquipmentGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            RightEquipmentGrid.obj.setHeader("编号,所关联检验设备", null,
                ["text-align:center;", "text-align:center;"]);  //设置标题内容居中
            RightEquipmentGrid.obj.setColumnIds("itemId,itemName");
            RightEquipmentGrid.obj.setColAlign("center,center");   //设置列中数据居中
            RightEquipmentGrid.obj.setInitWidths("150,*");          //列宽
            RightEquipmentGrid.obj.setColTypes("ro,ro");
            RightEquipmentGrid.obj.init();
        },
        loadData: function (selectedRowId) {
            ajaxUtils.get('checkItemSettings/getAssociatedEquipment.json', {
                checkItemId: selectedRowId
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(RightEquipmentGrid.obj, data, "itemId");  //删除所有行，加载数据
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //右侧展示选中的检验项目对应的检验项目组合
    var RightCheckItemGroupGrid = {
        obj:null,
        initObj: function () {
            RightCheckItemGroupGrid.obj = BottomLayout.obj.cells("c").attachGrid();
            RightCheckItemGroupGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            RightCheckItemGroupGrid.obj.setHeader("编号,所关联检验项目组合", null,
                ["text-align:center;", "text-align:center;"]);  //设置标题内容居中
            RightCheckItemGroupGrid.obj.setColumnIds("groupId,groupName");
            RightCheckItemGroupGrid.obj.setColAlign("center,center");   //设置列中数据居中
            RightCheckItemGroupGrid.obj.setInitWidths("150,*");          //列宽
            RightCheckItemGroupGrid.obj.setColTypes("ro,ro");
            RightCheckItemGroupGrid.obj.init();
        },
        loadData: function (selectedRowId) {
            ajaxUtils.get('checkItemSettings/getAssociatedCheckItemGroup.json', {
                checkItemId: selectedRowId
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(RightCheckItemGroupGrid.obj, data, "groupId");  //删除所有行，加载数据
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
            ItemDetailWindow.obj = windowFactory.createWindow("ItemDetailWindow", 0, 0, 700, 400);   //(id, left, top, width, height)
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
            {type: "settings", position: "label-left", blockOffset: 0, offsetLeft: 30, offsetTop: 10},
            {
                type: "block", list: [
                    {type: "input", name: "itemName", label: "检验项目", value: "", inputWidth: 180, maxLength: 15},
                    {type: "newcolumn"},
                    {
                        type: "input",
                        name: "itemId",
                        label: "检验项目编号",
                        value: "",
                        inputWidth: 180,
                        readonly: true,
                        style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {type: "input", name: "englishAbbreviation", label: "英文缩写", inputWidth: 180, maxLength: 15},
                    {type: "newcolumn"},
                    {
                        type: "input",
                        name: "unit",
                        label: "计&nbsp&nbsp量&nbsp&nbsp单&nbsp&nbsp位",
                        inputWidth: 180,
                        maxLength: 10
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input",
                        name: "referenceValue",
                        label: "参&nbsp&nbsp考&nbsp值",
                        inputWidth: 180,
                        maxLength: 15
                    },
                    {type: "newcolumn"},
                    {
                        type: "input",
                        name: "defaultValue",
                        label: "默&nbsp&nbsp&nbsp&nbsp认&nbsp&nbsp&nbsp&nbsp&nbsp值",
                        inputWidth: 180,
                        maxLength: 10
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "combo", id: "itemType", name: "itemType", label: "项目类型", inputWidth: 180,
                        options: [
                            {text: "常规项目", value: "常规项目", selected: true},
                            {text: "生化项目", value: "生化项目"},
                            {text: "普通项目", value: "普通项目"},
                            {text: "临检项目", value: "临检项目"},
                            {text: "血凝项目", value: "血凝项目"},
                            {text: "血库项目", value: "血库项目"},
                            {text: "免疫项目", value: "免疫项目"},
                            {text: "细菌", value: "细菌"}
                        ]
                    }
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
                dhtmlxAlert.alertWarningMsg("检验项目名称不可为空");
                return;
            }
            if (!formData.itemType) {
                dhtmlxAlert.alertWarningMsg("项目类型不可为空");
                return;
            }
            ajaxUtils.postBody('checkItemSettings/saveCheckItem.json',
                formData
            ).then(function (data) {
                ItemDetailWindow.obj.close();
                ItemOperationForm.itemSearchBtnEvent();
                RightEquipmentGrid.obj.clearAll();
                RightCheckItemGroupGrid.obj.clearAll();
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
            if (!rowData) {
                return;
            }
            ItemDetailWindow.Form.obj.setFormData(rowData);
        }
    };

    var init = function () {
        Layout.initObj();
        BottomLayout.initObj();
        ItemOperationForm.initObj();
        ItemOperationForm.initEvent();
        ItemGrid.initObj();
        ItemGrid.initEvent();
        RightEquipmentGrid.initObj();
        RightCheckItemGroupGrid.initObj();
    };

    var checkItemSettings = function () {
    };
    checkItemSettings.init = init;
    global.checkItemSettings = checkItemSettings || {};
}(this);