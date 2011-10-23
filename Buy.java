import java.util.ArrayList;

public class Buy{
	private  ArrayList<OrderThreadType> buyList=new ArrayList<OrderThreadType>();
	//
	public synchronized void add(OrderThreadType e) {
		buyList.add(e);
		}
	
	/*public synchronized OrderThreadType pop() throws InterruptedException{
	//	if(frontIndex>=rearIndex){
			while(simulate.queue.size()==0){
				wait();
			} 
			OrderThreadType obj = OrderThreadType.copy(queue.get(0));
			queue.remove(0);
			return (obj);
			
			
			//else(return )
			//else{return null;}
	}
*/

	public synchronized int size() {
		return (buyList.size());
	}
	
	public synchronized OrderThreadType elementAtPosition(int x){
		return buyList.get(x);
	}

	public synchronized void deal(OrderThreadType sellObj){
		
	}
}

/*
see once i pick up an order from queue in exchange i dont want cleanup to the list i am working 
so till something concrete is done a lock should be obtained on that list

data structure does not mean creating elements it just means putting them at a good address,just for using them 
in a better way we use different schemes
*/