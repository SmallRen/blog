package com.renbaojia.blog.dao;

import com.renbaojia.blog.model.LoginLog;
import com.renbaojia.blog.model.LoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginLogMapper {
    long countByExample(LoginLogExample example);

    int deleteByExample(LoginLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    List<LoginLog> selectByExample(LoginLogExample example);

    LoginLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    int updateByExample(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}