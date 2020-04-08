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
                    text: "已关联检验设备",
                    header: true,
                    collapsed_text: "已关联检验设备",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true],
                    width:400,
                    height:300
                },
                {
                    id: "c",
                    text: "已关联检验项目组合",
                    header: true,
                    collapsed_text: "已关联检验项目组合",   // 折叠栏标题
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
            RightEquipmentGrid.obj.setHeader("编号,已关联检验设备", null,
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
            RightCheckItemGroupGrid.obj.setHeader("编号,已关联检验项目组合", null,
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
            ItemDetailWindow.obj = windowFactory.createWindow("ItemDetailWindow", 0, 0, 1200, 670);   //(id, left, top, width, height)
            ItemDetailWindow.obj.setText("检验项目详细信息");  //标题
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
            ItemDetailWindow.EquipmentGrid.initObj();
            ItemDetailWindow.EquipmentGrid.initEvent();
            ItemDetailWindow.EquipmentGrid.loadData(rowData);
            ItemDetailWindow.SearchCheckItemGroupToolbar.initobj();
            ItemDetailWindow.SearchCheckItemGroupToolbar.initEvent();
            ItemDetailWindow.CheckItemGroupGrid.initObj();
            ItemDetailWindow.CheckItemGroupGrid.initEvent();
            ItemDetailWindow.CheckItemGroupGrid.loadData(rowData);
        }
    };

    //详细信息窗口布局设置
    ItemDetailWindow.Layout = {
        obj: null,

        config: {
            pattern: "3W",
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
                    fix_size: [true, true],
                    width:400
                },
                {
                    id: "b",
                    text: "检验设备",
                    collapsed_text: "检验设备",
                    header: true,
                    collapse: false,
                    fix_size: [true, true]
                },
                {
                    id: "c",
                    text: "检验项目组合",
                    collapsed_text: "检验项目组合",
                    header: true,
                    collapse: false,
                    fix_size: [true, true],
                    width:450
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
            {type: "settings", position: "label-left", blockOffset: 0, offsetLeft: 30, offsetTop: 5},
            {
                type: "block", list: [
                    {type: "input", name: "itemName", label: "检&nbsp&nbsp验&nbsp&nbsp项&nbsp&nbsp目", value: "", inputWidth: 180, maxLength: 15}
                ]
            },
            {
                type: "block", list: [
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
                    {type: "input", name: "englishAbbreviation", label: "英&nbsp&nbsp文&nbsp&nbsp缩&nbsp&nbsp写", inputWidth: 180, maxLength: 15}
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "combo", id: "itemType", name: "itemType", label: "项&nbsp&nbsp目&nbsp&nbsp类&nbsp&nbsp型", inputWidth: 180,
                        options: [
                            {text: "", value: ""},
                            {text: "常规项目", value: "常规项目"},
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
                    {type: "input", name: "unit", label: "计&nbsp&nbsp量&nbsp&nbsp单&nbsp&nbsp位", inputWidth: 180, maxLength: 10}
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input",
                        name: "referenceValue",
                        label: "参&nbsp&nbsp&nbsp&nbsp考&nbsp&nbsp&nbsp&nbsp&nbsp值",
                        inputWidth: 180,
                        maxLength: 15
                    }
                ]
            },
            {
                type: "block", list: [
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
                        type: "input",
                        rows:3,
                        name: "equipmentNameList",
                        label: "已关联检验设备",
                        position: "label-top",
                        inputWidth: 270,
                        readonly: true,
                        style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input",
                        rows:4,
                        name: "CheckItemGroupNameList",
                        label: "已关联检验项目组合",
                        position: "label-top",
                        inputWidth: 270,
                        readonly: true,
                        style: "background:#eaeaea",
                        offsetTop:2
                    }
                ]
            },
            {
                type: "block", list: [
                    {type: "button", name: "itemDetailSaveBtn", value: "保存", offsetLeft: 60},
                    {type: "newcolumn"},
                    {type: "button", name: "itemDetailCancelBtn", value: "取消", offsetLeft: 40}
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
            var equipmentIdList = dhtmlxUtils.getCheckedRowIds(ItemDetailWindow.EquipmentGrid.obj);
            formData.equipmentIdList = equipmentIdList;
            var checkItemGroupIdList = dhtmlxUtils.getCheckedRowIds(ItemDetailWindow.CheckItemGroupGrid.obj);
            formData.checkItemGroupIdList = checkItemGroupIdList;
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
    
    //详细信息窗口检验设备表格
    ItemDetailWindow.EquipmentGrid = {
        obj:null,
        initObj: function () {
            ItemDetailWindow.EquipmentGrid.obj = ItemDetailWindow.Layout.obj.cells("b").attachGrid();
            ItemDetailWindow.EquipmentGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ItemDetailWindow.EquipmentGrid.obj.setHeader("选择,编号,检验设备", null,
                ["text-align:center;","text-align:center;", "text-align:center;"]);  //设置标题内容居中
            ItemDetailWindow.EquipmentGrid.obj.setColumnIds("ch,itemId,itemName");
            ItemDetailWindow.EquipmentGrid.obj.setColAlign("center,center,center");   //设置列中数据居中
            ItemDetailWindow.EquipmentGrid.obj.setInitWidths("50,100,*");          //列宽
            ItemDetailWindow.EquipmentGrid.obj.setColTypes("ch,ro,ro");
            ItemDetailWindow.EquipmentGrid.obj.init();
        },
        initEvent:function(){
            ItemDetailWindow.EquipmentGrid.obj.attachEvent("onRowSelect", function (id, ind) {
                //自动勾选上本行或取消勾选
                var flag = ItemDetailWindow.EquipmentGrid.obj.cells(id, 0).getValue() === '1' ? 0 : 1;
                ItemDetailWindow.EquipmentGrid.obj.cells(id, 0).setValue(flag);
                ItemDetailWindow.EquipmentGrid.setEquipmentList();
            });
            ItemDetailWindow.EquipmentGrid.obj.attachEvent("onCheck", function (rId, cInd, state) {
                ItemDetailWindow.EquipmentGrid.obj.selectRowById(rId, true, true, false);
                ItemDetailWindow.EquipmentGrid.setEquipmentList();
            });
        },
        //设置左侧表单已关联检验设备
        setEquipmentList:function(){
            var rowDataList = dhtmlxUtils.getCheckedRowBindingDatas(ItemDetailWindow.EquipmentGrid.obj);
            var equipmentName = "";
            for (var i = 0; i < rowDataList.length; i++) {
                if (i === rowDataList.length -1 ){
                    equipmentName = equipmentName + rowDataList[i].itemName
                } else {
                    equipmentName = equipmentName + rowDataList[i].itemName + ', '
                }
            }
            ItemDetailWindow.Form.obj.setItemValue("equipmentNameList",equipmentName);
        },
        loadData: function (rowData) {
            //查找所有的检验设备
            ajaxUtils.get('checkItemSettings/getAllEquipment.json', {
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(ItemDetailWindow.EquipmentGrid.obj, data, "itemId");  //删除所有行，加载数据
                ItemDetailWindow.EquipmentGrid.obj.sortRows(1, "int", "asc");
                if (JSUtils.isNotEmpty(rowData)){
                    ItemDetailWindow.EquipmentGrid.selectEquipment();
                }
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        //更新检验项目时勾选上所有初始时的检验设备
        selectEquipment:function () {
            var allRowIds = RightEquipmentGrid.obj.getAllRowIds();
            var allRowIdArr = new Array();
            allRowIdArr = allRowIds.split(",");
            for (var i = 0; i < allRowIdArr.length; i++) {
                ItemDetailWindow.EquipmentGrid.obj.cells(allRowIdArr[i],0).setValue(1);
            }
            ItemDetailWindow.EquipmentGrid.setEquipmentList();
        }  
    };

    //详细信息窗口快速查询检验项目
    ItemDetailWindow.SearchCheckItemGroupToolbar = {
        obj: null,
        config: {
            align: "left",
            icon_path: "images/",
            items: [
                {id: "inputValue", type: "buttonInput", text: "",width:200},
                {id: "sep1", type: "separator"},
                {
                    id: "searchButton",
                    type: "button",
                    text: "<span style=\"font-weight: bold;font-size: 12px\">快速定位</span>",
                    img: "search.png"
                }
            ]
        },
        initobj: function () {
            ItemDetailWindow.SearchCheckItemGroupToolbar.obj = ItemDetailWindow.Layout.obj.cells("c").attachToolbar(ItemDetailWindow.SearchCheckItemGroupToolbar.config);
        },
        initEvent: function () {
            //按回车时快速查询
            ItemDetailWindow.SearchCheckItemGroupToolbar.obj.attachEvent("onEnter", function () {
                ItemDetailWindow.SearchCheckItemGroupToolbar.searchButtonEvent();
            });
            ItemDetailWindow.SearchCheckItemGroupToolbar.obj.attachEvent("onClick", function (name) {
                switch (name) {
                    case "searchButton":
                        ItemDetailWindow.SearchCheckItemGroupToolbar.searchButtonEvent();
                        break;
                    default:
                }
            });
        },
        //快速查询对应的检验项目
        searchButtonEvent: function () {
            var inputValue = ItemDetailWindow.SearchCheckItemGroupToolbar.obj.getValue("inputValue");
            if (JSUtils.isEmpty(inputValue)) {
                dhtmlxAlert.alertMsg("请输入检验项目组合编号或者名称");
                return;
            }
            ajaxUtils.get('checkItemSettings/getCheckItemGroupId.json', {
                inputValue:inputValue
            }).then(function (data) {
                if (JSUtils.isEmpty(data)){
                    dhtmlxAlert.alertMsg("未查询到相应的检验项目组合，请检查后重新查询");
                    return;
                }
                if(JSUtils.isNotNum(data)){
                    dhtmlxAlert.alertWarningMsg("查询到结果值类型不对，请联系系统管理员维护");
                    return;
                }
                ItemDetailWindow.CheckItemGroupGrid.obj.selectRowById(data);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };
    
    //详细信息窗口检验项目组合表格
    ItemDetailWindow.CheckItemGroupGrid = {
        obj:null,
        initObj: function () {
            ItemDetailWindow.CheckItemGroupGrid.obj = ItemDetailWindow.Layout.obj.cells("c").attachGrid();
            ItemDetailWindow.CheckItemGroupGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ItemDetailWindow.CheckItemGroupGrid.obj.setHeader("选择,编号,检验项目组合", null,
                ["text-align:center;","text-align:center;", "text-align:center;"]);  //设置标题内容居中
            ItemDetailWindow.CheckItemGroupGrid.obj.setColumnIds("ch,groupId,groupName");
            ItemDetailWindow.CheckItemGroupGrid.obj.setColAlign("center,center,center");   //设置列中数据居中
            ItemDetailWindow.CheckItemGroupGrid.obj.setInitWidths("50,100,*");          //列宽
            ItemDetailWindow.CheckItemGroupGrid.obj.setColTypes("ch,ro,ro");
            ItemDetailWindow.CheckItemGroupGrid.obj.init();
        },
        initEvent:function(){
            ItemDetailWindow.CheckItemGroupGrid.obj.attachEvent("onRowSelect", function (id, ind) {
                //自动勾选上本行或取消勾选
                var flag = ItemDetailWindow.CheckItemGroupGrid.obj.cells(id, 0).getValue() === '1' ? 0 : 1;
                ItemDetailWindow.CheckItemGroupGrid.obj.cells(id, 0).setValue(flag);
                ItemDetailWindow.CheckItemGroupGrid.setCheckItemGroupList();
            });
            ItemDetailWindow.CheckItemGroupGrid.obj.attachEvent("onCheck", function (rId, cInd, state) {
                ItemDetailWindow.CheckItemGroupGrid.obj.selectRowById(rId, true, true, false);
                ItemDetailWindow.CheckItemGroupGrid.setCheckItemGroupList();
            });
        },
        //设置左侧表单已关联检验项目组合
        setCheckItemGroupList:function(){
            var rowDataList = dhtmlxUtils.getCheckedRowBindingDatas(ItemDetailWindow.CheckItemGroupGrid.obj);
            var checkItemGroupName = "";
            for (var i = 0; i < rowDataList.length; i++) {
                if (i === rowDataList.length -1 ){
                    checkItemGroupName = checkItemGroupName + rowDataList[i].groupName
                } else {
                    checkItemGroupName = checkItemGroupName + rowDataList[i].groupName + ', '
                }
            }
            ItemDetailWindow.Form.obj.setItemValue("CheckItemGroupNameList",checkItemGroupName);
        },
        loadData: function (rowData) {
            //查找所有的检验项目组合
            ajaxUtils.get('checkItemSettings/getAllCheckItemGroup.json', {
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(ItemDetailWindow.CheckItemGroupGrid.obj, data, "groupId");  //删除所有行，加载数据
                ItemDetailWindow.CheckItemGroupGrid.obj.sortRows(1, "int", "asc");
                if (JSUtils.isNotEmpty(rowData)){
                    ItemDetailWindow.CheckItemGroupGrid.selectCheckItemGroup();
                }
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        //更新检验项目时勾选上所有初始时的检验项目组合
        selectCheckItemGroup:function () {
            var allRowIds = RightCheckItemGroupGrid.obj.getAllRowIds();
            var allRowIdArr = new Array();
            allRowIdArr = allRowIds.split(",");
            for (var i = 0; i < allRowIdArr.length; i++) {
                ItemDetailWindow.CheckItemGroupGrid.obj.cells(allRowIdArr[i],0).setValue(1);
            }
            ItemDetailWindow.CheckItemGroupGrid.setCheckItemGroupList();
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