/**
 * 打印检验结果页面
 */
function printCheckResult(sampleInfo,checkResult) {
        //LODOP.NEWPAGE();
        LODOP.PRINT_INIT("");
        var normalFontSize = 10;
        LODOP.PRINT_INITA(0, 0, "210mm", "297mm", "");
        LODOP.SET_PRINT_PAGESIZE(3, 0, 0, "A4");
        //标题栏
        LODOP.SET_PRINT_STYLE("FontSize", 15);
        LODOP.SET_PRINT_STYLE("Bold", 1);
        LODOP.SET_PRINT_STYLE("Alignment", 2);
        LODOP.ADD_PRINT_TEXT(10, 42, 700, 100, sampleInfo.hosnName + '报告单');
        LODOP.SET_PRINT_STYLE("FontSize", 10);
        LODOP.SET_PRINT_STYLE("Bold", 0);
        LODOP.SET_PRINT_STYLE("Alignment", 3);
        LODOP.ADD_PRINT_TEXT(15, 42, 660, 100, '【条码号：' + sampleInfo.barcodeNumber + '】');

        //定义样本信息高度
        var line1_top = 45;
        var line2_top = 63;
        var line3_top = 81;
        //第一行 信息
        LODOP.SET_PRINT_STYLE("FontSize", normalFontSize);
        LODOP.SET_PRINT_STYLE("Bold", 0);
        LODOP.SET_PRINT_STYLE("Alignment", 1);
        LODOP.ADD_PRINT_TEXT(line1_top, 42, 100, 100, '姓  名：');
        LODOP.SET_PRINT_STYLE("FontSize", 12);
        LODOP.SET_PRINT_STYLE("Bold", 1);
        LODOP.ADD_PRINT_TEXT(line1_top, 100, 100, 100, sampleInfo.patientName);
        LODOP.SET_PRINT_STYLE("FontSize", normalFontSize);
        LODOP.SET_PRINT_STYLE("Bold", 0);
        LODOP.ADD_PRINT_TEXT(line1_top, 225, 100, 100, '就诊号：');
        LODOP.ADD_PRINT_TEXT(line1_top, 285, 100, 100, sampleInfo.patientId);
        LODOP.ADD_PRINT_TEXT(line1_top, 390, 100, 100, '性 别：');
        LODOP.ADD_PRINT_TEXT(line1_top, 440, 100, 100, sampleInfo.sex);
        LODOP.ADD_PRINT_TEXT(line1_top, 560, 100, 100, '年 龄:');
        LODOP.ADD_PRINT_TEXT(line1_top, 625, 100, 100, sampleInfo.age);

        //第二行
        LODOP.ADD_PRINT_TEXT(line2_top, 42, 100, 100, '检验仪器：');
        LODOP.ADD_PRINT_TEXT(line2_top, 100, 100, 100, sampleInfo.equipmentList);
        LODOP.ADD_PRINT_TEXT(line2_top, 225, 100, 100, '送检科室：');
        LODOP.ADD_PRINT_TEXT(line2_top, 285, 100, 100, sampleInfo.submitDepartment);
        LODOP.ADD_PRINT_TEXT(line2_top, 390, 100, 100, '样本类型：');
        LODOP.ADD_PRINT_TEXT(line2_top, 450, 100, 100, sampleInfo.sampleType);
        LODOP.ADD_PRINT_TEXT(line2_top, 560, 100, 100, '是否急诊：');
        LODOP.ADD_PRINT_TEXT(line2_top, 625, 100, 100, sampleInfo.isEmergency === "1" ? "急诊" : "非急诊");

        //第三行
        LODOP.ADD_PRINT_TEXT(line3_top, 42, 100, 100, '检验项目组合：');
        LODOP.ADD_PRINT_TEXT(line3_top, 130, 200, 100, sampleInfo.checkItemGroupNameList);

        LODOP.ADD_PRINT_LINE(120, 50, 120, 700, 0, 1);//中间线
        LODOP.ADD_PRINT_LINE(148, 50, 148, 700, 0, 1);//抗菌药物标题下线

        LODOP.ADD_PRINT_TEXT(126, 60, 100, 100, '序号');
        LODOP.ADD_PRINT_TEXT(126, 160, 200, 100, '检验项目');
        LODOP.ADD_PRINT_TEXT(126, 360, 200, 100, '检验结果');
        LODOP.ADD_PRINT_TEXT(126, 560, 100, 100, '参考值');
        LODOP.ADD_PRINT_TEXT(126, 660, 100, 100, '单位');

        for (var i = 0; i < checkResult.length; i++) {
            LODOP.ADD_PRINT_TEXT(150+17*i, 60, 100, 100, checkResult[i].serialNumber);
            LODOP.ADD_PRINT_TEXT(150+17*i, 160, 200,100, checkResult[i].itemName);
            LODOP.ADD_PRINT_TEXT(150+17*i, 360, 200,100, checkResult[i].defaultValue);
            LODOP.ADD_PRINT_TEXT(150+17*i, 560, 100, 100, checkResult[i].referenceValue);
            LODOP.ADD_PRINT_TEXT(150+17*i, 660, 100, 100, checkResult[i].unit);
        }

        var bottom_top = 498;//底边信息打印高度
        LODOP.ADD_PRINT_TEXT(bottom_top, 48, 200, 100, '检验时间:' + sampleInfo.resultEntryTime);
        LODOP.ADD_PRINT_TEXT(bottom_top, 250, 100, 100, '检验医师:' + sampleInfo.resultEntryDoctor);
        LODOP.ADD_PRINT_TEXT(bottom_top, 400, 200, 100, '审核时间:' + sampleInfo.resultAuditTime);
        LODOP.ADD_PRINT_TEXT(bottom_top, 600, 100, 100, '审核医师:' + sampleInfo.resultAuditDoctor);
        LODOP.ADD_PRINT_TEXT(bottom_top+18, 50, 750, 20, "★本结果仅对检测标本负责，供临床参考，如有疑问请在三日内反馈!");

        // LODOP.PRINT_DESIGN();
        LODOP.PREVIEW();
}