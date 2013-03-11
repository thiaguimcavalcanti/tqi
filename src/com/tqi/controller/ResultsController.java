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

import com.tqi.model.AppConfigurationBean;
import com.tqi.model.ResultAnalyticBean;
import com.tqi.service.MascotService;
import com.tqi.service.VoteService;
import com.tqi.util.exceptions.AppException;

@ManagedBean
@RequestScoped
public class ResultsController extends CommonController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{mascotService}")
	private MascotService mascotService;
	
	@ManagedProperty(value="#{voteService}")
	private VoteService voteService;
	
	private List<ResultAnalyticBean> listMascotsResults = new ArrayList<ResultAnalyticBean>();
	
	@PostConstruct
	public void initView() {
		try {
			// Busca as configuracoes do aplicativo
			AppConfigurationBean appConfig = super.getAppConfig();
			
			// Redireciona o usuario para a pagina de resultados se estiver liberado
			if (appConfig != null && appConfig.getReleaseDateVotePage().compareTo(new Date()) <= 0) {
				listMascotsResults = voteService.getVotesResults();
				generatePercents();
			} else {
				FacesContext.getCurrentInstance().getExternalContext().redirect("permission_denied.xhtml");
			}
		} catch (AppException e) {
			createViewMessage(e.getEnumMessage());
		} catch (IOException e) {
			e.printStackTrace(); // Erro ao tentar redirecionar o usuario para a pagina de permissao negada.
		}
	}
	
	/**
	 * Gera o percentual com o montante de votos para cada mascote.
	 */
	public void generatePercents() {
		
		int qtdTotalVotes = 0;
		
		// Armazena a quantidade total de votos
		for (ResultAnalyticBean result : listMascotsResults) {
			qtdTotalVotes += result.getQtdVotes();
		}
		
		// Atribui a porcentagem de cada
		for (ResultAnalyticBean result : listMascotsResults) {
			result.setPercent((result.getQtdVotes() * 100) / qtdTotalVotes);
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
	
	public List<ResultAnalyticBean> getListMascotsResults() {
		return listMascotsResults;
	}
	
	public void setListMascotsResults(List<ResultAnalyticBean> listMascotsResults) {
		this.listMascotsResults = listMascotsResults;
	}
}