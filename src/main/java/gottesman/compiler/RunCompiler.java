package gottesman.compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunCompiler {

	public void runCompiler() {

		Compiler compiler = new Compiler();
		InputStream assembly = getClass().getResourceAsStream("/assemblyDecimal.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(assembly));

		try {
			StringBuilder builder = new StringBuilder();
			String line;
			while (((line = reader.readLine()) != null)) {
				builder.append(compiler.translateCode(line));
			}
			System.out.println(builder.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		RunCompiler compiler = new RunCompiler();
		compiler.runCompiler();
	}
}
