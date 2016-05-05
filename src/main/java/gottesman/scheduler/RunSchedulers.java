package gottesman.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunSchedulers {

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
				System.out.println("PriorityJobScheduler NUM JOBS COMPLETED " + JobScheduler.numJobsCompleted + " TOTAL TIME " + JobScheduler.totalTime);
				
				RoundRobinScheduler rr = new RoundRobinScheduler(new ArrayList<Job>(jobs));
				rr.run();
				System.out.println("RoundRobinScheduler NUM JOBS COMPLETED " + RoundRobinScheduler.numJobsCompleted + " TOTAL TIME " +RoundRobinScheduler.totalTime);

				DeadlineScheduler deadline = new DeadlineScheduler(new ArrayList<Job>(jobs));
				deadline.run();
				System.out.println("DeadlineScheduler NUM JOBS COMPLETED " + DeadlineScheduler.numJobsCompleted + " TOTAL TIME " +DeadlineScheduler.totalTime);

			}
}
