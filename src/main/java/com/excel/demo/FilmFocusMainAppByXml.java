package com.excel.demo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excel.demo.mapper.FilmFocusMapper;
import com.excel.demo.model.FilmFocus;
import com.excel.demo.util.DBTools;
import com.excel.demo.util.PropertiesTools;

/**
 * Hello world!
 *
 */
public class FilmFocusMainAppByXml {
	private static final Logger LOGGER = LoggerFactory.getLogger(FilmFocusMainAppByXml.class);
	
	public static void main(String[] args) throws IOException {
		LOGGER.debug("Hello Mybatis555!");
		usewithxml();
		
	}

	public static void usewithxml() throws IOException {
		//insertData(17);
		//listAllData();
		//listAllFilmsForUserByXml(18);
		
		selectAllByName("568588");
		Map<String, Object> params = new HashMap<String, Object>(); 
		params.put("filmName", "大闹天宫"); 
		params.put("filmActors", "Actor33");
		params.put("filmRmk", "good");
		params.put("fromIndex", 21);
		params.put("pageSize", 20);
		params.put("lowPrice", 11.669);
		params.put("highPrice", 211.541);
		selectAllByParam(params);
		
		params.put("fromIndex", 31);
		params.put("pageSize", 10);
		selectAllByParam(params);
		//listOneData(6);
		//updateData();
		//listOneData(6);
		
		//listOneData(28);
		//deleteData();
		//listOneData(28);

	}

	private static void insertData(Integer userId) {
		SqlSession session = DBTools.getSession();
		try {
			Properties prop = PropertiesTools.getProperties();
			int filmIdx=1;
			for (int i = 1; i < 1000000; i++) {
				FilmFocus film = new FilmFocus();
				film.setFilmCode("F" + i);
				if(filmIdx>104) {
					filmIdx=1;
				}
				String filmName = prop.getProperty(""+filmIdx)+i;
				LOGGER.debug(filmName);
				film.setFilmName(filmName);
				film.setFilmActors("Actor"+i);
				film.setFilmShowDate(new Date());
				film.setFilmPrice(new BigDecimal(""+Math.random()*100));
				film.setFilmRmk("The film is good ,everyone like it . Remark for Film" + i);
				film.setUserId(userId);
				
				filmIdx++;

				//方式1:使用xml配置文件里的sql语句执行
				/*
				int cnt = session.insert("com.excel.demo.mapper.FilmFocusMapper.insert", fans);
				LOGGER.debug("insert:" + cnt);
				*/
				
				//方式2:使用Mapper接口的方式调用
				FilmFocusMapper mapper = session.getMapper(FilmFocusMapper.class);
				mapper.insert(film);
				
				if(i%1000==0) {
					session.commit();
				}
			}
			session.commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			session.rollback();
		} finally {
			session.close();
		}
	}

	private static void updateData() {
		SqlSession session = DBTools.getSession();
		try {
			FilmFocus film = new FilmFocus();
			int id = 968;
			film.setId(id);
			film.setFilmCode("F" + id);
			film.setFilmName("Film" + id);
			film.setFilmActors("Jack,Mary,Brown,Actor3"+id);
			film.setFilmPrice(new BigDecimal("33."+id));
			film.setFilmRmk("The film is good ,everyone like it . Remark for Film2" + id);

			//int cnt = session.update("com.excel.demo.mapper.FilmFocusMapper.updateByPrimaryKey", blog);
			
			//方式2:使用Mapper接口的方式调用
			FilmFocusMapper mapper = session.getMapper(FilmFocusMapper.class);
			int cnt = mapper.updateByPrimaryKey(film);
			LOGGER.debug("update:{}", cnt);
			session.commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			session.rollback();
		} finally {
			session.close();
		}
	}
	
