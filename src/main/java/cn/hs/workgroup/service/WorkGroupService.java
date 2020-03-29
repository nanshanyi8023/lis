package cn.hs.workgroup.service;

import cn.hs.publicclass.method.BusinessException;
import cn.hs.publicclass.method.GetCookieService;
import cn.hs.workgroup.mapper.WorkGroupMapper;
import cn.hs.workgroup.pojo.WorkGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class WorkGroupService {

    @Autowired
    private WorkGroupMapper workGroupMapper;

    @Autowired
    private GetCookieService getCookie;

    //查找工作组
    public List<WorkGroup> getworkGroups(String workGroup) {
        return workGroupMapper.getworkGroups(getCookie.getHosNum(),workGroup);
    }

    //删除选中工作组
    public int deleteWorkGroups(List<String> itemIdList) {
        if (itemIdList.isEmpty()){
            return 0;
        }
        return workGroupMapper.deleteWorkGroups(getCookie.getHosNum(),itemIdList);
    }

    //保存工作组
    public void saveWorkGroup(WorkGroup workGroup) {
        String hosNum = getCookie.getHosNum();
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
        return workGroupMapper.getGroupCode(getCookie.getHosNum(),groupType);
    }
}
