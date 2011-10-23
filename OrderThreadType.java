public class OrderThreadType{
//this class is built assuming that everything is fine
public int tO;
public String name;
public int texp;
public String type;
public int qty;
public String stock;
public int price;
public int partial;

/*public char log;
public int status;
*/

public static OrderThreadType copy(OrderThreadType obj){
	return (new OrderThreadType(obj.tO, obj.name, obj.texp, obj.type, obj.qty, obj.stock, obj.price, obj.partial));
}

public OrderThreadType(){
	this.tO=0;
	this.name=null;
	this.texp=0;
	this.type=null;
	this.qty=0;
	this.stock=null;
	this.price=0;
	this.partial=0;
/*	this.log='a';
	this.status=0;
*/
}

public OrderThreadType(int tOinput,String nameinput,int texpinput,String typeinput,int qtyinput,String stockinput,int priceinput,int partialinput){
	tO=tOinput;
	name=nameinput;
	texp=texpinput;
	type=typeinput;
	qty=qtyinput;
	stock=stockinput;
	price=priceinput;
	partial=partialinput;
/*	log='a';
	status=0;
*/
	}

}