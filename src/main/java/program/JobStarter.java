package program;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.JobDetail;

import static org.quartz.SimpleScheduleBuilder.*;

public class JobStarter implements ServletContextListener{
/*singleton
fai partire thread 
uno per avvisare che scade abbonamento
uno per mandare mail
*/
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("partito");
	 // Grab the Scheduler instance from the Factory
	  Scheduler scheduler = null;
	try {
		scheduler = StdSchedulerFactory.getDefaultScheduler();
	} catch (SchedulerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  // and start it off
	  try {
		scheduler.start();
	} catch (SchedulerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  // define the job and tie it to our MyJob class
	  JobDetail job = newJob(Job1.class)
	      .withIdentity("job1", "group1")
	      .build();
	  
	  JobDetail job2 = newJob(Job2.class)
		      .withIdentity("job2", "group1")
		      .build();
	  // Trigger the job to run now, and then repeat every 40 seconds
	  Trigger trigger = newTrigger()
	      .withIdentity("trigger1", "group1")
	      .startNow()
	      .withSchedule(simpleSchedule()
	    		  .withIntervalInHours(24)
	              .repeatForever())
	      .build();
	  Trigger trigger2 = newTrigger()
		      .withIdentity("trigger2", "group1")
		      .startNow()
		      .withSchedule(simpleSchedule()
		    		  .withIntervalInHours(24)//controllo mail ogni giorno
		              .repeatForever())
		      .build();
	  // Tell quartz to schedule the job using our trigger
	  try {
		scheduler.scheduleJob(job2, trigger);
		scheduler.scheduleJob(job, trigger2);

	} catch (SchedulerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.out.println("andati");
	  
	  
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
