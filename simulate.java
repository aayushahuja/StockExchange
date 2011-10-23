import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

//all central facilities go through here
public class simulate {
	public static Thread orderThread = new Thread( new Order());
	public static Thread exchangeThread = new Thread( new Exchange());
	public static Thread cleanupThread = new Thread(new Cleanup());
	public static StockQueue queue = new StockQueue();
	public static Lists lsts=new Lists();//one for both
	public static OutWriter out = new OutWriter();
//	public static Sell sell=new Sell();
	public static boolean LISTUSING=true;
	//public static boolean SELLAVAILABLE=true;
	//public static boolean BUYAVAILABLE=true;
	public static boolean INPUTFILEEMPTY = false;
	public static void main(String[] args) throws FileNotFoundException {
		Order.in=new BufferedReader(new FileReader(args[0]));	
		orderThread.start();
		exchangeThread.start();
		cleanupThread.start();
	}

}
