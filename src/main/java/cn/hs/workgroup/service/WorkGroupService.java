package cn.hs.workgroup.service;

import cn.hs.workgroup.mapper.WorkGroupMapper;
import cn.hs.workgroup.pojo.WorkGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class WorkGroupService {

    @Autowired
    private WorkGroupMapper workGroupMapper;

    @Autowired
    private HttpServletRequest request;

    //查找医院号
    private String getHosNum(){
        Cookie cookies[]=request.getCookies();
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("hosNum")){
                return cookie.getValue();
            }
        }
        return null;
    }

    //查找工作组
    public List<WorkGroup> getworkGroups(String workGroup) {
        return workGroupMapper.getworkGroups(this.getHosNum(),workGroup);
    }

    //删除选中工作组
    public int deleteWorkGroups(List<String> itemIdList) {
        if (itemIdList.isEmpty()){
            return 0;
        }
        return workGroupMapper.deleteWorkGroups(this.getHosNum(),itemIdList);
    }

    //保存工作组
    public WorkGroup saveWorkGroup(WorkGroup workGroup) {

        return null;
    }
}
