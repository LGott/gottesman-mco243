package gottesman.Processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RunProcessor {

	public static void main(String[] args) {

		Memory memory = null;
		MicroProcessor processor;
		String[] processedMemory = null;

		BufferedReader reader;
		try {
			reader = new BufferedReader((new FileReader(new File("mach.in"))));

			String line;
			while (((line = reader.readLine()) != null)) {

				memory = new Memory(line);
				processor = new MicroProcessor(memory);
				processor.processMemory();
				processedMemory = processor.getMemory();

				for (String s : processedMemory) {
					System.out.print(s);
				}
				System.out.println();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
