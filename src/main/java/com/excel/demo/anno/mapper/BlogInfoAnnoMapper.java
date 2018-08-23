package com.excel.demo.anno.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.excel.demo.model.BlogInfo;

public interface BlogInfoAnnoMapper {
  @Results(value= {
		  @Result(column="user_id",property="userId")
		  })
  @Select("SELECT * FROM t_bloginfo WHERE id = #{id}")
  BlogInfo selectBlog(int id);
  
  @Results(value= {
		  @Result(column="rmk",property="desc")
		  })
  @Select("SELECT * FROM t_bloginfo")
  List<BlogInfo> selectAllBlog();
  
  @Insert("insert into t_bloginfo (title,context,keyword,user_id) values (#{title},#{context},#{keyword},#{userId})")
  int insertBlog(BlogInfo blog);
  
  @Update("update t_bloginfo set title=#{title},context=#{context},keyword=#{keyword} where id=#{id}")
  int updateBlog(BlogInfo blog);
  
  @Delete("delete from t_bloginfo where id=#{id}")
  int deleteBlog(int id);
  
  @Select("select max(id) maxid from t_bloginfo")
  Integer selectMaxId();
  
  @Results(value= {
		  @Result(column="user_id",property="userId")
		  })
  @Select("select * from t_bloginfo where user_id=#{userid}")
  List<BlogInfo> selectBlogsByUserId(Integer userid);
}