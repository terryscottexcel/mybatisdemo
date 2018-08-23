package com.excel.demo;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.excel.demo.mapper.FansInfoMapper;
import com.excel.demo.model.FansInfo;
import com.excel.demo.util.DBTools;

/**
 * Hello world!
 *
 */
public class FansInfoMainAppByXml {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello Mybatis44444!");

		usewithxml();
		
	}

	public static void usewithxml() throws IOException {
		//insertData(14);
		//listAllData();
		listAllFansForUserByXml(14);
		
		//listOneData(6);
		//updateData();
		//listOneData(6);
		
		listOneData(28);
		//deleteData();
		listOneData(28);

	}

	private static void insertData(Integer userId) {
		SqlSession session = DBTools.getSession();
		try {
			for (int i = 0; i < 105; i++) {
				FansInfo fans = new FansInfo();
				fans.setFansCode("F" + i);
				fans.setFansName("Fans" + i);
				fans.setFansRmk("the Fans Description for Fans" + i);
				fans.setUserId(userId);

				//方式1:使用xml配置文件里的sql语句执行
				/*
				int cnt = session.insert("com.excel.demo.mapper.FansInfoMapper.insert", fans);
				System.out.println("insert:" + cnt);
				*/
				
				//方式2:使用Mapper接口的方式调用
				FansInfoMapper mapper = session.getMapper(FansInfoMapper.class);
				mapper.insert(fans);
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
			FansInfo fans = new FansInfo();
			int id = 6;
			fans.setId(id);
			fans.setFansCode("F33" + id);
			fans.setFansName("Fans447" + id);
			fans.setFansRmk("the Fans Description for Fans33" + id);

			//int cnt = session.update("com.excel.demo.mapper.FansInfoMapper.updateByPrimaryKey", blog);
			
			//方式2:使用Mapper接口的方式调用
			FansInfoMapper mapper = session.getMapper(FansInfoMapper.class);
			int cnt = mapper.updateByPrimaryKey(fans);
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

			//int cnt = session.delete("com.excel.demo.mapper.FansInfoMapper.deleteByPrimaryKey", id);
			//方式2:使用Mapper接口的方式调用
			FansInfoMapper mapper = session.getMapper(FansInfoMapper.class);
			int cnt = mapper.deleteByPrimaryKey(id);
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
			List<Blog> list = session.selectList("com.excel.demo.mapper.FansInfoMapper.selectAll");
			for (Blog blog : list) {
				System.out.println(blog);
			}
			*/
	
			//第2种方式:获取Mapper对象，调用对象方法
			FansInfoMapper mapper = session.getMapper(FansInfoMapper.class);
			List<FansInfo> list = mapper.selectAll();
			if(list!=null && !list.isEmpty()) {
				for (FansInfo fans : list) {
					System.out.println(fans);
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
			Blog blog = session.selectOne("com.excel.demo.mapper.FansInfoMapper.selectByPrimaryKey",id);
			System.out.println(blog);
			*/
			
			//第2种方式:获取Mapper对象，调用对象方法
			FansInfoMapper mapper = session.getMapper(FansInfoMapper.class);
			FansInfo fans = mapper.selectByPrimaryKey(id);
			if(fans==null) {
				System.out.println("data is not found!");
			}else {
				System.out.println(fans);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	
	public static void listAllFansForUserByXml(Integer userId) {
		SqlSession session = DBTools.getSession();
		try {
			//第1种方式:使用指定要调用的sql语句的id
			/*
			List<Blog> list = session.selectList("com.excel.demo.mapper.FansInfoMapper.selectAll");
			for (Blog blog : list) {
				System.out.println(blog);
			}
			 */
			
			//第2种方式:获取Mapper对象，调用对象方法
			FansInfoMapper mapper = session.getMapper(FansInfoMapper.class);
			List<FansInfo> list = mapper.selectAllByUserId(userId);
			if(list!=null && !list.isEmpty()) {
				for (FansInfo fans : list) {
					System.out.println(fans);
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
	
}
