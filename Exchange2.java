public class Exchange2{
	private OrderThreadType obj;
	private Integer[] posibleOrdersList;
	private int max;
	
	private static class subList{
	//method returning an array of integers representing positions of the satisfied orders
	private static int[] possibleOrders(OrderThreadType obj2){
		private Integer[] list;
		if(obj2.type.toUpperCase()=="SELL"){look in buy list
			if(buy==empty){return null;or an exception}
			else{while(buy.hasNext()){
				if(buy.next().stock==obj2.stock){
					if( (!buy.next().partial || !obj2.partial) && buy.next().qty!=obj2.qty){continue;}	
					else if((buy.next().qty*buy.next().price) -  (obj2.qty * obj2.price)<0){continue;}
					else{list.add(buy.next());}
					}
				}}
			}//all possible should also satisfy buy-sell>0
			
		else{if(sell==empty){return null;or an exception}
			else{while(sell.hasNext()){
				if(sell.next().stock==obj2.stock){
					if( (!sell.next().partial || !obj2.partial) && sell.next().qty!=obj2.qty){continue;}	
					else if( (obj2.qty * obj2.price) - (sell.next().qty*sell.next().price)<0){continue;}
					else{list.add(sell.next());}
					}
				}}
			}
		return list;
		}
	private static int getBestOrder(Integer[] posOrders,OrderThreadType obj3){
		//buy will give money to stock,and sell will take money from stock so maximise buy-sell
		int max,posOfMax;
		max=0;
		if(obj3.type.touppercase()=="SELL"){
		while(posOrders.hasNext()){
			if( (posOrders.next().qty*posOrders.next().price) -  (obj3.qty * obj3.price)  >max){max=(posOrders.next().qty*posOrders.next().price) -  (obj3.qty * obj3.price);posOfMax=posOrders.getPositionOfTop();}
			
				update array index}
			}
		else{while(posOrders.hasNext()){
			if( (obj3.qty * obj3.price) -  (posOrders.next().qty*posOrders.next().price)  >max){max=(obj3.qty * obj3.price) -  (posOrders.next().qty*posOrders.next().price);posOfMax=posOrders.getPositionOfTop();
			}
			update index of array}
		}	
	return posOfMax;	
	}
	public class run(){
		while(StockQueue.hasNext() || inputfile!=empty){
		obj = StockQueue.pop();//pop an element from queue means delete it also
		if((int)(System.time-Order.simulationStartTime) > obj.texp){
			order expired;
			put a log in file;
			flag=expired;
		}
		else //{if((int)(System.time-Order.simulationStartTime) <= obj.texp) 4 things imp here:name of stock,price,qty,partial nothing else
		{
		look up for a suitable order in the buy,sell lists,have to manage trivial cases
		
		
		if found or many found{possibleOrdersList = subList.possibleOrders(obj);
		bestOrder=subList.getBestOrder(possibleOrdersList,obj);
		//put flag=satisfied for each ,add a log into file,increase the STOCKPROFIT by that amount(global vaala max),some release lock also
		}
		
		else { if(obj.type.touppercase()=="SELL"){add to sell list;list.add(obj)}
			else{add to purchase list;}
			//perhaps dlete it from queue pop it;main logic here only}
		}//this is also assuming that the presnt lists cannot be satisfies further ow a new thread for this also have to create
		
	}	
	


}}
	
	
	
	
	

	/*main thing here
	 * 
	if(sell.size()==0){
		simulate.buy.add(buyObj);
		out.write("P"+ExtractOrderThread.getLine(obj,);
outSell.write("P"+"\t"+Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000))+"\t"+"0"+"\t"+Integer.toString(buyObj.tO)+"\t"+buyObj.name+"\t"+Integer.toString(buyObj.texp)+"\t"+buyObj.type+"\t"+Integer.toString(buyObj.qty)+"\t"+buyObj.stock+"\t"+Integer.toString(buyObj.price)+"\t"+Integer.toString(buyObj.partial));			
		out.newLine();
	}
	else{
		int[] possibleOrders=possibleOrder(buyObj);
		makeTransaction(buyObj,possibleOrders,out);
	}
	
	private synchronized makeTransaction(OrderThreadType obj,int[] possibleOrder,BufferedReader out){
		sortQtyIncreasing(possibleOrder);
		boolean keepDoing=true;
		OrderthreadType temp;
		int i=0;
		while(keepdoing==true && i<possibleOrders.size()){
		temp=possibleOrders.get(i);
		//both parties want the same qty
		if((obj.qty*obj.price) - temp.qty*temp.price >	)
		//buy vaala pehle
		 if(obj.tO < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000){
				out.write("EXPIRED "+ExtractOrderThread.getLine(obj,(System.currentTimeMillis()-Order.simulationStartTime)/1000));
				out.writeLine();
				keepdoing=false;
			}
		else if(temp.tO < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000){
				out.write("EXPIRED "+ExtractOrderThread.getLine(temp,(System.currentTimeMillis()-Order.simulationStartTime)/1000));
				out.writeLine();
				continue;
			}	
1)		else if(obj.qty==temp.qty && obj.price>=temp.price ){
			out.write("T"+"\t"+Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000))+"\t"+Integer.toString(obj.qty)+"\t"+Integer.toString(temp.tO)+"\t"+temp.name+"\t"+Integer.toString(temp.texp)+"\t"+temp.type+"\t"+Integer.toString(temp.qty)+"\t"+temp.stock+"\t"+Integer.toString(temp.price)+"\t"+Integer.toString(temp.partial));
			out.writeLine();
			out.write("T"+"\t"+Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000))+"\t"+Integer.toString(obj.qty)+"\t"+Integer.toString(obj.tO)+"\t"+obj.name+"\t"+Integer.toString(obj.texp)+"\t"+obj.type+"\t"+Integer.toString(obj.qty)+"\t"+obj.stock+"\t"+Integer.toString(obj.price)+"\t"+Integer.toString(obj.partial));
			out.writeLine();
			temp.qty==0;		
			keepdoing=false;
			continue;		
			}
2)		else if(obj.qty==temp.qty && obj.price<temp.price ){
			continue;
			}
3)		else if(obj.qty<temp.qty && obj.price<=temp.price){
			continue;
			}
4)		else if(obj.qty<temp.qty && obj.price>temp.price && temp.partial==1){
			if(obj.qty*obj.price - obj.qty*temp.price>=0){
				out.write("T"+"\t"+Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000))+"\t"+Integer.toString(obj.qty)+"\t"+Integer.toString(temp.tO)+"\t"+temp.name+"\t"+Integer.toString(temp.texp)+"\t"+temp.type+"\t"+Integer.toString(temp.qty)+"\t"+temp.stock+"\t"+Integer.toString(temp.price)+"\t"+Integer.toString(temp.partial));
				out.writeLine();
				out.write("T"+"\t"+Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000))+"\t"+Integer.toString(obj.qty)+"\t"+Integer.toString(obj.tO)+"\t"+obj.name+"\t"+Integer.toString(obj.texp)+"\t"+obj.type+"\t"+Integer.toString(obj.qty)+"\t"+obj.stock+"\t"+Integer.toString(obj.price)+"\t"+Integer.toString(obj.partial));
				out.writeLine();
				temp.qty=temp.qty-obj.qty;		
				keepdoing=false;
				continue;
			}
5)		else if(obj.qty>temp.qty &&)			
			}
			
		}
		
		
		
		
		
		
			
		partial and qty are imp;
	if(temp.partial==0 && obj.partial==0){
		if(temp.qty!=obj.qty){continue;}
		else if (temp.qty==obj.qty){//>max}
		}
			
	else if(temp.partial==0 && obj.partial==1){
		
		}
	
	
	
	
	
	else if(temp.partial==1 && obj.partial==0){}
	else if(temp.partial==1 && obj.partial==1){}
	
		
		//possibleorders has buy price>=sell price
		if(obj.qty==temp.qty){
			if((obj.qty*obj.price) - (temp.qty*temp.price) >max){
			..
			}
		else if(obj.qty>temp.qty && obj.partial==1){
			if(obj.price*temp.qty - temp.price*temp.qty>max){...........}
			}
		else if(obj.qty<temp.qty && temp.partial==1){
			if(obj.price*obj.qty - temp.price*obj.qty>max){...........}
			}
		else{continue;}	
		
	}*/
	
	
	
	

	
	if((sellObj.old.texp+sellObj.old.tO) < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000){
		
		/*if(makeTransDeal.size()==0){
			//print line expired
			//line2="EXPIRED "+ExtractOrderThread.getLine(sellObj.old,(System.currentTimeMillis()-Order.simulationStartTime)/1000);
			//simulate.out.writeLine(line2);
		}*/
		/*else if(makeTransDeal.size()==1 && makeTransDeal.get(0)==sellObj){
			line2="EXPIRED "+ExtractOrderThread.getLine(sellObj.old,(System.currentTimeMillis()-Order.simulationStartTime)/1000);
			simulate.out.writeLine(line2);
		}*/
		if(makeTransDeal.get(makeTransDeal.size()-1)!=sellObj){
			makeTransDeal.add(sellObj);
			}
			makeTransDeal = sortList(makeTransDeal);
		
			for(int i =0;i<makeTransDeal.size();i++){
			
				ExchangeThreadType temp = makeTransDeal.get(i);
				line2 = "T"+"\t" + Long.toString((System.currentTimeMillis()-Order.simulationStartTime)/1000)+"\t"+Integer.toString(temp.qty)+"\t"+Integer.toString(temp.old.tO)+"\t"+temp.old.name+"\t"+Integer.toString(temp.old.texp)+"\t"+temp.old.type+"\t"+Integer.toString(temp.old.qty)+"\t"+temp.old.stock+"\t"+Integer.toString(temp.old.price)+"\t"+Integer.toString(temp.old.partial);
				simulate.out.writeLine(line2);
				//time us point ka should have	;separate field should have
				
			}
			line2="EXPIRED "+ExtractOrderThread.getLine(sellObj.old,(System.currentTimeMillis()-Order.simulationStartTime)/1000);
			simulate.out.writeLine(line2);
			}
		//also send that buyObj is expired
		sellObj.old.qty=sellObj.qty;//so that subsequent lines are not printed
		makeTransDeal.clear();//so that subsequent lines are not printed
	
	
	
	
	
//why inputfile!=empty,queue may get empty but inputfile may have accounts to be accessed after some time??
//lock at the queue is required coz of changes in the numbering of the list
//cleanup and exchange bilkkul saath mai nahi especially if queue implementation is such that it involves moving the members
//so postitioning tof the above instructions is very imp,so have to plan using the thread examples
