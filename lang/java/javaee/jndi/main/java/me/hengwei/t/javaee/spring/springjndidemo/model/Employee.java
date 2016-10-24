/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hengwei.t.javaee.spring.springjndidemo.model;

import java.io.Serializable;

/**
 *
 * @author hengwei
 */

public class Employee implements Serializable {

	private static final long serialVersionUID = -7788619177798333712L;
	
	private int id;
	private String name;
	private String role;
	
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
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
