package cn.hs.reportentry.service;

import cn.hs.publicclass.mapper.CheckApplicationMapper;
import cn.hs.publicclass.method.FormatDate;
import cn.hs.publicclass.method.GetCookieService;
import cn.hs.publicclass.table.checkapplication.CheckApplication;
import cn.hs.samplerecept.dto.ReceptedSampleQueryDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportEntryService {

    @Autowired
    private CheckApplicationMapper checkApplicationMapper;

    @Autowired
    private GetCookieService getCookie;


}
