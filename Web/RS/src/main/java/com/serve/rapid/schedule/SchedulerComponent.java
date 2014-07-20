package com.serve.rapid.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.serve.rapid.domain.FieldAgent;
import com.serve.rapid.domain.FieldAgentLocation;
import com.serve.rapid.repository.FieldAgentLocationRepository;
import com.serve.rapid.repository.FieldAgentRepository;

@Named("scheduler")
@Component
public class SchedulerComponent {

	@Autowired
	private FieldAgentLocationRepository fieldAgentLocationRepository;

	@Autowired
	private FieldAgentRepository fieldAgentRepository;

	private List<IScheduledComponent> components;

	public SchedulerComponent() {
		this.components = new ArrayList<IScheduledComponent>();
	}

	public void register(IScheduledComponent component) {
		this.components.add(component);
	}

/*	@Scheduled(fixedRate = 5000)
	public void doAllWork() {
		try {
			for (IScheduledComponent component : this.components) {
				component.doWork();
			}
			// TODO Auto-generated method stub
			System.out.println("Scheduled Workkkk");
			for (FieldAgent agent : fieldAgentRepository.findAll()) {
				System.out.println(agent);
				FieldAgentLocation loc = fieldAgentLocationRepository.findByAgentOrderBySeenDesc(agent);
				getRandomLocation(Double.parseDouble(loc.getLongitude()),
						Double.parseDouble(loc.getLatitude()), 1000,
						loc.getAgent());
			}
			System.out.println("SchedulerComponent.doAllWork()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public void getRandomLocation(double x0, double y0, int radius,
			FieldAgent fa) {
		Random random = new Random();

		// Convert radius from meters to degrees
		double radiusInDegrees = radius / 111000f;

		double u = random.nextDouble();
		double v = random.nextDouble();
		double w = radiusInDegrees * Math.sqrt(u);
		double t = 2 * Math.PI * v;
		double x = w * Math.cos(t);
		double y = w * Math.sin(t);

		// Adjust the x-coordinate for the shrinking of the east-west distances
		double new_x = x / Math.cos(y0);

		double foundLongitude = new_x + x0;
		double foundLatitude = y + y0;
		FieldAgentLocation location = new FieldAgentLocation();
		location.setLatitude(Double.toString(foundLatitude));
		location.setLongitude(Double.toString(foundLongitude));
		location.setAgent(fa);
		location.setSeen(new Date());
		fieldAgentLocationRepository.save(location);

	}
}