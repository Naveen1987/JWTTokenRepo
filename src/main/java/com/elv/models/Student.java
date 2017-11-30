package com.elv.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Entity
public class Student implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private String sname;
	private String scourse;
	private double sfee;
	private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getScourse() {
		return scourse;
	}
	public void setScourse(String scourse) {
		this.scourse = scourse;
	}
	public double getSfee() {
		return sfee;
	}
	public void setSfee(double sfee) {
		this.sfee = sfee;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", sname=" + sname + ", scourse=" + scourse + ", sfee=" + sfee + ", email=" + email
				+ "]";
	}
	
}
