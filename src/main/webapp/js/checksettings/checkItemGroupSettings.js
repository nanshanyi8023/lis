// 检验项目组合设置页面
!function (global) {
    'use strict';
    //页面总布局
    var Layout = {
        obj: null,

        config: {
            pattern: "3T",
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
                    text: "&nbsp;&nbsp;检验项目组合",
                    header: true,
                    collapsed_text: "检验项目组合",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true]
                },
                {
                    id: "c",
                    text: "&nbsp;&nbsp所含检验项目",
                    header: true,
                    collapsed_text: "所含检验项目",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true],
                    width:450
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
            {type: "combo", name: "equipment", label: "所属检验设备：", width: 150, offsetLeft: 10, offsetTop: 12, maxLength: 20},
            {type: "newcolumn"},
            {
                type: "input",
                name: "checkItemGroup",
                label: "检验项目组合：",
                width: 200,
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
        //查找所有检验设备
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
        initEvent: function () {
            //按回车时搜索
            ItemOperationForm.obj.attachEvent("onEnter", function () {
                ItemOperationForm.itemSearchBtnEvent();
            });
            ItemOperationForm.obj.getCombo("equipment").attachEvent("onKeyPressed", function (keyCode) {
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
                        dhtmlxAlert.confirmWarningMsg("删除检验项目组合时，同时会删除检验项目组合——检验项目之间的关联，请确认是否删除?", function () {
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
            var checkItemGroup = ItemOperationForm.obj.getItemValue("checkItemGroup");
            ItemGrid.loadData(equipmentId, checkItemGroup);
        },
        //清空查询功能
        clearSearchBtnEvent: function () {
            ItemOperationForm.obj.clear();
            ItemOperationForm.obj.getCombo("equipment").unSelectOption();
            ItemGrid.loadData();
        },
        //添加功能
        itemAddBtnEvent: function () {
            ItemDetailWindow.createObj();
        },
        //删除功能
        itemDeleteBtnEvent: function (itemIdList) {
            ajaxUtils.postBody('checkItemGroupSettings/deleteCheckItemGroups.json',
                itemIdList
            ).then(function (data) {
                //重新加载表格
                ItemOperationForm.itemSearchBtnEvent();
                RightCheckItemGrid.obj.clearAll();
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //检验项目组合列表
    var ItemGrid = {
        obj: null,
        initObj: function () {
            ItemGrid.obj = Layout.obj.cells("b").attachGrid();
            ItemGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ItemGrid.obj.setHeader("选择,编号,检验项目组合名称,所属检验设备,样本类型", null,
                ["text-align:center;", "text-align:center;", "text-align:center", "text-align:center", "text-align:center"]);  //设置标题内容居中
            ItemGrid.obj.setColumnIds("ch,groupId,groupName,equipmentName,sampleType");
            ItemGrid.obj.setColAlign("center,center,center,center,center");   //设置列中数据居中
            ItemGrid.obj.setInitWidths("50,150,*,200,200");          //列宽
            ItemGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro");
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
            //单击选中行时查找对应的检验项目
            ItemGrid.obj.attachEvent("onRowSelect", function(id,ind){
                RightCheckItemGrid.loadData(id);
            });

            ItemGrid.obj.attachEvent("onRowDblClicked", function () {
                var rowData = dhtmlxUtils.getSelectedRowBindingData(ItemGrid.obj);
                ItemDetailWindow.createObj(rowData);
            });
        },
        loadData: function (equipmentId, checkItemGroup) {
            ajaxUtils.get('checkItemGroupSettings/getcheckItemGroups.json', {
                equipmentId: equipmentId,
                checkItemGroup: checkItemGroup
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(ItemGrid.obj, data, "groupId");  //删除所有行，加载数据
                ItemGrid.obj.sortRows(4, "str", "asc");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //右侧检验项目组合所包含的检验项目展示表格
    var RightCheckItemGrid = {
        obj:null,
        initObj: function () {
            RightCheckItemGrid.obj = Layout.obj.cells("c").attachGrid();
            RightCheckItemGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            RightCheckItemGrid.obj.setHeader("编号,所含检验项目", null,
                ["text-align:center;", "text-align:center;"]);  //设置标题内容居中
            RightCheckItemGrid.obj.setColumnIds("itemId,itemName");
            RightCheckItemGrid.obj.setColAlign("center,center");   //设置列中数据居中
            RightCheckItemGrid.obj.setInitWidths("150,*");          //列宽
            RightCheckItemGrid.obj.setColTypes("ro,ro");
            RightCheckItemGrid.obj.init();
        },
        loadData: function (selectedRowId) {
            ajaxUtils.get('checkItemGroupSettings/getcheckItemNameList.json', {
                checkItemGroupId: selectedRowId
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(RightCheckItemGrid.obj, data, "itemId");  //删除所有行，加载数据
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
            ItemDetailWindow.obj = windowFactory.createWindow("ItemDetailWindow", 0, 0, 950, 650);   //(id, left, top, width, height)
            ItemDetailWindow.obj.setText("检验项目组合详细信息设置");  //标题
            ItemDetailWindow.obj.denyResize();  //拒绝调整大小
            ItemDetailWindow.obj.denyPark();
            ItemDetailWindow.obj.setModal(true);
            ItemDetailWindow.obj.centerOnScreen(); //窗口居中显示在屏幕中
            ItemDetailWindow.initWindow(rowData);
        },
        initWindow: function (rowData) {
            ItemDetailWindow.Layout.initObj();
            ItemDetailWindow.Form.initObj(rowData);
            ItemDetailWindow.Form.initEvent();
            ItemDetailWindow.SearchToolbar.initobj();
            ItemDetailWindow.SearchToolbar.initEvent();
            ItemDetailWindow.CheckItemGrid.initObj();
            ItemDetailWindow.CheckItemGrid.initEvent();
            ItemDetailWindow.CheckItemGrid.loadData();
        }
    };

    //详细信息窗口布局设置
    ItemDetailWindow.Layout = {
        obj: null,

        config: {
            pattern: "2U",
            offsets: {
                top: 5,
                right: 5,
                bottom: 5,
                left: 5
            },
            cells: [
                {
                    id: "a",
                    text: "检验项目组合详细信息",
                    collapsed_text: "检验项目组合详细信息",
                    header: false,
                    collapse: false,
                    fix_size: [true, true]
                },
                {
                    id: "b",
                    text: "所含检验项目",
                    collapsed_text: "所含检验项目",
                    header: false,
                    collapse: false,
                    fix_size: [true, true],
                    width: 500
                }
            ]
        },

        initObj: function () {
            ItemDetailWindow.Layout.obj = ItemDetailWindow.obj.attachLayout(ItemDetailWindow.Layout.config);
        }
    };

    //详细信息窗口左侧表单设置
    ItemDetailWindow.Form = {
        obj: null,
        config: [
            {type: "settings", position: "label-left", blockOffset: 0, offsetLeft: 25, offsetTop: 12},
            {
                type: "block", list: [
                    {
                        type: "input",
                        name: "groupName",
                        label: "检验项目组合",
                        value: "",
                        inputWidth: 230,
                        maxLength: 15
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "input",
                        name: "groupId",
                        label: "编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号",
                        value: "",
                        inputWidth: 230,
                        readonly: true,
                        style: "background:#eaeaea"
                    }
                ]
            },
            {
                type: "block", list: [
                    {
                        type: "combo",
                        name: "sampleType",
                        label: "样&nbsp;&nbsp;本&nbsp;&nbsp;类&nbsp;&nbsp;型",
                        inputWidth: 230,
                        maxLength: 15
                    }
                ]
            },
            {
                type: "block", list: [
                    {type: "combo", name: "equipmentId", label: "所属检验设备", inputWidth: 230}
                ]
            },
            {
                type: "block", list: [
                    {type: "input", rows:8,name: "checkItemNameList", label: "所含检验项目", inputWidth: 230,readonly: true,
                        style: "background:#eaeaea"}
                ]
            },
            {
                type: "block", list: [
                    {type: "button", name: "itemDetailSaveBtn", value: "保存", offsetLeft: 80, offsetTop: 30},
                    {type: "newcolumn"},
                    {type: "button", name: "itemDetailCancelBtn", value: "取消", offsetLeft: 40, offsetTop: 30}
                ]
            }
        ],
        initObj: function (rowData) {
            ItemDetailWindow.Form.obj = ItemDetailWindow.Layout.obj.cells("a").attachForm(ItemDetailWindow.Form.config);
            ItemDetailWindow.Form.getAllequipment(rowData);
        },
        //查找所有检验设备
        getAllequipment: function (rowData) {
            ajaxUtils.get('checkItemSettings/getAllEquipment.json'
            ).then(function (data) {
                //初始化检验设备下拉框
                var equipmentCombo = ItemDetailWindow.Form.obj.getCombo("equipmentId");
                var options = data.map(function (e, index, array) {
                    return [e.itemId, e.itemName];
                });
                equipmentCombo.addOption(options);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
                ItemDetailWindow.Form.getAllSampleType(rowData);
            });
        },
        //查找所有样品类型
        getAllSampleType: function (rowData) {
            ajaxUtils.get('checkItemGroupSettings/getAllSampleType.json'
            ).then(function (data) {
                //初始化样品类型下拉框
                var sampleTypeCombo = ItemDetailWindow.Form.obj.getCombo("sampleType");
                var options = data.map(function (e, index, array) {
                    return [e, e];
                });
                sampleTypeCombo.addOption(options);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
                //加载详细信息窗口表单数据
                ItemDetailWindow.Form.loadData(rowData);
            });
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
            if (!formData.groupName) {
                dhtmlxAlert.alertWarningMsg("检验项目组合名称不可为空");
                return;
            }
            if (!formData.equipmentId) {
                dhtmlxAlert.alertWarningMsg("所属检验设备不可为空");
                return;
            }
            var checkItemIdList = dhtmlxUtils.getCheckedRowIds(ItemDetailWindow.CheckItemGrid.obj);
            formData.checkItemIdList = checkItemIdList;
            ajaxUtils.postBody('checkItemGroupSettings/savecheckItemGroup.json',
                formData
            ).then(function (data) {
                ItemDetailWindow.obj.close();
                ItemOperationForm.itemSearchBtnEvent();
                RightCheckItemGrid.obj.clearAll();
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
            ItemDetailWindow.CheckItemGrid.selectCheckItem();
        }
    };

    //详细信息窗口快速查询检验项目
    ItemDetailWindow.SearchToolbar = {
        obj: null,
        config: {
            align: "left",
            icon_path: "images/",
            items: [
                {id: "inputValue", type: "buttonInput", text: "",width:150},
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
            ItemDetailWindow.SearchToolbar.obj = ItemDetailWindow.Layout.obj.cells("b").attachToolbar(ItemDetailWindow.SearchToolbar.config);
        },
        initEvent: function () {
            //按回车时快速查询
            ItemDetailWindow.SearchToolbar.obj.attachEvent("onEnter", function () {
                ItemDetailWindow.SearchToolbar.searchButtonEvent();
            });
            ItemDetailWindow.SearchToolbar.obj.attachEvent("onClick", function (name) {
                switch (name) {
                    case "searchButton":
                        ItemDetailWindow.SearchToolbar.searchButtonEvent();
                        break;
                    default:
                }
            });
        },
        //快速查询对应的检验项目
        searchButtonEvent: function () {
            var inputValue = ItemDetailWindow.SearchToolbar.obj.getValue("inputValue");
            if (JSUtils.isEmpty(inputValue)) {
                dhtmlxAlert.alertMsg("请输入检验项目编号或者名称或者英文缩写");
                return;
            }
            ajaxUtils.get('checkItemGroupSettings/getCheckItemId.json', {
                inputValue:inputValue
            }).then(function (data) {
                if (JSUtils.isEmpty(data)){
                    dhtmlxAlert.alertMsg("未查询到相应的检验项目，请检查后重新查询");
                    return;
                }
                if(JSUtils.isNotNum(data)){
                    dhtmlxAlert.alertWarningMsg("查询到结果值类型不对，请联系系统管理员维护");
                    return;
                }
                ItemDetailWindow.CheckItemGrid.obj.selectRowById(data);
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };

    //详细信息窗口右侧检验项目组合所包含的检验项目展示表格
    ItemDetailWindow.CheckItemGrid = {
        obj:null,
        initObj: function () {
            ItemDetailWindow.CheckItemGrid.obj = ItemDetailWindow.Layout.obj.cells("b").attachGrid();
            ItemDetailWindow.CheckItemGrid.obj.setImagePath("toolfile/dhtmlxstand/skins/skyblue/imgs/");     //选择框图片
            ItemDetailWindow.CheckItemGrid.obj.setHeader("选择,编号,所含检验项目", null,
                ["text-align:center;","text-align:center;", "text-align:center;"]);  //设置标题内容居中
            ItemDetailWindow.CheckItemGrid.obj.setColumnIds("ch,itemId,itemName");
            ItemDetailWindow.CheckItemGrid.obj.setColAlign("center,center,center");   //设置列中数据居中
            ItemDetailWindow.CheckItemGrid.obj.setInitWidths("50,150,*");          //列宽
            ItemDetailWindow.CheckItemGrid.obj.setColTypes("ch,ro,ro");
            ItemDetailWindow.CheckItemGrid.obj.init();
        },
        initEvent:function(){
            ItemDetailWindow.CheckItemGrid.obj.attachEvent("onRowSelect", function (id, ind) {
                //自动勾选上本行或取消勾选
                var flag = ItemDetailWindow.CheckItemGrid.obj.cells(id, 0).getValue() == '1' ? 0 : 1;
                ItemDetailWindow.CheckItemGrid.obj.cells(id, 0).setValue(flag);
                ItemDetailWindow.CheckItemGrid.setCheckItemNameList();
            });
            ItemDetailWindow.CheckItemGrid.obj.attachEvent("onCheck", function (rId, cInd, state) {
                ItemDetailWindow.CheckItemGrid.obj.selectRowById(rId, true, true, false);
                ItemDetailWindow.CheckItemGrid.setCheckItemNameList();
            });
        },
        //设置左侧表单所含检验项目
        setCheckItemNameList:function(){
            var rowDataList = dhtmlxUtils.getCheckedRowBindingDatas(ItemDetailWindow.CheckItemGrid.obj);
            var checkItemName = "";
            for (var i = 0; i < rowDataList.length; i++) {
                if (i === rowDataList.length -1 ){
                    checkItemName = checkItemName + rowDataList[i].itemName
                } else {
                    checkItemName = checkItemName + rowDataList[i].itemName + ', '
                }
            }
            ItemDetailWindow.Form.obj.setItemValue("checkItemNameList",checkItemName);
        },
        loadData: function () {
            //查找所有的检验项目
            ajaxUtils.get('checkItemSettings/getCheckItems.json', {
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(ItemDetailWindow.CheckItemGrid.obj, data, "itemId");  //删除所有行，加载数据
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        },
        //更新检验项目组合时勾选上所有初始时的检验项目
        selectCheckItem:function () {
            var allRowIds = RightCheckItemGrid.obj.getAllRowIds();
            var allRowIdArr = new Array();
            allRowIdArr = allRowIds.split(",");
            for (var i = 0; i < allRowIdArr.length; i++) {
                ItemDetailWindow.CheckItemGrid.obj.cells(allRowIdArr[i],0).setValue(1);
            }
            ItemDetailWindow.CheckItemGrid.setCheckItemNameList();
        }
    };

    var init = function () {
        Layout.initObj();
        ItemOperationForm.initObj();
        ItemOperationForm.initEvent();
        ItemGrid.initObj();
        ItemGrid.initEvent();
        RightCheckItemGrid.initObj();
    };

    var checkItemGroupSettings = function () {
    };
    checkItemGroupSettings.init = init;
    global.checkItemGroupSettings = checkItemGroupSettings || {};
}(this);