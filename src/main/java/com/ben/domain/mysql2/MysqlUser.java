package com.ben.domain.mysql2;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mysqUSER")
public class MysqlUser implements Serializable{
	private static final long serialVersionUID = 6971730097058311486L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
 
    private String name;
 
    @Column(unique = true, nullable = false)
    private String email;
 
    private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
    
}
