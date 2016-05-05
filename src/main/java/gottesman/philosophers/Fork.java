package gottesman.philosophers;

public class Fork {

	private int number;
	private boolean inUse;

	public Fork(int number) {
		this.number = number;
	}

	public void pickUp() {
		inUse = true;
	}

	public void putDown() {
		inUse = false;
	}

	public boolean inUse() {
		return inUse;
	}
	
	public int getForkNum(){
		return number;
	}

	@Override
	public String toString() {
		return "Fork [number=" + number + "]";
	}

}