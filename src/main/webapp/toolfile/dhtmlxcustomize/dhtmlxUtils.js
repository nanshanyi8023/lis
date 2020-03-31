!function (global) {
    'use strict';
    /**
     * 获取 dhtmlxGrid 中选中行附带的 json对象
     */
    var getSelectedRowBindingData = function (dhtmlxGridObj) {
        var rowId = dhtmlxGridObj.getSelectedRowId();
        if (!rowId) {
            return null;
        }
        return getRowBindingData(dhtmlxGridObj, rowId);
    };

    /**
     * 获取 dhtmlxGrid 中指定行附带的 json对象
     * （同时会把用户在表格中修改的数据，更新到当前行的UserData）
     */
    var getRowBindingData = function (dhtmlxGridObj, rowId) {
        var _rowUserData = dhtmlxGridObj.getUserData(rowId, "_rowData");
        if (_rowUserData === "") {
            _rowUserData = {};
            dhtmlxGridObj.setUserData(rowId, "_rowData", _rowUserData);
        }
        var _rowData = dhtmlxGridObj.getRowData(rowId);
        $.extend(true, _rowUserData, _rowData); //传递的是对象的引用，所以在合并对象的同时，也修改了原_rowUserData对象
        return $.extend(true, {}, _rowUserData); //返回对象的拷贝而非引用，防止引用泄漏可能引起的隐患
    };

    /**
     * 获取 dhtmlxGrid 中 checkbox 所有被勾选上行的 rowId
     */
    var getCheckedRowIds =  function (dhtmlxGridObj, checkboxIndex) {
        var _checkboxIndex = 0;
        if (checkboxIndex && $.isNumeric(checkboxIndex)) {
            _checkboxIndex = checkboxIndex;
        }
        var rowIdList = dhtmlxGridObj.getCheckedRows(_checkboxIndex).split(",");
        if (rowIdList.length === 0 || rowIdList[0] === "") {
            return [];
        } else {
            return rowIdList;
        }
    };

    /**
     * 获取 dhtmlxGrid 中 checkbox 所有被勾选上行的附带的 json对象
     */
    var getCheckedRowBindingDatas = function (dhtmlxGridObj, checkboxIndex) {
        var rowIds = this.getCheckedRowIds(dhtmlxGridObj, checkboxIndex);
        if (rowIds.length === 0) {
            return [];
        }
        var rowBindingDatas = [];
        rowIds.forEach(function (e, index) {
            rowBindingDatas.push(dhxUtil.xGrid.getRowBindingData(dhtmlxGridObj, e))
        });
        return rowBindingDatas;
    };

    var clearAndLoadJsonListData = function (dhtmlxGridObj, jsonList, rowIdAttrName, formatCallback) {
        dhtmlxGridObj.clearAll();
        loadJsonListData(dhtmlxGridObj, jsonList, rowIdAttrName, formatCallback);
    };

    /**
     * 按json数组渲染 dhtmlxGrid，需配合 dhtmlxGrid 的 setColumnIds 方法来使用
     * @param dhtmlxGridObj -- dhtmlxGridObj对象
     * @param jsonList -- 需要渲染的json数组
     * @param rowIdAttrName -- json对象中充当rowId的属性的key名
     * @param formatCallback (可选参数)-- 回调函数，可按需修改json对象的值
     */
    var loadJsonListData = function (dhtmlxGridObj, jsonList, rowIdAttrName, formatCallback) {
        var needFormated = false;
        if (formatCallback && $.isFunction(formatCallback)) {
            needFormated = true;
        }

        for (var i = 0; i < jsonList.length; i++) {
            var rowId = jsonList[i][rowIdAttrName];
            var rowData = jsonList[i];

            if (needFormated) {
                rowData = formatCallback(rowData);
            }

            dhtmlxGridObj.addRow(rowId, "");
            dhtmlxGridObj.setRowData(rowId, rowData);
            dhtmlxGridObj.setUserData(rowId, "_rowData", rowData);
        }
        dhtmlxGridObj.setUserData("", "_tableData", jsonList);
    };

    var dhtmlxUtils = function(){
    };
    dhtmlxUtils.getSelectedRowBindingData = getSelectedRowBindingData;
    dhtmlxUtils.getCheckedRowIds = getCheckedRowIds;
    dhtmlxUtils.getCheckedRowBindingDatas = getCheckedRowBindingDatas;
    dhtmlxUtils.clearAndLoadJsonListData = clearAndLoadJsonListData;

    global.dhtmlxUtils = dhtmlxUtils;
}(this);