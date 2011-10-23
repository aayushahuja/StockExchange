

public class Exchange implements Runnable {
	OrderThreadType obj = new OrderThreadType();
	String line2 = null;
	
	
	public void run() {
			String temp = null;
		try {
			
			while(simulate.INPUTFILEEMPTY!=true || (simulate.queue.size()!=0)){
				obj=simulate.queue.pop();
				ExchangeThreadType obj2=new ExchangeThreadType(obj);
				if((obj.texp+obj.tO) < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000){
					/*line2 = "EXPIRED "+ExtractOrderThread.getLine(obj,(System.currentTimeMillis()-Order.simulationStartTime)/1000);
					simulate.out.writeLine(line2);*/
					continue;
				}	
				else {		
					temp = obj2.old.type;
					if(temp.toLowerCase().equals("buy")){
							simulate.lsts.dealForSellList(obj2);
					}
					else{
						simulate.lsts.dealForBuyList(obj2);
						System.out.print("sellobj ");
						//perhaps have ensured that list already cannot be satisfied further
					}	
				}
			}
		}
		catch (Exception e){}
		
	
	}
}