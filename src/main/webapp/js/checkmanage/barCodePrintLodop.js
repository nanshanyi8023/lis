//打印条码lodop实现
var printBarCode = function (barCodeData) {
    //初始化LODOP
    var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
    LODOP.PRINT_INIT("打印检验条码");
    LODOP.PRINT_INITA(1, 13, "48mm", "35mm", "检验申请条码");

    LODOP.SET_PRINT_PAGESIZE(1, 500, 282, "");
    //设置打印预览窗口大小
    LODOP.SET_PREVIEW_WINDOW(1,0,0,600,400,"");

    for (var i = 0; i < barCodeData.length; i++) {
        //设置是否显示“加急”
        LODOP.ADD_PRINT_TEXT(15, 40, 60, 23, (barCodeData[i].isEmergency === "1" ? "(加急)" : ""));    //(Top,Left,Width,Height,strContent)
        //设置条码号，显示条码数字
        LODOP.ADD_PRINT_BARCODE(30, 13, 128, 46, "128A", barCodeData[i].barcodeNumber);
        //设置患者姓名、样本类型、采集容器、送检科室
        LODOP.SET_PRINT_STYLE("FontSize", 6);
        LODOP.ADD_PRINT_TEXT(28, 123, 100, 20, barCodeData[i].patientName);
        LODOP.ADD_PRINT_TEXT(38, 123, 100, 20, barCodeData[i].sampleType);
        LODOP.ADD_PRINT_TEXT(48, 123, 100, 20, barCodeData[i].collectionContainer);
        LODOP.ADD_PRINT_TEXT(58, 123, 100, 20, barCodeData[i].submitDepartment);
        //判断是否需要分页
        if (i < barCodeData.length-1) {
            LODOP.NewPage();
        }
    }
    //打印预览
    LODOP.PREVIEW();
    //LODOP.PRINT_DESIGN();
    //LODOP.PRINT();
};