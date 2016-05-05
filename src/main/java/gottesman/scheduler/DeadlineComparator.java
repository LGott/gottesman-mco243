package gottesman.scheduler;


import java.util.Comparator;

public class DeadlineComparator implements Comparator<Job>{

	@Override
	public int compare(Job job1, Job job2) {
		return job1.getDeadline().compareTo(job2.getDeadline());
	}

}