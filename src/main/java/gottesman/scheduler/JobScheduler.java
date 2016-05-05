package gottesman.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class JobScheduler implements Runnable {


	protected final int TIME_SLICE = 10;
	protected final int OVERHEAD = 3;

	protected ArrayList<Job> jobs;
	protected Comparator<Job> comparator;
	protected static int totalTime;
	protected static int numJobsCompleted;
	private JobType jobType;
	private static Random random = new Random();

	public JobScheduler(ArrayList<Job> jobs, Comparator<Job> comparator) {
		this.comparator = comparator;
		this.jobs = jobs;
		numJobsCompleted = 0;
		totalTime = 0;
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

	public int executeJob(Job job) {
		job.setState(JobState.Running);
		job.setLastRanAtTime(System.currentTimeMillis());
		int timeLeftToRun = job.getTimeLeft();
		int actualTimeSlice = computeActualTimeSlice(job, timeLeftToRun);
		timeLeftToRun -= actualTimeSlice;

		job.setTimeLeft(timeLeftToRun);

		if (timeLeftToRun == 0) {
			jobs.remove(0);
			numJobsCompleted++;
		} else {
			job.setState(JobState.Ready);
		}
		return actualTimeSlice;
	}

	protected int computeActualTimeSlice(Job job, int timeLeftToRun) {
		int actualTimeSlice;
		if (job.getJobType() == JobType.IO) {
			actualTimeSlice = Math.min(random.nextInt(TIME_SLICE), timeLeftToRun);
		} else {
			actualTimeSlice = Math.min(TIME_SLICE, timeLeftToRun);
		}
		return actualTimeSlice;
	}

	public static void main(String[] args) {
		List<Job> jobs = Arrays.asList(

		new Job("1", Priority.High, 50, JobType.Computation, 5L),
		new Job("2", Priority.Low, 90, JobType.IO, 45L),
		new Job("3",Priority.High, 10, JobType.Computation, 3L), 
		new Job("4", Priority.Medium, 100, JobType.Computation, 1L),
		new Job("5", Priority.Medium, 20, JobType.IO, 67L),
		new Job("6", Priority.Low, 100, JobType.Computation, 103L),
		new Job("7", Priority.Low, 10, JobType.IO, 23L), 
		new Job("8", Priority.High, 40, JobType.Computation, 44L),
		new Job("9", Priority.High, 50, JobType.Computation, 51L), 
		new Job("10", Priority.Low, 60, JobType.IO, 18L),
		new Job("11", Priority.Medium, 80, JobType.Computation, 70L), 
		new Job("12", Priority.High, 70, JobType.IO, 49L));
		
		JobScheduler scheduler = new JobScheduler(new ArrayList<Job>(jobs), new PriorityJobComparator());
		scheduler.run();

		System.out.println("PriorityJobScheduler NUM JOBS COMPLETED " + numJobsCompleted + " TOTAL TIME " + totalTime);
		
		RoundRobinScheduler rr = new RoundRobinScheduler(new ArrayList<Job>(jobs));
		rr.run();
		System.out.println("RoundRobbinScheduler NUM JOBS COMPLETED " + numJobsCompleted + " TOTAL TIME " + totalTime);

	}
}