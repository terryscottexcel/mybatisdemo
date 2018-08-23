package com.excel.demo.anno.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.excel.demo.model.UserInfo;


public interface UserInfoAnnoMapper {
  @Results(value= {
		  @Result(column="user_code",property="userCode"),
		  @Result(column="user_name",property="userName")
		  })
  @Select("SELECT * FROM t_userinfo WHERE id = #{id}")
  UserInfo selectUserInfo(Integer id);
  
  @Results(value= {
		  @Result(column="user_code",property="userCode"),
		  @Result(column="user_name",property="userName")
		  })
  @Select("SELECT * FROM t_userinfo")
  List<UserInfo> selectAllUserInfo();
  
  @Insert("insert into t_userinfo (user_code,user_name,birthday) values (#{userCode},#{userName},#{birthday})")
  int insertUserInfo(UserInfo blog);
  
  @Update("update t_userinfo set user_code=#{userCode},user_name=#{userName},birthday=#{birthday} where id=#{id}")
  int updateUserInfo(UserInfo blog);
  
  @Delete("delete from t_userinfo where id=#{id}")
  int deleteUserInfo(Integer id);
  
  @Select("select max(id) maxid from t_userinfo")
  Integer selectMaxId();
  
  
}