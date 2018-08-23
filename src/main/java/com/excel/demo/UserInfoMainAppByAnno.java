package com.excel.demo;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.excel.demo.anno.mapper.UserInfoAnnoMapper;
import com.excel.demo.model.UserInfo;
import com.excel.demo.util.DBTools;
import com.excel.demo.util.DBToolsAnno;
import com.excel.demo.util.DateUtil;

/**
 * Hello world!
 *
 */
public class UserInfoMainAppByAnno {
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
		//listOneDataByAnno(123);
		//MainAppByAnno.listAllBlogForUserByAnno(123);
		//listAllBlogForAllUserByAnno();
		//listAllFansForAllUserByAnno();
		listAllDatasForAllUserByAnno();
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
			for (int i = maxid; i < maxid+30; i++) {
				UserInfo userinfo = new UserInfo();
				userinfo.setUserCode("U" + i);
				userinfo.setUserName("UserInfo" + i);
				userinfo.setBirthday(DateUtil.convertToUtilDate("1985/08/06"));

				//方式:使用Mapper接口的方式调用
				UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
				int cnt = mapper.insertUserInfo(userinfo);
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
			UserInfo userinfo = new UserInfo();
			int id = 162;
			userinfo.setId(id);
			userinfo.setUserCode("U5" + id);
			userinfo.setUserName("UserInfo7" + id);

			//方式:使用Mapper接口的方式调用
			UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
			int cnt = mapper.updateUserInfo(userinfo);
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
			UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
			int cnt = mapper.deleteUserInfo(id);
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
			UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
			List<UserInfo> list = mapper.selectAllUserInfo();
			if(list!=null && !list.isEmpty()) {
				for (UserInfo userinfo : list) {
					System.out.println(userinfo);
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
			UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
			UserInfo userinfo = mapper.selectUserInfo(id);
			if(userinfo==null) {
				System.out.println("data is not found!");
			}
			System.out.println(userinfo);

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
			UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
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
			UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
			List<UserInfo> list = mapper.selectAllUserInfo();
			if(list!=null && !list.isEmpty()) {
				for (UserInfo userinfo : list) {
					int id = userinfo.getId();
					userinfo.setUserCode("U"+id);
					userinfo.setUserName("UserInfo"+id);
					mapper.updateUserInfo(userinfo);
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
	
	private static void listAllBlogForAllUserByAnno() {
		SqlSession session = DBToolsAnno.getSession();
		try {
			UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
			List<UserInfo> list = mapper.selectAllUserInfo();
			if(list!=null && !list.isEmpty()) {
				for (UserInfo userinfo : list) {
					System.out.println("##################################################");
					System.out.println(userinfo);
					BlogInfoMainAppByAnno.listAllBlogForUserByAnno(userinfo.getId());
					System.out.println("##################################################");
					System.out.println();
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
	
	private static void listAllFansForAllUserByAnno() {
		SqlSession session = DBToolsAnno.getSession();
		try {
			UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
			List<UserInfo> list = mapper.selectAllUserInfo();
			if(list!=null && !list.isEmpty()) {
				for (UserInfo userinfo : list) {
					System.out.println("##################################################");
					System.out.println(userinfo);
					FansInfoMainAppByXml.listAllFansForUserByXml(userinfo.getId());
					System.out.println("##################################################");
					System.out.println();
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
	
	private static void listAllDatasForAllUserByAnno() {
		SqlSession session = DBToolsAnno.getSession();
		try {
			UserInfoAnnoMapper mapper = session.getMapper(UserInfoAnnoMapper.class);
			List<UserInfo> list = mapper.selectAllUserInfo();
			if(list!=null && !list.isEmpty()) {
				for (UserInfo userinfo : list) {
					System.out.println("##################################################");
					System.out.println(userinfo);
					BlogInfoMainAppByAnno.listAllBlogForUserByAnno(userinfo.getId());
					System.out.println();
					FansInfoMainAppByXml.listAllFansForUserByXml(userinfo.getId());
					System.out.println();
					FilmFocusMainAppByXml.listAllFilmsForUserByXml(userinfo.getId());
					System.out.println("##################################################");
					System.out.println();
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
