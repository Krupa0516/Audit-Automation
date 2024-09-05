package com.startup.model;

import java.io.Serializable;
import java.util.Objects;

public class saatrackerKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startupname;
	private Long id;

	public saatrackerKey() {
	}

	public saatrackerKey(String startupname, Long id) {
		this.startupname = startupname;
		this.id = id;
	}

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

	// equals and hashCode methods
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof saatracker))
			return false;
		saatracker that = (saatracker) o;
		return Objects.equals(getStartupname(), that.getStartupname()) && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStartupname(), getId());
	}

}
