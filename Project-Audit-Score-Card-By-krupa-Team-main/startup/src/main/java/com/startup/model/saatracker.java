package com.startup.model;

import java.util.*;
import javax.persistence.*;

@Entity
@IdClass(saatrackerKey.class)
@Table(name="startup_assessment_automation_tracker")
public class saatracker {

	@Id
	@Column(columnDefinition = "CHAR(100)")
	private String startupname;

	@Id
	@Column(name = "saatracker_id")
	private Long id;

	@Column(length = 255)
	private String processarea;

	@Column(length = 999)
	private String linkURL;

	@Column
	private Date dateofprocess;

	@Column(columnDefinition = "CHAR(50)")
	private String status;

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

	public String getProcessarea() {
		return processarea;
	}

	public void setProcessarea(String processarea) {
		this.processarea = processarea;
	}

	public String getLinkURL() {
		return linkURL;
	}

	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}

	public Date getDateofprocess() {
		return dateofprocess;
	}

	public void setDateofprocess(Date dateofprocess) {
		this.dateofprocess = dateofprocess;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public saatracker(String startupname, Long id, String processarea, String linkURL, Date dateofprocess,
			String status) {
		super();
		this.startupname = startupname;
		this.id = id;
		this.processarea = processarea;
		this.linkURL = linkURL;
		this.dateofprocess = dateofprocess;
		this.status = status;
	}

	public saatracker() {
		super();
	}
}
