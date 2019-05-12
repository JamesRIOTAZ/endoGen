package ClassicEndoGen;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * Class create and write to a file
 */
public class WriteFile {
	private String path;//file path

	//Create file
	public WriteFile(String path, boolean append_value){
		this.path = path;
		try {
			FileWriter write = new FileWriter(this.path);
			PrintWriter print_line = new PrintWriter(write);
			print_line.print("");
			print_line.close();
		} catch (IOException e) {
			System.out.println("Error create file : "+this.path);
			e.printStackTrace();
		}
	}

	//Write line to file
	public void writeLineToFile(String textLine, boolean appendToFile){
		try {
			FileWriter write = new FileWriter(path, appendToFile);
			PrintWriter print_line = new PrintWriter(write);
			print_line.println(textLine);
			print_line.close();
		} catch (IOException e) {
			System.out.println("Error write line <"+textLine+"> to: "+path);
			e.printStackTrace();
		}
	}
}
