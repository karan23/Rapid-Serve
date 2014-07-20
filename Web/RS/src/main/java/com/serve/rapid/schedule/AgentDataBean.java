package com.serve.rapid.schedule;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.serve.rapid.domain.FieldAgent;
import com.serve.rapid.domain.FieldAgentLocation;
import com.serve.rapid.repository.FieldAgentLocationRepository;

@Named("agentData")
@Scope("application")
public class AgentDataBean implements Serializable,IScheduledComponent {

	@Autowired
	private FieldAgentLocationRepository fieldAgentLocationRepository;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doWork() {
		// TODO Auto-generated method stub
		System.out.println("Scheduled Workkkk");
		for(FieldAgentLocation loc : fieldAgentLocationRepository.findAll()){
			System.out.println(loc);
			
			
		}
		
	}
	
	
	
}