package gottesman.scheduler;

import java.util.ArrayList;
import java.util.Collections;

/*Process first enters the holding queue, ages until its priority reaches the level of the 
 * processes in the active queue, and then added to the active queue**/

public class SelfishRoundRobinScheduler extends RoundRobinScheduler {

	private ArrayList<Job> holdingQueue;

	public SelfishRoundRobinScheduler(ArrayList<Job> jobs) {
		super(new ArrayList<Job>());

		this.holdingQueue = jobs;
	}

	@Override
	public void run() {

		Job lastJob = null;

		while (!jobs.isEmpty()) {

			increasePriority();
			activateJob();

			Collections.sort(jobs, comparator);
			Job job = jobs.get(0);

			int actualTimeSlice = executeJob(job);

			totalTime += actualTimeSlice;

			if (job != lastJob) {
				totalTime += OVERHEAD;
				lastJob = job;
			}
		}
	}

	private void increasePriority() {

		for (Job job : holdingQueue) {
			Priority priority = job.getPriority();
			if (priority.name().equals(Priority.RealTime)) {
				jobs.add(job);
			} else {
				Priority nextPriority = Priority.values()[priority.ordinal() + 1];
				job.setPriority(nextPriority);
			}
		}
	}

	private void activateJob() {

		for (Job job : holdingQueue) {
			if (job.getPriority().ordinal() >= jobs.get(0).getPriority().ordinal()) {
				jobs.add(job);
			}
		}
	}
}
