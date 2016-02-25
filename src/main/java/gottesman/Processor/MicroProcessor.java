package gottesman.Processor;

public class MicroProcessor {

	private String[] memory;
	private String accumulatorA;
	private String accumulatorB;
	private int index;

	public MicroProcessor(Memory memoryArray) {

		this.memory = memoryArray.getMemory();
		this.accumulatorA = "0";
		this.accumulatorB = "0";
		this.index = 0;
	}

	public String[] getMemory() {
		return this.memory;
	}

	public void processMemory() {

		while ((index < memory.length) && (Integer.parseInt(memory[index]) != 8)) {

			switch (Integer.parseInt(memory[index])) {

			case 0:
				load();
				break;
			case 1:
				store();
				break;
			case 2:
				swap();
				break;
			case 3:
				add();
				break;
			case 4:
				increment();
				break;
			case 5:
				decrement();
				break;
			case 6:
				bz();
				break;
			case 7:
				br();
				break;
			case 8:
				return;

			}
		}

	}

	public void load() {

		// LD: Load accumulator A with the contents of memory at the specified
		// argument.

		// Parse the contents of the specified memory locations
		int address1 = Integer.parseInt(memory[index++], 16);
		int address2 = Integer.parseInt(memory[index++], 16);

		address1 *= 16;

		// Add the contents of the two addresses and load accumulator A
		// with the memory slot contents of the new address

		int newAddress = address1 + address2;
		accumulatorA = memory[newAddress];

		index++;

	}

	public void store() {

		// ST: Write the contents of accumulator A to the memory location
		// specified by the argument.

		int address1 = Integer.parseInt(memory[index++], 16);
		int address2 = Integer.parseInt(memory[index++], 16);

		address1 *= 16; // Change to hex

		int newAddress = address1 + address2; // get the new address
		memory[newAddress] = accumulatorA;

		index++;

	}

	public void swap() {

		// Save the contents of AccumulatorA into a temp variable
		String tempSwap = accumulatorA;

		// Swap A & B
		accumulatorA = accumulatorB;
		accumulatorB = tempSwap;
		index++;

	}

	public void add() {
		// ADD: Add the contents of accumulators A and B.
		// The low word of the sum is stored in A, and the high word in B

		int a = Integer.parseInt(String.valueOf(accumulatorA), 16);
		int b = Integer.parseInt(String.valueOf(accumulatorB), 16);

		int total = a + b;

		String getHex = Integer.toHexString(total).toUpperCase();

		String[] hex = getHex.split("");

		if (hex.length == 2) {
			accumulatorA = hex[1];
			accumulatorB = hex[0];
		} else {
			accumulatorA = hex[0];
			accumulatorB = "0";
		}

		index++;

	}

	public void increment() {
		// INC: Increment accumulator A.
		// Overflow is allowed; that is, incrementing F yields 0.

		// If accumulatorA is F, then set it equal to 0
		if (accumulatorA.equals("F")) {
			accumulatorA = "0";
		} else {

			int num = Integer.parseInt(String.valueOf(accumulatorA), 16);
			num++;
			accumulatorA = Integer.toString(num).toUpperCase();
		}
		index++;
	}

	public void decrement() {
		// DEC: Decrement accumulator A.
		// Underflow is allowed; that is, decrementing 0 yields F.

		if (accumulatorA.equals("F")) {
			accumulatorA = "0";
		}

		else {
			int num = Integer.parseInt(accumulatorA, 16);
			num--;
			accumulatorA = Integer.toString(num).toUpperCase();
		}
		index++;
	}

	public void bz() {

		// BZ: If accumulator A is zero, the next command to be executed is at
		// the location specified by
		// the argument. If A is not zero, the argument is ignored and nothing
		// happens.

		if (accumulatorA.equals("0")) {

			int address1 = Integer.parseInt(memory[index++], 16);
			int address2 = Integer.parseInt(memory[index++], 16);

			address2 *= 16;

			index = address1 + address2;

		} else {
			index += 3;
		}
	}

	private void br() {

		// BR: The next command to be executed is at the location
		// specified by the argument.

		int address1 = Integer.parseInt(memory[index++], 16);
		int address2 = Integer.parseInt(memory[index++], 16);

		address1 *= 16;

		index = address1 + address2;
	}
}
