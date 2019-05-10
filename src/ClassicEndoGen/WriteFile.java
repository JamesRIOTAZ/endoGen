package ClassicEndoGen;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {
	private String path;
	private boolean append_to_file = false;

	public WriteFile(String file_path) {
		path = file_path;
	}

	public WriteFile(String file_path, boolean append_value) throws IOException {
		path = file_path;
		append_to_file = append_value;

		FileWriter write = new FileWriter(path);
		PrintWriter print_line = new PrintWriter(write);
		print_line.print("");
		print_line.close();
	}

	public void writeToFile(String textLine) throws IOException {
		FileWriter write = new FileWriter(path, append_to_file);
		PrintWriter print_line = new PrintWriter(write);

		print_line.println(textLine);
		print_line.close();
	}
}
