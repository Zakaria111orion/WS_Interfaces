package jpu2016.javapetri.dao;

import jpu2016.javapetri.model.JavaCell;
import jpu2016.javapetri.model.JavaPetri;
import java.sql.*;

public class DAOJavaPetri {
	private static String URL = "jdbc:mysql://localhost/wsdataaccess?autoReconnect=true&useSSL=false" ;
	private static String LOGIN = "root";
	private static String PASSWORD ="SlyFun007";
	public Connection connect;
	public Statement stat;
	
	public DAOJavaPetri() {
		this.connect=null;
		this.stat=null;	
	}
	
	public boolean open() {
			 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 this.connect = DriverManager.getConnection(DAOJavaPetri.URL, DAOJavaPetri.LOGIN, DAOJavaPetri.PASSWORD);
			 this.stat = this.connect.createStatement();
			 } catch (final ClassNotFoundException e) {
			 e.printStackTrace();
			 return false;
			 } catch (final SQLException e) {
			 e.printStackTrace();
			 return false;
			 }
			 return true;
			 }
		
	
	public void close() throws SQLException {
		this.stat.close();
		this.connect.close();
	}
	
	
	
	public JavaPetri getJavaPetriByld(int id) {
		
		return null;}
	
	
	public void insertJavaPetri(JavaPetri jp) throws SQLException {
		 this.stat.executeUpdate(QueryJavaPetri.getQueryInsert(jp), Statement.RETURN_GENERATED_KEYS);
			 if
			(this.stat.executeUpdate(QueryJavaPetri.getQueryInsert(jp), Statement.RETURN_GENERATED_KEYS) == 1) {
			 final int idJp = this.getLastId();
			 if (idJp != -1) {  for (final JavaCell javaCell :
				 jp.getJavaCells()) {

				 this.stat.executeUpdate(QueryJavaCell.getQueryInsert(javaCell, idJp), Statement.RETURN_GENERATED_KEYS);
				 
				 }
				 }
				 }
	}
	

	private int getLastId() {
		 try {
		 ResultSet resultSet = this.stat.getGeneratedKeys();
		 if (resultSet.next()) {
		 return resultSet.getInt(1);
		 }
		 } catch (SQLException e) {
		 e.printStackTrace();
		 }
		 return 0;
		 }
	
	
	
	
	
	private ResultSet executeQuery(String Query) {
		return null;}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
