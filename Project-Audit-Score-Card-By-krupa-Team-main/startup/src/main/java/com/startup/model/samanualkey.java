package com.startup.model;

import java.io.Serializable;
import java.util.Objects;

public class samanualkey implements Serializable {

	private static final long serialVersionUID = 1L;
	private String startupname;
	private Long id;
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

	public samanualkey() {
		
	}
	public samanualkey(String startupname, Long id) {
		super();
		this.startupname = startupname;
		this.id = id;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof samanual))
			return false;
		samanual that = (samanual) o;
		return Objects.equals(getStartupname(), that.getStartupname()) && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStartupname(), getId());
	}
	
}
