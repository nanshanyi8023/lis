package cn.hs.checkitemgroup.service;

import cn.hs.checkitemgroup.mapper.CheckItemGroupMapper;
import cn.hs.checkitemgroup.pojo.CheckItemGroup;
import cn.hs.publicmethod.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CheckItemGroupService {

    @Autowired
    private CheckItemGroupMapper checkItemGroupMapper;

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

    //查找检验项目组合
    public List<CheckItemGroup> getcheckItemGroups(String workGroupId, String checkItemGroup) {
        return checkItemGroupMapper.getcheckItemGroups(this.getHosNum(),workGroupId,checkItemGroup);
    }

    public int deleteCheckItemGroups(List<String> itemIdList) {
        if (itemIdList.isEmpty()){
            return 0;
        }
        return checkItemGroupMapper.deleteCheckItemGroups(this.getHosNum(),itemIdList);
    }

    //保存检验项目组合
    public void savecheckItemGroup(CheckItemGroup checkItemGroup) {
        String hosNum = this.getHosNum();
        checkItemGroup.setHosnum(hosNum);
        if (StringUtils.isEmpty(checkItemGroup.getGroupId())){  //新增
            if (this.isRepeat(hosNum,checkItemGroup.getGroupName())){
                throw new BusinessException("检验项目组合名称不可重复");
            }
            checkItemGroup.setGroupId(checkItemGroupMapper.getMaxId(hosNum));
            checkItemGroupMapper.insertSelective(checkItemGroup);
        }else {  //更新
            checkItemGroupMapper.updateByPrimaryKeySelective(checkItemGroup);
        }
    }

    //判断检验项目名称是否重复
    private boolean isRepeat(String hosNum, String checkItemGroupName) {
        List<String> list =  checkItemGroupMapper.getAllcheckItemGroupName(hosNum);
        for (int i = 0; i < list.size(); i++) {
            if (checkItemGroupName.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

    //查找所有样品类型
    public List<String> getAllSampleType() {
        return checkItemGroupMapper.getAllSampleType(this.getHosNum());
    }
}
