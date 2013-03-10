package com.tqi;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
@ContextConfiguration(locations={"classpath:/hibernateTestConfig.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DaoTest {

	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private MascotService mascotService;
	
	// Send using 10 concurrent threads
    private ExecutorService executor = Executors.newFixedThreadPool(2);
    
    private int count = 0;
    
    private long tempoInicial;
	
	@Before
	public void before() {
		
	}

	@After
    public void after() {
        
    }
	
	@Test
	@Transactional(rollbackFor={AppException.class})
    public void testGetName() {

		tempoInicial = System.currentTimeMillis();

		for (int i=0; i<500; i++) {
			executor.execute(new Runnable() {
				public void run() {
					try {
						count++;
						
						if (count >= 500) {
							long tempoFinal = System.currentTimeMillis();
							
							System.out.println("TEMPO DE EXECUCAO: " + (tempoFinal - tempoInicial) );
					        return;
						}
						
						MascotBean mascot = mascotService.findOne(1 + (int)(Math.random() * 2));
						
						// Popula o bean
						VoteBean vote = new VoteBean();
						vote.setDateCreate(new Date(System.currentTimeMillis()));
						vote.setMascoteBean(mascot);
						
						// Salva
						voteService.save(vote);
						
						System.out.println(count);

					} catch (AppException e) {
						e.printStackTrace();
					}
				}
			});
		}

		try {
			executor.shutdown();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
