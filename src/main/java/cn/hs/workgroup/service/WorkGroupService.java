package cn.hs.workgroup.service;

import cn.hs.publicclass.method.BusinessException;
import cn.hs.workgroup.mapper.WorkGroupMapper;
import cn.hs.workgroup.pojo.WorkGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public void saveWorkGroup(WorkGroup workGroup) {
        String hosNum = this.getHosNum();
        workGroup.setHosnum(hosNum);
        if (StringUtils.isEmpty(workGroup.getWorkGroupId())){  //新增
            if (this.isRepeat(hosNum,workGroup.getWorkGroupName())){
                throw new BusinessException("检验项目名称不可重复");
            }
            workGroup.setWorkGroupId(workGroupMapper.getMaxId(hosNum));
            workGroupMapper.insertSelective(workGroup);
        }else {  //更新
            workGroupMapper.updateByPrimaryKeySelective(workGroup);
        }
    }

    //判断检验项目名称是否重复
    private boolean isRepeat(String hosNum, String workGroupName) {
        List<String> list =  workGroupMapper.getAllWorkGroupName(hosNum);
        for (int i = 0; i < list.size(); i++) {
            if (workGroupName.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

    //根据组类型查找组代码
    public String getGroupCode(String groupType) {
        return workGroupMapper.getGroupCode(this.getHosNum(),groupType);
    }
}
