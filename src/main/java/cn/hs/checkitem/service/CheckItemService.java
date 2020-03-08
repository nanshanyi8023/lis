package cn.hs.checkitem.service;

import cn.hs.checkitem.mapper.CheckItemMapper;
import cn.hs.checkitem.pojo.CheckItem;
import cn.hs.checkitemgroup.mapper.CheckItemGroupDetailMapper;
import cn.hs.checkitemgroup.mapper.CheckItemGroupMapper;
import cn.hs.checkitemgroup.pojo.CheckItemGroup;
import cn.hs.checkitemgroup.pojo.CheckItemGroupDetail;
import cn.hs.publicmethod.BusinessException;
import cn.hs.workgroup.mapper.WorkGroupDetailMapper;
import cn.hs.workgroup.mapper.WorkGroupMapper;
import cn.hs.workgroup.pojo.WorkGroup;
import cn.hs.workgroup.pojo.WorkGroupDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;

    @Autowired
    private WorkGroupMapper workGroupMapper;

    @Autowired
    private CheckItemGroupMapper checkItemGroupMapper;

    /*@Autowired
    private WorkGroupDetailMapper workGroupDetailMapper;

    @Autowired
    private CheckItemGroupDetailMapper checkItemGroupDetailMapper;*/

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

    //查找检验项目
    public List<CheckItem> getCheckItems(String workGroupId, String checkItemGroupId, String checkItem) {
        List<CheckItem> checkItemList = checkItemMapper.getCheckItems(this.getHosNum(), workGroupId,checkItemGroupId,checkItem);
        /*//查找工作组并将id赋给checkItem
        List<WorkGroupDetail> workGroupIdAndCheckItemIdList = workGroupDetailMapper.getWorkGroupIdAndCheckItemId(this.getHosNum());
        //查找检验项目组合并将id赋给checkItem
        List<CheckItemGroupDetail> checkItemGroupDetailList = checkItemGroupDetailMapper.getItemGroupIdAndItemId(this.getHosNum());
        for (int i = 0; i < checkItemList.size(); i++) {
            for (int j = 0; j < workGroupIdAndCheckItemIdList.size(); j++) {
                if (checkItemList.get(i).getItemId().equals(workGroupIdAndCheckItemIdList.get(j).getItemId())){
                    checkItemList.get(i).setWorkGroup(workGroupIdAndCheckItemIdList.get(j).getWorkGroupId());
                    continue;
                }
            }
            for (int j = 0; j < checkItemGroupDetailList.size(); j++) {
                if (checkItemList.get(i).getItemId().equals(checkItemGroupDetailList.get(j).getItemId())){
                    checkItemList.get(i).setCheckItemGroup(checkItemGroupDetailList.get(j).getItemGroupId());
                    continue;
                }
            }
        }*/
        return checkItemList;
    }

    //删除检验项目
    public int deleteCheckItems(List<String> itemIdList){
        if (itemIdList.isEmpty()){
            return 0;
        }
        return checkItemMapper.deleteCheckItems(this.getHosNum(), itemIdList);
    }

    //保存检验项目
    public CheckItem saveCheckItem(CheckItem checkItem) {
        String hosNum = this.getHosNum();
        checkItem.setHosnum(hosNum);
        if (StringUtils.isEmpty(checkItem.getItemId())){  //新增
            if (this.isRepeat(hosNum,checkItem.getItemName())){
                throw new BusinessException("检验项目名称不可重复");
            }
            checkItem.setItemId(checkItemMapper.getMaxId(hosNum));
            checkItemMapper.insertSelective(checkItem);
        }else {  //更新
            checkItemMapper.updateByPrimaryKeySelective(checkItem);
        }
        return null;
    }

    //判断检验项目名称是否重复
    private boolean isRepeat(String hosNum, String itemName) {
        List<String> list =  checkItemMapper.getAllExamMethod(hosNum);
        for (int i = 0; i < list.size(); i++) {
            if (itemName.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

    //根据医院号查找所有工作组
    public List<WorkGroup> getAllWorkGroup() {
        List<WorkGroup> workGroupList = workGroupMapper.selectAllWorkGroup(this.getHosNum());
        return workGroupList;
    }

    //根据医院号和工作组id查找所有检验项目组合
    public List<CheckItemGroup> getAllCheckItemGroup(String workGroupId) {
        List<CheckItemGroup> checkItemGroupList = checkItemGroupMapper.selectAllCheckItemGroup(this.getHosNum(),workGroupId);
        return checkItemGroupList;
    }
}
