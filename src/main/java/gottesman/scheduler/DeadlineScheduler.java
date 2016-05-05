package gottesman.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*Certain processes are scheduled to be completed by a spe-
cific time or deadline. These processes may have high value if delivered on time and
little or no value otherwise. */

public class DeadlineScheduler extends JobScheduler {

	public DeadlineScheduler(ArrayList<Job> jobs) {
		super(jobs, new DeadlineComparator());
	
	}
	
	@Override
	public void run() {

		Job lastJob = null;
		
		while (!jobs.isEmpty()) {
			Collections.sort(jobs, comparator);
			Job job = jobs.get(0);

			int actualTimeSlice = executeJob(job);

			totalTime += actualTimeSlice;
			
			if(job != lastJob){
				totalTime += OVERHEAD;
				lastJob = job;
			}
		}
	}

}
