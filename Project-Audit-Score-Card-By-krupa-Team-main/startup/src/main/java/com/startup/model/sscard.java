package com.startup.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@IdClass(sscardkey.class)
@Table(name="startup_score_card")
public class sscard {

	@Id
	@Column(columnDefinition = "CHAR(100)")
	private String startupname;

	@Id
	@Column(name = "samanual_id")
	private Long id;
	
	@Column(columnDefinition = "CHAR(100)")
	private String dimension;
	
	@Column(columnDefinition = "CHAR(100)")
	private String perspective;
	
	@Column(columnDefinition = "CHAR(100)")
	private String processarea;
	
	@Column
	private double weight;
	
	@Column
	private double maxscore;
	
	@Column
	private double actualscore;
	
	@Column(length=999)
	private String remark;
	
	@Column
	private Date date_process;

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

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getPerspective() {
		return perspective;
	}

	public void setPerspective(String perspective) {
		this.perspective = perspective;
	}

	public String getProcessarea() {
		return processarea;
	}

	public void setProcessarea(String processarea) {
		this.processarea = processarea;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getMaxscore() {
		return maxscore;
	}

	public void setMaxscore(double maxscore) {
		this.maxscore = maxscore;
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

	public Date getDate_process() {
		return date_process;
	}

	public void setDate_process(Date date_process) {
		this.date_process = date_process;
	}

	public sscard(String startupname, Long id, String dimension, String perspective, String processarea, double weight,
			double maxscore, double actualscore, String remark, Date date_process) {
		super();
		this.startupname = startupname;
		this.id = id;
		this.dimension = dimension;
		this.perspective = perspective;
		this.processarea = processarea;
		this.weight = weight;
		this.maxscore = maxscore;
		this.actualscore = actualscore;
		this.remark = remark;
		this.date_process = date_process;
	}

	public sscard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
