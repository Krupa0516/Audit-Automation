package com.startup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(samanualkey.class)
@Table(name="startup_assessment_manual")
public class samanual {

	/*
	 * 
startupname            char(100)
processarea            vc(100)
number_of_link         double
actualscore            double
remark                 vc(999)    

	 */
	@Id
	@Column(columnDefinition = "CHAR(100)")
	private String startupname;

	@Id
	@Column(name = "samanual_id")
	private Long id;
    
	@Column(length = 255)
	private String processarea;
	
	public String getProcessarea() {
		return processarea;
	}

	public void setProcessarea(String processarea) {
		this.processarea = processarea;
	}

	@Column
	private int number_of_link; 
	
	@Column
	private double actualscore;
	
	@Column(length = 999)
	private String remark;

	public String getStartupname() {
		return startupname;
	}

	public void setStartupname(String startupname) {
		this.startupname = startupname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber_of_link() {
		return number_of_link;
	}

	public void setNumber_of_link(int number_of_link) {
		this.number_of_link = number_of_link;
	}

	public double getActualscore() {
		return actualscore;
	}

	public void setActualscore(double actualscore) {
		this.actualscore = actualscore;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public samanual(String startupname, Long id, String processarea, int number_of_link, double actualscore,
			String remark) {
		super();
		this.startupname = startupname;
		this.id = id;
		this.processarea = processarea;
		this.number_of_link = number_of_link;
		this.actualscore = actualscore;
		this.remark = remark;
	}

	public samanual() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
