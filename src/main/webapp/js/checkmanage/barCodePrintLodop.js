//打印条码lodop实现
var printBarCode = function (barCodeData) {
    var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
    LODOP.PRINT_INIT("打印检验条码");
    LODOP.PRINT_INITA(1, 13, "48mm", "35mm", "检验申请条码");
    LODOP.SET_PRINT_PAGESIZE(1, 500, 282, "");

    for (var i = 0; i < barCodeData.length; i++) {
        LODOP.ADD_PRINT_TEXT(15, 40, 60, 23, (barCodeData[i].isEmergency === "1" ? "(加急)" : ""));    //(Top,Left,Width,Height,strContent)
        LODOP.ADD_PRINT_BARCODE(30, 13, 128, 46, "128A", barCodeData[i].barcodeNumber);

        LODOP.SET_PRINT_STYLE("FontSize", 6);
        LODOP.ADD_PRINT_TEXT(28, 123, 100, 20, barCodeData[i].patientName);
        LODOP.ADD_PRINT_TEXT(38, 123, 100, 20, barCodeData[i].sampleType);
        LODOP.ADD_PRINT_TEXT(48, 123, 100, 20, barCodeData[i].collectionContainer);
        LODOP.ADD_PRINT_TEXT(58, 123, 100, 20, barCodeData[i].submitDepartment);

        //LODOP.PRINT_DESIGN();
        //LODOP.PREVIEW();
        LODOP.PRINT();
    }
};