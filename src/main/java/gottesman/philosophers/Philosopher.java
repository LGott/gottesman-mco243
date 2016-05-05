package gottesman.philosophers;

import java.util.Random;

public class Philosopher extends Thread {

	private Fork f1;
	private Fork f2;
	private String name;
	final Random RAND = new Random();

	public Philosopher(String name, Fork f1, Fork f2) {
		this.name = name;
		this.f1 = f1;
		this.f2 = f2;

	}

	public void run() {
		while (true) {
			eat();
			think();
		}
	}

	public void eat() {
		synchronized (f1) {
			synchronized (f2) {
				System.out.println(this + " trying to pick up " + f1);
				System.out.println(this + " trying to pick up " + f2);
				System.out.println(name + " is eating");
				waitForAFewSeconds();
			}
		}
	}

	public void think() {
		f1.putDown();
		System.out.println(this + " put down " + f1);
		f2.putDown();
		System.out.println(this + " put down " + f2);
		System.out.println(name + " is done eating");
		System.out.println(name + " is thinking");
		waitForAFewSeconds();
	}

	private void waitForAFewSeconds() {
		try {
			Thread.sleep((long) RAND.nextInt(10000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Philosopher [name=" + name + "]";
	}

}