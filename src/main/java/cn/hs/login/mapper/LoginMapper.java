package cn.hs.login.mapper;

import cn.hs.login.dto.User_Info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    //判断登录是否成功
    // 查询所有账户
    @Select(" select * from user_info where login_name = #{loginName} and login_password = #{loginPassword}")
    User_Info doLogin(@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);
}
