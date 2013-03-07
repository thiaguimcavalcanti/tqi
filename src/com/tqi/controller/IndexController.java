package com.tqi.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import com.tqi.model.MascotBean;
import com.tqi.model.VoteBean;
import com.tqi.service.MascotService;
import com.tqi.service.VoteService;
import com.tqi.util.exceptions.AppException;

@ManagedBean
@RequestScoped
public class IndexController extends CommonController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{mascotService}")
	private MascotService mascotService;
	
	@ManagedProperty(value="#{voteService}")
	private VoteService voteService;
	
	private List<MascotBean> listMascots = new ArrayList<MascotBean>();
	
	private long selectedMascotID;
	
	@PostConstruct
	public void initView() {
		try {
			listMascots = mascotService.findAll();
		} catch (AppException e) {
			createViewMessage(e.getEnumMessage());
		}
	}
	
	public void submit(ActionEvent event) { 

		// Busca a posicao do mascote selecionado na lista
		int index = listMascots.indexOf(new MascotBean(selectedMascotID));
		
		if (index > -1) {
			try {
				MascotBean mascot = listMascots.get(index);
				
				VoteBean vote = new VoteBean();
				vote.setDateCreate(new Date(System.currentTimeMillis()));
				vote.setMascoteBean(mascot);
				
				voteService.save(vote);
			} catch (AppException e) {
				createViewMessage(e.getEnumMessage());
			}
		}
	}
	
	public MascotService getMascotService() {
		return mascotService;
	}
	
	public void setMascotService(MascotService mascotService) {
		this.mascotService = mascotService;
	}
	
	public VoteService getVoteService() {
		return voteService;
	}
	
	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}
	
	public List<MascotBean> getListMascots() {
		return listMascots;
	}
	
	public void setListMascots(List<MascotBean> listMascots) {
		this.listMascots = listMascots;
	}
	
	public long getSelectedMascotID() {
		return selectedMascotID;
	}
	
	public void setSelectedMascotID(long selectedMascotID) {
		this.selectedMascotID = selectedMascotID;
	}
}