import exceptions.NameNotAString;
import exceptions.PartialNotABoolean;
import exceptions.PriceNotAnInteger;
import exceptions.QtyNotAnInteger;
import exceptions.StockNotAString;
import exceptions.TONotAnInteger;
import exceptions.TexpNotAnInteger;
import exceptions.TypeNotAString;


public class ExtractOrderThread {

	private static OrderThreadType obj1 = new OrderThreadType();
	
public static String getLine(OrderThreadType obj,long time){
	String line;
	line=Long.toString(time)+"\t"+Integer.toString(obj.tO)+"\t"+obj.name+"\t"+Integer.toString(obj.texp)+"\t"+obj.type+"\t"+Integer.toString(obj.qty)+"\t"+obj.stock+"\t"+Integer.toString(obj.price)+"\t"+Integer.toString(obj.partial);
	return line;
}
	
public static OrderThreadType extractData(String line) throws Exception{
	String[] tokens;
	tokens=line.split("\\t");
	
	try{
		obj1.tO=Integer.valueOf(tokens[0]);
	}
	catch(NumberFormatException e){throw new TONotAnInteger();}
	try{
		obj1.name=tokens[1];
	}
	catch(Exception e){throw new NameNotAString();}
	try{
		obj1.texp=Integer.valueOf(tokens[2]);
	}
	catch(NumberFormatException e){throw new TexpNotAnInteger();}
	
	try{
		System.out.print(tokens[3]);
		obj1.type=tokens[3];
	//	
		
	}
	catch(Exception e){throw new TypeNotAString();}
	if((tokens[3].toLowerCase().equals("sell")) || (tokens[3].toLowerCase().equals("buy"))){}
	else{throw new TypeNotAString();}
	
	try{
		obj1.qty=Integer.valueOf(tokens[4]);
	}
	catch(NumberFormatException e){throw new QtyNotAnInteger();}
	try{
		obj1.stock=tokens[5];
	}
	catch(Exception e){throw new StockNotAString();}
	try{
		obj1.price=Integer.valueOf(tokens[6]);
	}
	catch(NumberFormatException e){throw new PriceNotAnInteger();}
	
	
	/*if(obj1.partial!=1 || obj1.partial!=0){throw new PartialNotABoolean();}*/
	
	try{
		obj1.partial=Integer.valueOf(tokens[7]);
		System.out.print(obj1.partial);
	}
	catch(NumberFormatException e){throw new PartialNotABoolean();}
	if(obj1.partial>1 || obj1.partial<0){throw new PartialNotABoolean();}
	
	
	
	
	
	return (new OrderThreadType(Integer.valueOf(tokens[0]),tokens[1],Integer.valueOf(tokens[2]),tokens[3],Integer.valueOf(tokens[4]),tokens[5],Integer.valueOf(tokens[6]),Integer.valueOf(tokens[7])));
	
}}
