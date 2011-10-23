import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import exceptions.*;
public class Order implements Runnable{
	
	public static long simulationStartTime;
	public static BufferedReader in;
	public void run() {
		
		OrderThreadType obj=null;
		String line=null;
		String line2 = null;
		Date date1 = new Date();
		Order.simulationStartTime = date1.getTime();
		
		BufferedWriter out=null;
		
		try {
			
			//in = new BufferedReader((new FileReader("/home/comp/Desktop/sem/code/testcases/request2.dat")));
			out = new BufferedWriter(new FileWriter("order.out"));
			while((line=in.readLine()) != null ){
			
				try{
				obj = ExtractOrderThread.extractData(line);}
				catch(TONotAnInteger e){
					line2="EXCEPTION:tONotAnInteger"+"\t"+line;
					out.write(line2);
					out.newLine();
					continue;
				}
				catch(NameNotAString e){
					line2="EXCEPTION:NameNotAString"+"\t"+line;
					out.write(line2);
					out.newLine();
					continue;
				}
				catch(TexpNotAnInteger e){
					line2="EXCEPTION:TexpNotAnInteger"+"\t"+line;
					out.write(line2);
					out.newLine();
					continue;
				}
				catch(TypeNotAString e){
					line2="EXCEPTION:Invalid 'Type'"+"\t"+line;
					out.write(line2);
					out.newLine();
					continue;
				}
				catch(QtyNotAnInteger e){
					line2="EXCEPTION:QtyNotAnInteger"+"\t"+line;
					out.write(line2);
					out.newLine();
					continue;
				}
				catch(StockNotAString e){
					line2="EXCEPTION:StockNotAString"+"\t"+line;
					out.write(line2);
					out.newLine();
					continue;
				}
				catch(PriceNotAnInteger e){
					line2="EXCEPTION:PriceNotAnInteger"+"\t"+line;
					out.write(line2);
					out.newLine();
					continue;
				}
				catch(PartialNotABoolean e){
					line2="EXCEPTION:PartialNotABoolean"+"\t"+line;
					out.write(line2);
					out.newLine();
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				} 
				//System.out.println(System.currentTimeMillis() + " "+Order.simulationStartTime+" "+(System.currentTimeMillis()-Order.simulationStartTime)/1000  + " "+ (System.currentTimeMillis()-Order.simulationStartTime));
				if(obj.tO == (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000)//to check whether date1 changes 
				{
					//System.out.print(obj.tO+"\t"+"hi");
					//System.out.println((int)(System.currentTimeMillis()-Order.simulationStartTime)/1000);
					//add obj to queue
					simulate.queue.push(obj);//no checking etc done
					//add a log entry in a file
						line2 =ExtractOrderThread.getLine(obj,(System.currentTimeMillis()-Order.simulationStartTime)/1000); 
						out.write(line2);
						out.newLine();
					}
				else if(obj.tO < (int)(System.currentTimeMillis()-Order.simulationStartTime)/1000){
					//add a log in the file that time exceeded
					
				//	System.out.println("exceeded");
				}	
				else {//System.out.println("sleeping");
					Thread.sleep(obj.tO*1000 - (System.currentTimeMillis()-Order.simulationStartTime) );
					
					simulate.queue.push(obj);
					line2 = ExtractOrderThread.getLine(obj,(System.currentTimeMillis()-Order.simulationStartTime)/1000);
					out.write(line2);
					out.newLine();
				}
				//System.out.println("         ");
				
			}
			simulate.INPUTFILEEMPTY=true;
			in.close();
			out.close();
			
			/*obj=simulate.queue.pop();
			System.out.println(obj.name);
			
			for(int i = 0;i<simulate.queue.size();i++){
				System.out.println(simulate.queue.elementAtPosition(i).name + simulate.queue.size());
			}*/
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		
	}
}
