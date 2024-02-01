	
	
	import java.util.Scanner;
	
	
class CashRegister
	{
	
	String itemName;
	int quantity;
	float price;

	CashRegister(String name,int quant,float p)
	{
	this.itemName= name;
	this.quantity = quant;
	this.price = p;
	}

	float getSubtotal(int quant,float p)
	{

	return (p*quant);
	}

	float getTax(float subtotal)
	{

	return (subtotal*6)/100;
	}

	float getTotal (float subtotal,float tax)
	{

	return (subtotal+tax);
	}
	}

	public class CashRegisterDemo {
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
	  
	Scanner in=new Scanner(System.in); 

	String name;
	int quantity, available;
	float price,subtotal,tax,total;

	System.out.println("We need information about the retail item.");
	System.out.println("What is the name of the item?");
	name = in.next();
	do{
	System.out.println("How many units are available? ");
	available = in.nextInt();

	if(available<1)
	System.out.println("Invalid Entry. Please try again.");
	}while(available<1);
	  
	do{
	System.out.println("How much does the item cost per unit?");
	price = in.nextFloat();
	  

	if(price<1)
	System.out.println("Invalid Entry. Please try again.");
	}while(price<1);
	  
	do{
	System.out.println("How many items are you going to purchase?");
	quantity = in.nextInt();
	  

	if(quantity<1)
	System.out.println("Invalid Entry. Please try again.");
	}while(quantity<1);
	

	CashRegister cr = new CashRegister(name,quantity,price);
	  

	subtotal =cr.getSubtotal(quantity, price);
	tax = cr.getTax(subtotal);
	total = cr.getTotal(subtotal, tax);
	  

	System.out.println("\nSubtotal: "+ subtotal +"\nTax: "+tax+ "\nTotal: "+total);
	  
	}
	  
	}