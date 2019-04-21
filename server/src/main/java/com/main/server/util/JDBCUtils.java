package com.main.server.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class JDBCUtils {
 	private static SqlSessionFactory sessionFactory;
 
	public static SqlSession getSession(){
 
		return sessionFactory.openSession();
	}
 
	public static SqlSession getSessionn(boolean b){
		return sessionFactory.openSession(b);
	}
 
}