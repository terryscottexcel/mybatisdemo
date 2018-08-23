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
public class MainAppByXml {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello Mybatis3333!");

		usewithxml();
		
	}

	public static void usewithxml() throws IOException {
		//insertData();
		listAllData();
		
		//listOneData(6);
		//updateData();
		//listOneData(6);
		
		listOneData(28);
		deleteData();
		listOneData(28);

	}

	private static void insertData() {
		SqlSession session = DBTools.getSession();
		try {
			for (int i = 0; i < 100; i++) {
				Blog blog = new Blog();
				blog.setCode("B" + i);
				blog.setName("Blog" + i);
				blog.setDesc("the Blog Description for Blog" + i);

				//方式1:使用xml配置文件里的sql语句执行
				/*
				int cnt = session.insert("com.excel.demo.mapper.BlogXmlMapper.insertBlog", blog);
				System.out.println("insert:" + cnt);
				*/
				
				//方式2:使用Mapper接口的方式调用
				BlogXmlMapper mapper = session.getMapper(BlogXmlMapper.class);
				mapper.insertBlog(blog);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	private static void updateData() {
		SqlSession session = DBTools.getSession();
		try {
			Blog blog = new Blog();
			int id = 6;
			blog.setId(id);
			blog.setCode("B33" + id);
			blog.setName("Blog447" + id);
			blog.setDesc("the Blog Description for Blog33" + id);

			int cnt = session.update("com.excel.demo.mapper.BlogXmlMapper.updateBlog", blog);
			System.out.println("update:" + cnt);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
	
	private static void deleteData() {
		SqlSession session = DBTools.getSession();
		try {
			int id = 28;

			int cnt = session.delete("com.excel.demo.mapper.BlogXmlMapper.deleteBlog", id);
			System.out.println("delete:" + cnt);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	private static void listAllData() {
		SqlSession session = DBTools.getSession();
		try {
			//第1种方式:使用指定要调用的sql语句的id
			/*
			List<Blog> list = session.selectList("com.excel.demo.mapper.BlogXmlMapper.selectAllBlog");
			for (Blog blog : list) {
				System.out.println(blog);
			}
			*/
	
			//第2种方式:获取Mapper对象，调用对象方法
			BlogXmlMapper mapper = session.getMapper(BlogXmlMapper.class);
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
	
	private static void listOneData(int id) {
		SqlSession session = DBTools.getSession();
		try {
			//第1种方式:使用指定要调用的sql语句的id
			/*
			Blog blog = session.selectOne("com.excel.demo.mapper.BlogXmlMapper.selectBlogById",id);
			System.out.println(blog);
			*/
			
			//第2种方式:获取Mapper对象，调用对象方法
			BlogXmlMapper mapper = session.getMapper(BlogXmlMapper.class);
			Blog blog = mapper.selectBlogById(id);
			if(blog==null) {
				System.out.println("data is not found!");
			}else {
				System.out.println(blog);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	
}
