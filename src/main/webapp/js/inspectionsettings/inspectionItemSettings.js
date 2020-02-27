// 检验项目设置页面
!function (global) {
    'use strict';
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
                    collapsed_text: "collapsed_textA",   // 折叠栏标题
                    collapse: false,       // 初始是否折叠
                    fix_size: [true, true],
                    height:100
                },
                {
                    id: "b",
                    text: "textB",
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
            {type: "input", name: "iteminput", label: "检验项目：", width: 150, offsetLeft: 10, offsetTop: 12, maxLength: 15},
            {type: "newcolumn"},
            {type: "button", name: "itemSearchBtn", value: "查询", offsetLeft: 20},
            {type: "newcolumn"},
            {type: "button", name: "itemAddBtn", value: "新增", offsetLeft: 10},
            {type: "newcolumn"},
            {type: "button", name: "itemDeleteBtn", value: "删除", offsetLeft: 10}
        ],

        initObj: function () {
            ItemOperationForm.obj = Layout.obj.cells("a").attachForm(ItemOperationForm.config);
        },

        initEvent:function () {
            ItemOperationForm.obj.attachEvent("onEnter",function () {
                ItemOperationForm.itemSearchBtnEvent();
            });
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
                        dhtmlxUtils.confirmWarningMsg("是否确认删除?", function () {
                            ItemOperationForm.itemDeleteBtnEvent(itemIdList);
                            //重新加载表格

                        });
                        break;
                    default:
                }
            });
        },
        //查询功能(支持项目编号，项目名称，英文缩写查询)
        itemSearchBtnEvent: function () {
            ItemGrid.loadData(ItemOperationForm.obj.getItemValue("iteminput"));
        },
        //添加功能
        itemAddBtnEvent:function () {
            ItemDetailWindow.createObj();
        },
        //删除功能
        itemDeleteBtnEvent: function (itemIdList) {
            ajaxUtils.postBody('.json',
                itemIdList
            ).then(function (data) {

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
            ItemGrid.obj.setHeader("选择,编号,项目名称,英文缩写,计量单位,项目类型,拼音助记码,五笔助记码",null,
                ["text-align:center;","text-align:center;","text-align:center","text-align:center","text-align:center","text-align:center"]);  //设置标题内容居中
            ItemGrid.obj.setColumnIds("ch,itemId,itemName,englishAbbreviations,unit,itemType,pySpell,wbSpell");
            ItemGrid.obj.setColAlign("center,center,center,center,center,center");   //设置列中数据居中
            ItemGrid.obj.setInitWidths("50,150,250,*,150,100");          //列宽
            ItemGrid.obj.setColTypes("ch,ro,ro,ro,ro,ro");
            ItemGrid.obj.setColHidden(6,true);
            ItemGrid.obj.setColHidden(7,true);
            ItemGrid.obj.init();
        },
        initEvent: function () {
            ItemGrid.obj.attachEvent("onRowDblClicked",function () {
                var rowData = dhtmlxUtils.getSelectedRowBindingData(ItemGrid.obj);
                ItemDetailWindow.createObj(rowData);
            });
        },
        loadData: function (inputValue) {
            ajaxUtils.get('  .json', {
                inputValue:inputValue
            }).then(function (data) {
                dhtmlxUtils.clearAndLoadJsonListData(ItemGrid.obj, data, "itemid");  //删除所有行，加载数据
                ItemGrid.obj.sortRows(1,"int","asc");
            }).catch(function (reason) {
                dhtmlxAlert.alertErrorMsg(reason);
            }).finally(function () {
            });
        }
    };



    var init = function () {
        Layout.initObj();
        ItemOperationForm.initObj();
        ItemOperationForm.initEvent();
        ItemGrid.initObj();
        ItemGrid.initEvent();
    };

    var inspectionItemSettings = function(){};
    inspectionItemSettings.init = init;
    global.inspectionItemSettings = inspectionItemSettings||{};
}(this);