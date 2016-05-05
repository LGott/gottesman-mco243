package gottesman.scheduler;

import java.util.ArrayList;

/*Processes are dispatched FIFO but are
given a limited amount of processor time**/

public class RoundRobinScheduler extends JobScheduler {
	private int timeSliceLeft = TIME_SLICE;

	public RoundRobinScheduler(ArrayList<Job> jobs) {
		super(jobs, new FifoJobComparator());
	}

	//Process the first job in the list with a time limit. If job is not finished, 
	//return it to the back of the queue
	
	@Override
	public void run() {
		while (jobs.size() != 0) {
			Job job = jobs.remove(0);
			totalTime += executeJob(job);
			if (job.getTimeLeft() <= 0) {
				jobs.add(job);
			}
		}
	}
}