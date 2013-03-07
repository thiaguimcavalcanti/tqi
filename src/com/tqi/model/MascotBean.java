package com.tqi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Mascot")
public class MascotBean implements Serializable, Comparable<MascotBean> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Mascot_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long mascotID;
	
	@Column(name="Name", nullable = false)
	private String name;
	
	@Column(name="Description")
	private String description;
	
	public MascotBean() {}
	
	public MascotBean(long mascotID) {
		this.mascotID = mascotID;
	}

	public long getMascotID() {
		return mascotID;
	}
	
	public void setMascotID(long mascotID) {
		this.mascotID = mascotID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int compareTo(MascotBean o) {
		return (int) (this.mascotID - o.mascotID);
    }
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        MascotBean guest = (MascotBean) obj;
        return mascotID == guest.mascotID;
    }
}
