package com.tqi;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tqi.model.MascotBean;
import com.tqi.model.VoteBean;
import com.tqi.service.MascotService;
import com.tqi.service.VoteService;
import com.tqi.util.exceptions.AppException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/tests-config.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class DaoTest {

	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private MascotService mascotService;
	
	// Send using 2 concurrent threads
    private ExecutorService executor = Executors.newFixedThreadPool(2);
    
    private int count = 0;
    
    private long tempoInicial;
    
    @Autowired
	private SessionFactory sessionFactory;
	
	@Before
	public void before() {
		
	}

	@After
    public void after() {
        
    }
	
	/**
	 * Metodo para testar a insercao dos mascotes
	 */
	@Test
	@Transactional(rollbackFor={AppException.class})
	public void testPopulateMascots() {
		try {
			MascotBean mascot1 = new MascotBean();
			mascot1.setName("Amijubi");
			mascot1.setDescription("União das palavras amizade e jubilo, que está ligado ao tupi-guarani, em que jubi significa amarelo, cor predominante no mascote.");
			
			mascotService.save(mascot1);
			
			MascotBean mascot2 = new MascotBean();
			mascot2.setName("Fuleco");
			mascot2.setDescription("Uma mistura de futebol e ecologia. O nome busca incentivar o cuidado das pessoas com o meio ambiente.");
			
			mascotService.save(mascot2);
			
			MascotBean mascot3 = new MascotBean();
			mascot3.setName("Zuzeco");
			mascot3.setDescription("Mistura da cor azul com ecologia, que busca também incentivar cuidados relacionados à ecologia.");
			
			mascotService.save(mascot3);
			
		} catch (AppException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo responsavel por testar 500 requisicoes simultaneas e verifica se o sistema 
	 * comporta estas solicitacoes.
	 * 
	 * Em media: 500 requisicoes sao executadas em 1200 milisegundos | 1 segundo
	 */
	@Test
	@Transactional(rollbackFor={AppException.class})
    public void testVotation() {

		tempoInicial = System.currentTimeMillis();

		for (int i=1; i <= 500; i++) {
			executor.execute(new Runnable() {
				public void run() {
					try {
						
						count++;
						
						// Encerra em 500 e verifica o tempo de execucao
						if (count >= 497) {
							long tempoFinal = System.currentTimeMillis();
							
							System.out.println("TEMPO DE EXECUCAO: " + (tempoFinal - tempoInicial) + " milisegundos ou "
									+ ((tempoFinal - tempoInicial) / 1000) + " segundos");
						}
						
						// Cria um objeto mascote com o item selecionado
						MascotBean mascot = new MascotBean();
						mascot.setMascotID(1 + (int)(Math.random() * 2));
						
						// Popula o bean
						VoteBean vote = new VoteBean();
						vote.setDateCreate(new Date(System.currentTimeMillis()));
						vote.setMascoteBean(mascot);
						
						// Salva
						voteService.save(vote);
						
					} catch (AppException e) {
						e.printStackTrace();
					}
				}
			});
		}

		try {
			Thread.sleep(60000); // Faz com que o teste aguarde a conclusao das threads
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
