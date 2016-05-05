package gottesman.scheduler;

import java.util.ArrayList;

/*Selects the waiting process with the smallest estimated
 *  run-time-to-completion.**/

public class SPFScheduler extends JobScheduler {

	public SPFScheduler(ArrayList<Job> jobs) {
		super(jobs, new SPFComparator());
	}

	@Override
	public void run() {

		while (jobs.size() != 0) {
			Job job = jobs.remove(0);
			while (job.getTimeLeft() > 0) {
				totalTime += executeJob(job);
			}
		}
	}
}
