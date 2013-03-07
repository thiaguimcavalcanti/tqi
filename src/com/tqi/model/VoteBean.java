package com.tqi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Vote")
public class VoteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Vote_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long voteID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Mascot_ID", referencedColumnName = "Mascot_ID", nullable = false)
	private MascotBean mascoteBean;
	
	@Column(name="DateCreate", nullable=false)
	private Date dateCreate;

	public long getVoteID() {
		return voteID;
	}

	public void setVoteID(long voteID) {
		this.voteID = voteID;
	}

	public MascotBean getMascoteBean() {
		return mascoteBean;
	}

	public void setMascoteBean(MascotBean mascoteBean) {
		this.mascoteBean = mascoteBean;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
}
