package program;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static org.quartz.SimpleScheduleBuilder.*;

public class Job2 implements org.quartz.Job {
//Spedisce mail
    public Job2() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
    	System.out.println("partito");
             
    	
    	URL url = null;
			try {
				url = new URL("http://vidyanewsletter-env.w8tprsn3ph.eu-west-2.elasticbeanstalk.com/SendEmail");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            HttpURLConnection con = null;
			try {
				con = (HttpURLConnection) url.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
				con.setRequestMethod("POST");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			int responseCode2 = 0;
			try {
				responseCode2 = con.getResponseCode();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(responseCode2);
        System.out.println("fatto in teroria");
}
}