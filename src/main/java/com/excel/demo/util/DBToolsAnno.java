package com.excel.demo.util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.excel.demo.anno.mapper.BlogAnnoMapper;

public class DBToolsAnno {
    private static SqlSessionFactory sessionFactory;
    
    static{
        try {
            //使用MyBatis提供的Resources类加载db连接的的配置文件
        	Properties propdb = new Properties();
        	//使用MyBatis提供的Resources类加载其它的配置文件
        	Properties propcfg = new Properties();
    		try {
    			 String dbresource = "mysql.properties";
                 InputStream inputStream = Resources.getResourceAsStream(dbresource);
    			 propdb.load(inputStream);
    			 
    			 String cfgresource = "myconfig.properties";
                 InputStream inputStreamCfg = Resources.getResourceAsStream(cfgresource);
    			 propcfg.load(inputStreamCfg);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		PooledDataSourceFactory factory = new PooledDataSourceFactory();
    		factory.setProperties(propdb);
    		DataSource dataSource = factory.getDataSource();
    		TransactionFactory transactionFactory = new JdbcTransactionFactory();
    		String environmentName = propcfg.getProperty("environmentName");
    		Environment environment = new Environment(environmentName, transactionFactory, dataSource);
    		Configuration configuration = new Configuration(environment);
    		//方式1：指定Mapper接口类，需要写多个
    		//configuration.addMapper(BlogAnnoMapper.class);
    		//方式2：指定Mapper接口类的包名，会自动找Mapper接口
    		String mapperPackage = propcfg.getProperty("mapperPackage");
    		configuration.addMappers(mapperPackage);
    		
            //构建sqlSession的工厂
            sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    //创建能执行映射文件中sql的sqlSession
    public static SqlSession getSession(){
        return sessionFactory.openSession();
    }
    
}