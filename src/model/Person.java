package model;

import java.io.Serializable;

public class Person implements Serializable {
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private String name;
private int age;
private int salary;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}

}
