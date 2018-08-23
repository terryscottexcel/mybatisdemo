package com.excel.demo.mapper;

import java.util.List;

import com.excel.demo.model.Blog;


public interface BlogXmlMapper {
    /**
     * 新增博客
     * @param blog
     * @return
     * @throws Exception
     */
    public int insertBlog(Blog blog) throws Exception;
    /**
     * 修改博客
     * @param blog
     * @param id
     * @return
     * @throws Exception
     */
    public int updateBlog (Blog blog,int id) throws Exception;
     /**
      * 刪除博客
      * @param id
      * @return
      * @throws Exception
      */
    public int deleteBlog(int id) throws Exception;
    /**
     * 根据id查询博客信息
     * @param id
     * @return
     * @throws Exception
     */
    public Blog selectBlogById(int id) throws Exception;
     /**
      * 查询所有的博客信息
      * @return
      * @throws Exception
      */
    public List<Blog> selectAllBlog() throws Exception;
}