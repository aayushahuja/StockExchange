import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class OutWriter {
	
	BufferedWriter out;
	BufferedWriter cleanOut;
	public OutWriter() {
		try {
			out = new BufferedWriter(new FileWriter("exchange.out"));
			cleanOut = new BufferedWriter(new FileWriter("cleanup.out"));
		} catch (IOException e) {}
	}
	
	public synchronized void writeLine(String line){
		try {
			out.write(line);
			out.newLine();
		} catch (IOException e) {}
	}
	public void cleanWriteLine(String line){
		try {
			cleanOut.write(line);
			cleanOut.newLine();
		} catch (IOException e) {}
	}
		
}