	private static void deleteData() {
		SqlSession session = DBTools.getSession();
		try {
			int id = 28;

			//int cnt = session.delete("com.excel.demo.mapper.FilmFocusMapper.deleteByPrimaryKey", id);
			//方式2:使用Mapper接口的方式调用
			FilmFocusMapper mapper = session.getMapper(FilmFocusMapper.class);
			int cnt = mapper.deleteByPrimaryKey(id);
			LOGGER.debug("delete:{}" , cnt);
			session.commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
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
			List<Blog> list = session.selectList("com.excel.demo.mapper.FilmFocusMapper.selectAll");
			for (Blog blog : list) {
				LOGGER.debug(blog);
			}
			*/
	
			//第2种方式:获取Mapper对象，调用对象方法
			FilmFocusMapper mapper = session.getMapper(FilmFocusMapper.class);
			List<FilmFocus> list = mapper.selectAll();
			if(list!=null && !list.isEmpty()) {
				for (FilmFocus film : list) {
					LOGGER.debug(film.toString());
				}
				LOGGER.debug("sum:{}",list.size());
			}else {
				LOGGER.debug("the data is not found!");
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	
	private static void listOneData(int id) {
		SqlSession session = DBTools.getSession();
		try {
			//第1种方式:使用指定要调用的sql语句的id
			/*
			Blog blog = session.selectOne("com.excel.demo.mapper.FilmFocusMapper.selectByPrimaryKey",id);
			LOGGER.debug(blog);
			*/
			
			//第2种方式:获取Mapper对象，调用对象方法
			FilmFocusMapper mapper = session.getMapper(FilmFocusMapper.class);
			FilmFocus film = mapper.selectByPrimaryKey(id);
			if(film==null) {
				LOGGER.debug("data is not found!");
			}else {
				LOGGER.debug(film.toString());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
	}

	
	public static void listAllFilmsForUserByXml(Integer userId) {
		long start = System.currentTimeMillis();
		SqlSession session = DBTools.getSession();
		try {
			//第1种方式:使用指定要调用的sql语句的id
			/*
			List<Blog> list = session.selectList("com.excel.demo.mapper.FilmFocusMapper.selectAll");
			for (Blog blog : list) {
				LOGGER.debug(blog);
			}
			 */
			
			//第2种方式:获取Mapper对象，调用对象方法
			FilmFocusMapper mapper = session.getMapper(FilmFocusMapper.class);
			List<FilmFocus> list = mapper.selectAllByUserId(userId);
			if(list!=null && !list.isEmpty()) {
				for (FilmFocus film : list) {
					LOGGER.debug(film.toString());
				}
				LOGGER.debug("sum:"+list.size());
			}else {
				LOGGER.debug("the data is not found!");
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			LOGGER.debug("listAllFilmsForUserByXml take times={}",(end-start));
			session.close();
		}
		
	}
	
	public static void selectAllByName(String filmName) {
		long start = System.currentTimeMillis();
		SqlSession session = DBTools.getSession();
		try {
			//第1种方式:使用指定要调用的sql语句的id
			/*
			List<Blog> list = session.selectList("com.excel.demo.mapper.FilmFocusMapper.selectAll");
			for (Blog blog : list) {
				LOGGER.debug(blog);
			}
			 */
			
			//第2种方式:获取Mapper对象，调用对象方法
			FilmFocusMapper mapper = session.getMapper(FilmFocusMapper.class);
			List<FilmFocus> list = mapper.selectAllByName(filmName);
			if(list!=null && !list.isEmpty()) {
				for (FilmFocus film : list) {
					LOGGER.debug(film.toString());
				}
				LOGGER.debug("sum:{}",list.size());
			}else {
				LOGGER.debug("the data is not found!");
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			LOGGER.debug("selectAllByName take times={}",(end-start));
			session.close();
		}
		
	}
	
	public static void selectAllByParam(Map<String, Object> params) {
		long start = System.currentTimeMillis();
		SqlSession session = DBTools.getSession();
		try {
			//第1种方式:使用指定要调用的sql语句的id
			/*
			List<Blog> list = session.selectList("com.excel.demo.mapper.FilmFocusMapper.selectAll");
			for (Blog blog : list) {
				LOGGER.debug(blog);
			}
			 */
			
			//第2种方式:获取Mapper对象，调用对象方法
			FilmFocusMapper mapper = session.getMapper(FilmFocusMapper.class);
			int cnt = mapper.selectAllCountByParam(params);
			List<FilmFocus> list = mapper.selectAllByParam(params);
			if(list!=null && !list.isEmpty()) {
				for (FilmFocus film : list) {
					LOGGER.debug(film.toString());
				}
				LOGGER.debug("sum:{},data size:{}",cnt,list.size());
			}else {
				LOGGER.debug("the data is not found!");
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			LOGGER.debug("selectAllByParam take times={}",(end-start));
			session.close();
		}
		
	}
	
}
