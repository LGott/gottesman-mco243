package gottesman.scheduler;

import java.util.Comparator;

public class SPFComparator implements Comparator<Job>{

	@Override
	public int compare(Job a, Job b) {
		return a.getTimeLeft().compareTo(b.getTimeLeft());
	}
}