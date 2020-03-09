// 工作组设置页面
!function (global) {
    'use strict';
    //页面总布局
    var Layout = {
        obj: null,

        config: {
            parent: "RightLayoutObj",
            pattern: "2E",
            offsets: {
                top: 2,
                right: 2,
                bottom: 2,
                left: 2
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
            Layout.obj = new dhtmlXLayoutObject(Layout.config);
        }
    };

    //顶部单元格内容
    var ItemOperationForm = {
        obj: null,

        config: [
            {type: "input", name: "workGroup", label: "工作组：", width: 150, offsetLeft: 10, offsetTop: 12, maxLength: 20},
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
                        dhtmlxAlert.confirmWarningMsg("是否确认删除?", function () {
                            ItemOperationForm.itemDeleteBtnEvent(itemIdList);
                        });
                        break;
                    default:
                }
            });
        },
        //查询功能()
        itemSearchBtnEvent: function () {
            var workGroup = ItemOperationForm.obj.getItemValue("workGroup");
            ItemGrid.loadData(workGroup);
        },
        //添加功能
        itemAddBtnEvent:function () {
            ItemDetailWindow.createObj();
        },
        //删除功能
        itemDeleteBtnEvent: function (itemIdList) {
            ajaxUtils.postBody('workGroupSettings/deleteWorkGroups.json',
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
            ItemGrid.obj.setHeader("选择,编号,工作组名称,组代码,组类型,样本类型,所属科室",null,
                ["text-align:center;","text-align:center;","text-align:center","text-align:center","text-align:center","text-align:center","text-align:center"]);  //设置标题内容居中
            ItemGrid.obj.setColumnIds("ch,workGroupId,workGroupName,groupCode,groupType,sampleType,departmentName");
            ItemGrid.obj.setColAlign("center,center,center,center,center,center,center");   //设置列中数据居中
            ItemGrid.obj.setInitWidths("50,150,*,200,200,200,200");          //列宽
            ItemGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro,ro");
            ItemGrid.obj.enableSmartRendering(true);
            ItemGrid.obj.init();
        },
        initEvent: function () {
            ItemGrid.obj.attachEvent("onRowDblClicked",function () {
                var rowData = dhtmlxUtils.getSelectedRowBindingData(ItemGrid.obj);
                ItemDetailWindow.createObj(rowData);
            });
        },
        loadData: function (workGroup) {
            ajaxUtils.get('workGroupSettings/getworkGroups.json', {
                workGroup:workGroup
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
                    {type: "input", name: "workGroupName", label: "工作组", value: "", inputWidth: 180,maxLength:15},
                    {type: "newcolumn"},
                    {type: "input", name: "workGroupId", label: "工作组编号", value: "",inputWidth: 180,readonly: true,style:"background:#eaeaea"  }
                ]
            },
            {
                type: "block", list: [
                    {type: "input", name:"groupCode",label: "组代码", inputWidth: 180,maxLength:15},
                    {type: "newcolumn"},
                    {type: "input", name:"groupType",label: "组类型", inputWidth: 180,maxLength:10}
                ]
            },
            {
                type: "block", list: [
                    {type: "input", name:"sampleType",label: "样本类型", inputWidth: 180,maxLength:15},
                    {type: "newcolumn"},
                    {type: "input", name:"departmentName",label: "所属科室", inputWidth: 180,maxLength:10}
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
            if (!formData.workGroupName) {
                dhtmlxAlert.alertWarningMsg("工作组名称不可为空");
                return;
            }
            ajaxUtils.postBody('workGroupSettings/saveWorkGroup.json',
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

    var workGroupSettings = function(){};
    workGroupSettings.init = init;
    global.workGroupSettings = workGroupSettings||{};
}(this);