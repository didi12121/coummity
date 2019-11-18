package com.example.didi1309.coummity.mapper;

import com.example.didi1309.coummity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("Insert into USER (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accoundId},#{token},#{gmtCreate},#{gmtModified})")
     void insert(User user);
}
