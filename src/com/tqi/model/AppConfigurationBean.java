package com.tqi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "AppConfiguration")
public class AppConfigurationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Nome do app
	@Transient
	public static final String APP_NAME = "TQI";
	
	@Id
	@Column(name = "AppConfiguration_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appConfigurationID;
	
	@Column(name="Name", nullable=false)
	private String name = APP_NAME;
	
	@Column(name="ReleaseDateVotePage", nullable=false)
	private Date releaseDateVotePage;
	
	@Column(name="DateUpdate", nullable=false)
	private Date dateUpdate;
	
	@Column(name="DateCreate", nullable=false)
	private Date dateCreate;

	public long getAppConfigurationID() {
		return appConfigurationID;
	}

	public void setAppConfigurationID(long appConfigurationID) {
		this.appConfigurationID = appConfigurationID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Date getReleaseDateVotePage() {
		return releaseDateVotePage;
	}

	public void setReleaseDateVotePage(Date releaseDateVotePage) {
		this.releaseDateVotePage = releaseDateVotePage;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
}
