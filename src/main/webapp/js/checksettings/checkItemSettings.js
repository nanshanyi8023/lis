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

    //顶部单元格内容
    var ItemOperationForm = {
        obj: null,

        config: [
            {type: "combo", name: "workGroup", label: "工作组：", width: 150, offsetLeft: 10, offsetTop: 12, maxLength: 20},
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
            //初始化工作组下拉框
            ItemOperationForm.getAllWorkGroup();
            //加载表格
            ItemOperationForm.itemSearchBtnEvent();
        },
        //查找所有工作组
        getAllWorkGroup: function () {
            ajaxUtils.get('checkItemSettings/getAllWorkGroup.json'
            ).then(function (data) {
                //初始化工作组下拉框
                var workGroupCombo = ItemOperationForm.obj.getCombo("workGroup");
                workGroupCombo.clearAll();
                var options = data.map(function (e, index, array) {
                    return [e.workGroupId, e.workGroupName];
                });
                workGroupCombo.addOption(options);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        //查找所有检验项目组合
        getAllCheckItemGroup: function (workGroupId) {
            ajaxUtils.get('checkItemSettings/getAllCheckItemGroup.json', {
                workGroupId: workGroupId
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
                if (name == 'workGroup') {
                    var workGroupId = ItemOperationForm.obj.getCombo("workGroup").getSelectedValue();
                    if (workGroupId !== null && workGroupId !== "") {
                        ItemOperationForm.getAllCheckItemGroup(workGroupId);
                    }
                }
            });
            //按回车时搜索
            ItemOperationForm.obj.attachEvent("onEnter", function () {
                ItemOperationForm.itemSearchBtnEvent();
            });
            ItemOperationForm.obj.getCombo("workGroup").attachEvent("onKeyPressed", function (keyCode) {
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
                        dhtmlxAlert.confirmWarningMsg("是否确认删除?", function () {
                            ItemOperationForm.itemDeleteBtnEvent(itemIdList);
                        });
                        break;
                    default:
                }
            });
        },
        //查询功能(支持项目编号，项目名称，英文缩写查询)
        itemSearchBtnEvent: function () {
            var workGroupId = ItemOperationForm.obj.getCombo("workGroup").getSelectedValue();
            var checkItemGroupId = ItemOperationForm.obj.getCombo("checkItemGroup").getSelectedValue();
            var checkItem = ItemOperationForm.obj.getItemValue("checkItem");
            ItemGrid.loadData(workGroupId, checkItemGroupId, checkItem);
        },
        //清空查询功能
        clearSearchBtnEvent: function () {
            ItemOperationForm.obj.clear();
            ItemOperationForm.obj.getCombo("workGroup").unSelectOption();
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
            ItemGrid.obj.setHeader("选择,编号,项目名称,英文缩写,计量单位,项目类型,参考值,默认值,所属工作组,所属检验项目组合", null,
                ["text-align:center;", "text-align:center;", "text-align:center", "text-align:center", "text-align:center", "text-align:center", "text-align:center", "text-align:center"]);  //设置标题内容居中
            ItemGrid.obj.setColumnIds("ch,itemId,itemName,englishAbbreviation,unit,itemType,referenceValue,defaultValue,workGroup,checkItemGroup");
            ItemGrid.obj.setColAlign("center,center,center,center,center,center,center,center");   //设置列中数据居中
            ItemGrid.obj.setInitWidths("50,150,*,150,150,150,150,150,0,0");          //列宽
            ItemGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro,ro,ro,ro,ro");
            ItemGrid.obj.setColumnHidden(8, true);
            ItemGrid.obj.setColumnHidden(9, true);
            ItemGrid.obj.enableSmartRendering(30);
            ItemGrid.obj.init();

            //底部分页栏
            Layout.obj.cells("b").attachStatusBar({
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
        },
        loadData: function (workGroupId, checkItemGroupId, checkItem) {
            ajaxUtils.get('checkItemSettings/getCheckItems.json', {
                workGroupId: workGroupId,
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

    //新增以及查看详细信息窗口
    var ItemDetailWindow = {
        obj: null,
        createObj: function (rowData) {
            var windowFactory = new dhtmlXWindows();
            ItemDetailWindow.obj = windowFactory.createWindow("ItemDetailWindow", 0, 0, 700, 400);   //(id, left, top, width, height)
            ItemDetailWindow.obj.setText("项目详情");  //标题
            ItemDetailWindow.obj.denyResize();  //拒绝调整大小
            //ItemDetailWindow.obj.denyMove();    //拒绝窗口移动
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
        ItemOperationForm.initObj();
        ItemOperationForm.initEvent();
        ItemGrid.initObj();
        ItemGrid.initEvent();
    };

    var checkItemSettings = function () {
    };
    checkItemSettings.init = init;
    global.checkItemSettings = checkItemSettings || {};
}(this);