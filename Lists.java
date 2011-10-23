//have to just outwriter and duplicate for the other list

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


//now have to make special cases;time tO checking,and increase stock profit

public class Lists{
	
	public int PROFIT=0;//profit also have to change
	public  ArrayList<ExchangeThreadType> sellList=new ArrayList<ExchangeThreadType>();
	public  ArrayList<ExchangeThreadType> buyList=new ArrayList<ExchangeThreadType>();
	ArrayList<Integer> possibleOrders=new ArrayList<Integer>();//only one using
	ArrayList<ExchangeThreadType> listOfTransactions =new ArrayList<ExchangeThreadType>();//using one not making a new one each time
	public ExchangeThreadType buyObj;//sent from exchange thread
	public ExchangeThreadType sellObj;//sent from exchange thread
	ArrayList<Integer> pODeal = new ArrayList<Integer>();//possible orders deal method
	ArrayList<ExchangeThreadType> makeTransDeal = new ArrayList<ExchangeThreadType>();//make transactiion list for deal method
	String line2 = null;
	String line3 = null;
	//transaction ke time have to
	
	public synchronized ArrayList<ExchangeThreadType> sortList(ArrayList<ExchangeThreadType> ls){
		Collections.sort(ls, new Comparator<ExchangeThreadType>(){
		     public int compare(ExchangeThreadType o1,ExchangeThreadType o2){
		         if(o1.old.tO == o2.old.tO)
		             return 0;
		         return o1.old.tO < o2.old.tO ? -1 : 1;
		     }
		});
		return ls;
	}
	public synchronized void dealForSellList(ExchangeThreadType obj) throws Exception{
		buyObj=obj;//internalise ho gaya
		pODeal.clear();
		makeTransDeal.clear();
		pODeal=possibleOrderForSellList(buyObj);
		makeTransDeal=makeTransactionForSellList(pODeal);
		
		//this very imp that this thing is checked buyObj.old.qty-buyObj.qty
		if(makeTransDeal.size()>1){
			makeTransDeal = sortList(makeTransDeal);	//sort list according to time
				for(int i =0;i<makeTransDeal.size();i++){
				ExchangeThreadType temp = makeTransDeal.get(i);
				line2 = "T"+"\t" + Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000)+"\t"+Integer.toString(temp.qtyTrans)+"\t"+Integer.toString(temp.old.tO)+"\t"+temp.old.name+"\t"+Integer.toString(temp.old.texp)+"\t"+temp.old.type+"\t"+Integer.toString(temp.old.qty)+"\t"+temp.old.stock+"\t"+Integer.toString(temp.old.price)+"\t"+Integer.toString(temp.old.partial);
				simulate.out.writeLine(line2);
				//time us point ka should have	;separate field should have
			}
			//send to output file
		}
		if(makeTransDeal.size()==1 || buyObj.old.qty-buyObj.qty>0){
			buyList.add(buyObj);
			line2 = "P"+"\t" + Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000)+"\t"+Integer.toString(buyObj.qty)+"\t"+Integer.toString(buyObj.old.tO)+"\t"+buyObj.old.name+"\t"+Integer.toString(buyObj.old.texp)+"\t"+buyObj.old.type+"\t"+Integer.toString(buyObj.old.qty)+"\t"+buyObj.old.stock+"\t"+Integer.toString(buyObj.old.price)+"\t"+Integer.toString(buyObj.old.partial);
			simulate.out.writeLine(line2);
		}
			
			}
	private synchronized ArrayList<ExchangeThreadType> makeTransactionForSellList(ArrayList<Integer> posOrders) throws Exception{
			listOfTransactions.clear();
			int max=-1;
			int position=0;
			int qty=0;
			boolean found=false;
			Integer temp;
			do{found=false;//do while is for multiple passes
				System.out.print("buyObj");
				if((buyObj.old.texp+buyObj.old.tO) < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000){
					listOfTransactions.add(buyObj);
					return listOfTransactions;
				}
				for(int i=0;i<possibleOrders.size();i++){
					temp=posOrders.get(i);//is an integer link only
					if((sellList.get(temp).old.texp+sellList.get(temp).old.tO) < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000){
						continue;
					}
					if(sellList.get(temp).old.qty-sellList.get(temp).qty<=0){continue;}
					if(buyObj.old.qty==sellList.get(temp).old.qty){
						if((buyObj.old.qty*buyObj.old.price) - (sellList.get(temp).old.qty*sellList.get(temp).old.price) >max){
						max=(buyObj.old.qty*buyObj.old.price) - (sellList.get(temp).old.qty*sellList.get(temp).old.price);
						position=i;
						qty=buyObj.old.qty;
						found=true;
						}
					}
					else if(buyObj.old.qty>sellList.get(temp).old.qty && buyObj.old.partial==1){
						if(buyObj.old.price*sellList.get(temp).old.qty - sellList.get(temp).old.price*sellList.get(temp).old.qty>max){
						max=buyObj.old.price*sellList.get(temp).old.qty - sellList.get(temp).old.price*sellList.get(temp).old.qty;
						position=i;
						qty=sellList.get(temp).old.qty;
						found=true;
						}
					}
					else if(buyObj.old.qty<sellList.get(temp).old.qty && sellList.get(temp).old.partial==1){
						if(buyObj.old.price*buyObj.old.qty - sellList.get(temp).old.price*buyObj.old.qty>max){
						max=buyObj.old.price*buyObj.old.qty - sellList.get(temp).old.price*buyObj.old.qty;
						position=i;
						qty=buyObj.old.qty;
						found=true;
						}
					}
					else{continue;}	
				}	
					if(found == true){
					listOfTransactions.add(sellList.get(possibleOrders.get(position))); 
					PROFIT=PROFIT+((buyObj.old.price - sellList.get(possibleOrders.get(position)).old.price)*qty);
					buyObj.qty=buyObj.qty+qty;
					buyObj.qtyTrans=qty;
					sellList.get(possibleOrders.get(position)).qty=sellList.get(possibleOrders.get(position)).qty+qty;
					sellList.get(possibleOrders.get(position)).qtyTrans=qty;
					System.out.println(sellList.get(position).old.name + sellList.get(possibleOrders.get(position)).old.name);
					}
				}while(found==true && ((buyObj.old.qty-buyObj.qty)>0));
					listOfTransactions.add(buyObj);
				
			return listOfTransactions;	
		}
	private synchronized ArrayList<Integer> possibleOrderForSellList(ExchangeThreadType buyobj){
			possibleOrders.clear();
			for(int i=0;i<sellList.size();i++){
				if(sellList.get(i).old.stock.equals(buyobj.old.stock) && (buyobj.old.price>=sellList.get(i).old.price)){
					possibleOrders.add(i);}	
				}
			return possibleOrders;
			}

	//-----------------------------------------------SELL object list methods
	public synchronized void dealForBuyList(ExchangeThreadType obj) throws Exception{
		sellObj=obj;//internalise ho gaya
		
		pODeal.clear();
		makeTransDeal.clear();
		pODeal=possibleOrderForBuyList(sellObj);
		makeTransDeal=makeTransactionForBuyList(pODeal);
		
		if(makeTransDeal.size()>1){
		
			makeTransDeal = sortList(makeTransDeal);	//sort list according to time
				for(int i =0;i<makeTransDeal.size();i++){
				ExchangeThreadType temp = makeTransDeal.get(i);
				line2 = "T"+"\t" + Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000)+"\t"+Integer.toString(temp.qtyTrans)+"\t"+Integer.toString(temp.old.tO)+"\t"+temp.old.name+"\t"+Integer.toString(temp.old.texp)+"\t"+temp.old.type+"\t"+Integer.toString(temp.old.qty)+"\t"+temp.old.stock+"\t"+Integer.toString(temp.old.price)+"\t"+Integer.toString(temp.old.partial);
				simulate.out.writeLine(line2);
				//time us point ka should have	;separate field should have
			}
		}
		if(makeTransDeal.size()==1 || sellObj.old.qty-sellObj.qty>0){
			System.out.print("he"+makeTransDeal.size());
			sellList.add(sellObj);
			line2 = "S"+"\t" + Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000)+"\t"+Integer.toString(sellObj.qty)+"\t"+Integer.toString(sellObj.old.tO)+"\t"+sellObj.old.name+"\t"+Integer.toString(sellObj.old.texp)+"\t"+sellObj.old.type+"\t"+Integer.toString(sellObj.old.qty)+"\t"+sellObj.old.stock+"\t"+Integer.toString(sellObj.old.price)+"\t"+Integer.toString(sellObj.old.partial);
			simulate.out.writeLine(line2);
			}//this very imp that this thing is checked buyObj.old.qty-buyObj.qty
		}
	private synchronized ArrayList<ExchangeThreadType> makeTransactionForBuyList(ArrayList<Integer> posOrders) throws Exception{
			listOfTransactions.clear();
			int max=-1;
			int position=0;
			int qty=0;
			boolean found=false;
			Integer temp;
			do{found=false;//do while is for multiple passes
				if((sellObj.old.texp+sellObj.old.tO) < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000){
					listOfTransactions.add(sellObj);
					return listOfTransactions;
				}
				for(int i=0;i<possibleOrders.size();i++){
					temp=posOrders.get(i);//is an integer link only
					if((buyList.get(temp).old.texp+buyList.get(temp).old.tO) < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000){
						//buyList.get(temp).qty=buyList.get(temp).old.qty;//so that cleanup cleans it up
						continue;
					}
					if((buyList.get(temp).old.qty - buyList.get(temp).qty)<=0){continue;}
					if(sellObj.old.qty==buyList.get(temp).old.qty){
						
						if((sellObj.old.qty*sellObj.old.price) - (buyList.get(temp).old.qty*buyList.get(temp).old.price) >max){
						max=(sellObj.old.qty*sellObj.old.price) - (buyList.get(temp).old.qty*buyList.get(temp).old.price);
						position=i;
						qty=sellObj.old.qty;
						found=true;
						}
					}
					else if(sellObj.old.qty>buyList.get(temp).old.qty && sellObj.old.partial==1){
						if(sellObj.old.price*buyList.get(temp).old.qty - buyList.get(temp).old.price*buyList.get(temp).old.qty>max){
						max=sellObj.old.price*buyList.get(temp).old.qty - buyList.get(temp).old.price*buyList.get(temp).old.qty;
						position=i;
						qty=buyList.get(temp).old.qty;
						found=true;
						}
					}
					else if(sellObj.old.qty<buyList.get(temp).old.qty && buyList.get(temp).old.partial==1){
						if(sellObj.old.price*sellObj.old.qty - buyList.get(temp).old.price*sellObj.old.qty>max){
						max=sellObj.old.price*sellObj.old.qty - buyList.get(temp).old.price*sellObj.old.qty;
						position=i;
						qty=sellObj.old.qty;
						found=true;
						}
					}
					else{continue;}	
				}		
					if(found == true){
					listOfTransactions.add(buyList.get(possibleOrders.get(position)));
					PROFIT=PROFIT+((buyList.get(position).old.price - sellObj.old.price)*qty);
					sellObj.qtyTrans=qty;
					buyList.get(possibleOrders.get(position)).qtyTrans=qty;
					sellObj.qty=sellObj.qty+qty;
					buyList.get(possibleOrders.get(position)).qty=buyList.get(possibleOrders.get(position)).qty+qty;
					}
				}while(found==true && ((sellObj.old.qty-sellObj.qty)>0));
				
					listOfTransactions.add(sellObj);
				
			return listOfTransactions;	
		}
	private synchronized ArrayList<Integer> possibleOrderForBuyList(ExchangeThreadType sellobj){
			possibleOrders.clear();
			for(int i=0;i<buyList.size();i++){
				if(buyList.get(i).old.stock.equals(sellobj.old.stock) && (sellobj.old.price<=buyList.get(i).old.price)){
					possibleOrders.add(i);}	
				}
			System.out.print("beep"+possibleOrders.size());	
			return possibleOrders;
			}
	
	public synchronized void cleanup(){
		//scan in both lists
		//notifyAll(); perhaps dont need this,coz wait notify when temporarily want to transfer control
		//if found then remove and write log also
		int i = 0;
		while(i<buyList.size()){	
			if(((buyList.get(i).old.qty-buyList.get(i).qty)<=0) || ((buyList.get(i).old.texp+buyList.get(i).old.tO) < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000)){
				line3=ExtractOrderThread.getLine(buyList.get(i).old,(System.currentTimeMillis()-Order.simulationStartTime)/1000);
				simulate.out.cleanWriteLine(line3);
				buyList.remove(i);
				}
			i++;//else{i++;}
			}
		i = 0;
		while(i<sellList.size()){	
			if(((sellList.get(i).old.qty-sellList.get(i).qty)<=0) || ((sellList.get(i).old.texp+sellList.get(i).old.tO )< (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000)){
			line3=ExtractOrderThread.getLine(sellList.get(i).old,(System.currentTimeMillis()-Order.simulationStartTime)/1000);
			simulate.out.cleanWriteLine(line3);
			sellList.remove(i);
			}
			i++;//else{i++;}//not in if block coz elements khud shifted by 1
		}
		}
	
	}
