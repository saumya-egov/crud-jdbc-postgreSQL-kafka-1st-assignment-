package com.newproject.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.newproject.model.Student;
@Component
public class StudentDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Student> findAll() throws JSONException, SQLException{
		
		String query = "SELECT * FROM student";
		return (List<Student>) jdbcTemplate.query(query,new ResultSetExtractor<List<Student>>(){  
		    public List<Student> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<Student> list=new ArrayList<Student>();  
		        while(rs.next()){  
		        	Student e=new Student();  
		        e.setId(rs.getString(1));  
		        e.setName(rs.getString(2));  
		      
		        list.add(e);  
		        }  
		        return list;  
		        }  
		    });  

	}
	 public String saveRecord(String name,String id) {
         
	        
		String insertSql ="INSERT INTO student (name,id) VALUES (?,?)";
		
	        int row = jdbcTemplate.update(insertSql,name,id);
	        System.out.println(row + " row inserted.");
	        return row + " row inserted.";
	         
	    }
	 public List<Student> searchrecord(String id) throws JSONException, SQLException{
			
			String query = "SELECT * FROM student where id='"+id+"'";
			return (List<Student>) jdbcTemplate.query(query,new ResultSetExtractor<List<Student>>(){  
			    public List<Student> extractData(ResultSet rs) throws SQLException,  
			            DataAccessException {  
			      
			        List<Student> list=new ArrayList<Student>();  
			        while(rs.next()){  
			        	Student e=new Student();  
			        e.setId(rs.getString(1));  
			        e.setName(rs.getString(2));  
			      
			        list.add(e);  
			        }  
			        return list;  
			        }  
			    });  

		}
	 public String updateRecord(String id,String name) {
      
			String insertSql ="update student set name =? where id=?;";
					 
		        System.out.println(insertSql);

		        jdbcTemplate.batchUpdate(insertSql,name,id);
		       
		        return " row updated.";
		         
		    }
	 public String deleteRecord(String id) {
         
			String insertSql ="delete from student where id=?;";
			
		        int row = jdbcTemplate.update(insertSql,id);
		        System.out.println(row + " row deleted.");
		        return " row deleted.";
		         
		    }
	

}
