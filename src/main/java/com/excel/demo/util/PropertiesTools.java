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

public class PropertiesTools {
    private static Properties propcfg = new Properties();
    
    static {
        try {
    		try {
    			 
    			 String cfgresource = "myconfig.properties";
                 InputStream inputStreamCfg = Resources.getResourceAsStream(cfgresource);
    			 propcfg.load(inputStreamCfg);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    //创建能执行映射文件中sql的sqlSession
    public static Properties getProperties(){
        return propcfg;
    }
    public static void main(String[] args) {
    	String s = propcfg.getProperty("101");
    	System.out.println(s);
    	
	}
}