import java.util.ArrayList;

//arraylist is rightward growing 1st is at 0 then 1,2,3......n n is the last,so pop should give 0 push is at n
public  class StockQueue{

	private  ArrayList<OrderThreadType> queue=new ArrayList<OrderThreadType>();
	//private static int frontIndex=-1;//front to an element 
	//private static int rearIndex=0;//rear also to element
	//private static boolean empty=true;
	
	public synchronized void push(OrderThreadType e) {
		queue.add(e);
		notifyAll();
		//frontIndex++;
		//System.out.println("hi "+frontIndex );
	}
	
	public synchronized OrderThreadType pop() throws InterruptedException{
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

	/*public static synchronized OrderThreadType frontElement() {
	//	if(frontIndex>=rearIndex){
		queue.get(queue.size()));
		//else(return )
		//else{return null;}
	}*/

	/*public static synchronized boolean isEmpty() {
		if(){return true;}
		else{return false;}
	}*/

	public synchronized int size() {
		return (queue.size());
	}
	
	public synchronized OrderThreadType elementAtPosition(int x){
		return queue.get(x);
	}
//Queue implementation
	/*private static STOCKPROFIT=0;
	private static counter;
	public static void push(OrderThreadType obj){
	//sth sth
	counter=counter+1;
	}
	public static void pop(int index){counter=counter-1;}
	public static OrderThreadType front(){
		return front;
	}*/
	}
/*TO DO's 	
1.queue,buy,sell how to implement
2.exchange ka code reorder based on things used in implementing threads
3.file input/output
4.jo methods hai unhe ya equivalent represent
*/	
