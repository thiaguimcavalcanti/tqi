package com.tqi.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tqi.model.AppConfigurationBean;
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
				// Busca o bean selecionado
				MascotBean mascot = listMascots.get(index);
				
				// Popula o bean
				VoteBean vote = new VoteBean();
				vote.setDateCreate(new Date(System.currentTimeMillis()));
				vote.setMascoteBean(mascot);
				
				// Salva
				voteService.save(vote);
				
				// Redireciona o usuario para a pagina de resultados
				AppConfigurationBean appConfig = super.getAppConfig();
				
				if (appConfig != null && appConfig.getReleaseDateVotePage().compareTo(new Date()) <= 0) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("results.xhtml");
				} else {
					// Mostra uma mensagem de sucesso para o usuario
				}
				
			} catch (AppException e) {
				createViewMessage(e.getEnumMessage());
			} catch (IOException e) {
				e.printStackTrace(); // Erro ao tentar redirecionar o usuario para a pagina de resultados.
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