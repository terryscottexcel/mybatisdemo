package com.excel.demo;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.excel.demo.anno.mapper.BlogInfoAnnoMapper;
import com.excel.demo.model.BlogInfo;
import com.excel.demo.util.DBTools;
import com.excel.demo.util.DBToolsAnno;

/**
 * Hello world!
 *
 */
public class BlogInfoMainAppByAnno {
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
		
		insertBlogForUserByAnno(15);
		listAllBlogForUserByAnno(15);
		
		BlogInfo blog = new BlogInfo();
		blog.setTitle("中国核心技术进入世界领先地位");
		blog.setContext("中国核心技术取得重大突破，各个方面都有优异表现，受到各国的称赞，这一切都说明了中国的核心技术已经达到了先进水平，进入了世界的领先地位");
		blog.setKeyword("中国,核心技术,世界领先,优异,称赞");
		insertBlogForUserByAnno(blog,30);
		listAllBlogForUserByAnno(30);
		
		//listOneDataByAnno(162);
		//insertDataByAnno();
		//listMaxIdByAnno();
		//listAllDataByAnno();
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
				BlogInfo blog = new BlogInfo();
				blog.setTitle("B" + i);
				blog.setContext("Blog" + i);
				blog.setKeyword("The Key word for Blog" + i);
				blog.setUserId(20);

				//方式:使用Mapper接口的方式调用
				BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
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
			BlogInfo blog = new BlogInfo();
			int id = 162;
			blog.setId(id);
			blog.setTitle("B" + id);
			blog.setContext("Blog" + id);
			blog.setKeyword("The Key word for Blog" + id);
			blog.setUserId(20);

			//方式:使用Mapper接口的方式调用
			BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
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
			BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
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
			BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
			List<BlogInfo> list = mapper.selectAllBlog();
			if(list!=null && !list.isEmpty()) {
				for (BlogInfo blog : list) {
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
			BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
			BlogInfo blog = mapper.selectBlog(id);
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
			BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
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
			BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
			List<BlogInfo> list = mapper.selectAllBlog();
			if(list!=null && !list.isEmpty()) {
				for (BlogInfo blog : list) {
					int id = blog.getId();
					blog.setTitle("B" + id);
					blog.setContext("Blog" + id);
					blog.setKeyword("The Key word for Blog" + id);
					blog.setUserId(20);
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
	
	private static void insertBlogForUserByAnno(int userid) {
		SqlSession session = DBToolsAnno.getSession();
		try {
			Integer maxid = listMaxIdByAnno();
			
			for (int i = maxid; i < maxid+10; i++) {
				BlogInfo blog = new BlogInfo();
				blog.setTitle("Title" + i);
				blog.setContext("BlogContext" + i);
				blog.setKeyword("The Key word for BlogContext" + i);
				blog.setUserId(userid);

				//方式:使用Mapper接口的方式调用
				BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
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
	
	public static void listAllBlogForUserByAnno(int userid) {
		SqlSession session = DBToolsAnno.getSession();
		try {
			BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
			List<BlogInfo> list = mapper.selectBlogsByUserId(userid);
			if(list!=null && !list.isEmpty()) {
				for (BlogInfo blog : list) {
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
	
	private static void insertBlogForUserByAnno(BlogInfo blog, int userid) {
		SqlSession session = DBToolsAnno.getSession();
		try {
			blog.setUserId(userid);

			//方式:使用Mapper接口的方式调用
			BlogInfoAnnoMapper mapper = session.getMapper(BlogInfoAnnoMapper.class);
			int cnt = mapper.insertBlog(blog);
			System.out.println("insert:" + cnt);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
}
