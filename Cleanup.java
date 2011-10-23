import java.io.IOException;

public class Cleanup implements Runnable{

		
	public void run(){
			while(simulate.lsts.buyList.size()!=0 || simulate.lsts.sellList.size()!=0 || simulate.INPUTFILEEMPTY==false){
				simulate.lsts.cleanup();
				/*try {
					Thread.sleep(1000);
					} catch (InterruptedException e) {}*/
			}
			//this marks the end of the simulation
			try {
				simulate.out.writeLine(Integer.toString(simulate.lsts.PROFIT));
				simulate.out.out.close();
				simulate.out.cleanOut.close();
			} catch (IOException e) {}
		
	}
}//thread cannot throw exception

/*//i am doing one deletion at a time
T0: 	The time at which the order is placed. The time is in seconds from the beginning of the simulation. Assume orders are sorted by T0 in the file.
Name: 	Name of the person placing the order
Texp: 	The number of seconds that the order remains valid.
Type: 	Can be buy or sell (case insensitive).
Qty: 	The number of stocks to sell or purchase.
Stock: 	The name of the stock for sell or purchase
Price: 	The per unit price bid.*/
