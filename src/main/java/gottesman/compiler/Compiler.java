package gottesman.compiler;

public class Compiler {

	public Compiler() {

	}

	public String translateCode(String line) {

		StringBuilder builder = new StringBuilder();
		String[] code = line.split(" ");

		switch (code[0]) {

		case "LD":
			builder.append("0");
			builder.append(Integer.toHexString(Integer.parseInt(code[1])).toUpperCase());
			break;
		case "ST":
			builder.append("1");
			builder.append(Integer.toHexString(Integer.parseInt(code[1])).toUpperCase());
			break;
		case "SWP":
			builder.append("2");
			break;
		case "ADD":
			builder.append("3");
			break;
		case "INC":
			builder.append("4");
			break;
		case "DEC":
			builder.append("5");
			break;
		case "BZ":
			builder.append("6");
			builder.append(Integer.toHexString(Integer.parseInt(code[1])).toUpperCase());
			break;
		case "BR":
			builder.append("7");
			builder.append(Integer.toHexString(Integer.parseInt(code[1])).toUpperCase());
			break;
		case "STP":
			builder.append("8");
			break;
		case "DATA":
			builder.append((code[1]));
			break;
		}

		return builder.toString();
	}
}
