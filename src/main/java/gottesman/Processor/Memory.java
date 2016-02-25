package gottesman.Processor;

public class Memory {

	private String[] memory;

	public Memory(String memory) {

		this.memory = memory.split("");

	}

	public String[] getMemory() {
		return this.memory;
	}

	public void setMemory(String[] memory) {
		this.memory = memory;
	}
}