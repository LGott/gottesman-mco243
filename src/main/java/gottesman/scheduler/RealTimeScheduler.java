package gottesman.scheduler;

import java.util.ArrayList;
import java.util.Comparator;

public class RealTimeScheduler extends JobScheduler {

	public RealTimeScheduler(ArrayList<Job> jobs) {
		super(jobs, new DeadlineComparator());
		// TODO Auto-generated constructor stub
	}

}
