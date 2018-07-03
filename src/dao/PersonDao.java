package dao;

import org.springframework.stereotype.Service;

import model.Person;
import unit.MysqlDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonDao {
	public static List<Person> getPersonarraylist(){
		
	ArrayList<Person> arrayList = new ArrayList<Person>();
	try {
		Connection connct = MysqlDataBase.getConnct();
		String sql="select id,name,age,salary from user";
		Statement createStatement = connct.createStatement();
		ResultSet executeQuery = createStatement.executeQuery(sql);
		while (executeQuery.next()) {
			Person oneperson = new Person();
			oneperson.setId(executeQuery.getInt(1));
			oneperson.setName(executeQuery.getString(2));
			oneperson.setAge(executeQuery.getInt(3));
			oneperson.setSalary(executeQuery.getInt(4));
			
			arrayList.add(oneperson);
		}
		MysqlDataBase.getClose(connct, createStatement, executeQuery);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        return arrayList;
	}
	
	
	public static void insert(Person p) {
		try {
			Connection connct = MysqlDataBase.getConnct();
			String sql="insert into user VALUES ("+null+",'"+p.getName()+"',"+p.getAge()+","+p.getSalary()+")";
			Statement createStatement = connct.createStatement();
			createStatement.execute(sql);
			createStatement.close();
			connct.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
	
	public static void update(Person p) {
		try {
			Connection connct = MysqlDataBase.getConnct();
			String sql="UPDATE user SET name="+"'"+p.getName()+"'"+",age="+"'"+p.getAge()+"'"+",salary="+"'"+p.getSalary()+"'"+" WHERE id="+p.getId()+"";
			Statement createStatement = connct.createStatement();
			createStatement.execute(sql);
			createStatement.close();
			connct.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
	
}
