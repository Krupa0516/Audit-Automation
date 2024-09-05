package com.startup.model;

import javax.persistence.*;
@Entity
@Table(name="startup")
public class startup {

	@Column(columnDefinition = "CHAR(20)")
	private String startupcode;

	@Column(columnDefinition = "ChAR(100)")
	private String startupcategory;
	
	@Column
	private int srno;
	
	@Id
	@Column(length=100)
	private String startupname;
	
	@Column(length=100)
	private String website;
	
	public String getStartupcode() {
		return startupcode;
	}
	public void setStartupcode(String startupcode) {
		this.startupcode = startupcode;
	}
	public String getStartupcategory() {
		return startupcategory;
	}
	public void setStartupcategory(String startupcategory) {
		this.startupcategory = startupcategory;
	}
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public String getStartupname() {
		return startupname;
	}
	public void setStartupname(String startupname) {
		this.startupname = startupname;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public startup(String startupcode, String startupcategory, int srno, String startupname, String website) {
		super();
		this.startupcode = startupcode;
		this.startupcategory = startupcategory;
		this.srno = srno;
		this.startupname = startupname;
		this.website = website;
	}
	public startup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
