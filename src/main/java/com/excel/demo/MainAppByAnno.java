package com.excel.demo;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.excel.demo.anno.mapper.BlogAnnoMapper;
import com.excel.demo.mapper.BlogXmlMapper;
import com.excel.demo.model.Blog;
import com.excel.demo.util.DBTools;
import com.excel.demo.util.DBToolsAnno;

/**
 * Hello world!
 *
 */
public class MainAppByAnno {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello Mybatis3333!");
		
		usewithoutxml();
	}

	public static void usewithoutxml() {
		//listOneDataByAnno(3);
		//listAllDataByAnno();
		//insertDataByAnno();
		//updateDataByAnno();
		//deleteDataByAnno();
		
		//listOneDataByAnno(162);
		//insertDataByAnno();
		//listMaxIdByAnno();
		listAllDataByAnno();
		//System.out.println();
		//updateCodeNameAllDataByAnno();
		//System.out.println();
		//listAllDataByAnno();
		
	}

	private static void insertDataByAnno() {
		SqlSession session = DBToolsAnno.getSession();
		try {
			int maxid = listMaxIdByAnno();
			for (int i = maxid; i < maxid+100; i++) {
				Blog blog = new Blog();
				blog.setCode("B" + i);
				blog.setName("Blog" + i);
				blog.setDesc("the Blog Description for Blog" + i);

				//方式:使用Mapper接口的方式调用
				BlogAnnoMapper mapper = session.getMapper(BlogAnnoMapper.class);
				int cnt = mapper.insertBlog(blog);
				System.out.println("insert:" + cnt);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
	

	private static void updateDataByAnno() {
		SqlSession session = DBToolsAnno.getSession();
		try {
			Blog blog = new Blog();
			int id = 162;
			blog.setId(id);
			blog.setCode("B5" + id);
			blog.setName("Blog7" + id);
			blog.setDesc("the Blog Description for Blog9" + id);

			//方式:使用Mapper接口的方式调用
			BlogAnnoMapper mapper = session.getMapper(BlogAnnoMapper.class);
			int cnt = mapper.updateBlog(blog);
			System.out.println("update:" + cnt);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
	
	private static void deleteDataByAnno() {
		SqlSession session = DBTools.getSession();
		try {
			int id = 248;

			//方式:使用Mapper接口的方式调用
			BlogAnnoMapper mapper = session.getMapper(BlogAnnoMapper.class);
			int cnt = mapper.deleteBlog(id);
			System.out.println("delete:" + cnt);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
	
	private static void listAllDataByAnno() {
		SqlSession session = DBToolsAnno.getSession();
		try {
			BlogAnnoMapper mapper = session.getMapper(BlogAnnoMapper.class);
			List<Blog> list = mapper.selectAllBlog();
			if(list!=null && !list.isEmpty()) {
				for (Blog blog : list) {
					System.out.println(blog);
				}
			}else {
				System.out.println("the data is not found!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private static void listOneDataByAnno(int id) {
		SqlSession session = DBToolsAnno.getSession();
		try {
			BlogAnnoMapper mapper = session.getMapper(BlogAnnoMapper.class);
			Blog blog = mapper.selectBlog(id);
			if(blog==null) {
				System.out.println("data is not found!");
			}
			System.out.println(blog);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private static Integer listMaxIdByAnno() {
		Integer maxid=1;
		SqlSession session = DBToolsAnno.getSession();
		try {
			BlogAnnoMapper mapper = session.getMapper(BlogAnnoMapper.class);
			Integer id = mapper.selectMaxId();
			System.out.println("the max id = "+id);
			if(id!=null) {
				maxid = id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return maxid;
	}
	
	private static void updateCodeNameAllDataByAnno() {
		SqlSession session = DBToolsAnno.getSession();
		try {
			BlogAnnoMapper mapper = session.getMapper(BlogAnnoMapper.class);
			List<Blog> list = mapper.selectAllBlog();
			if(list!=null && !list.isEmpty()) {
				for (Blog blog : list) {
					int id = blog.getId();
					blog.setCode("B"+id);
					blog.setName("Blog"+id);
					blog.setDesc("The descriptions for Blog"+id);
					mapper.updateBlog(blog);
				}
			}else {
				System.out.println("the data is not found!");
			}
			
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
	
	
}
