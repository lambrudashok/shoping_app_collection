package org.shop.client;

import org.shop.model.customerModel;
import org.shop.model.productModel;
import org.shop.service.customerService;
import org.shop.service.productService;
import java.util.*;
public class shopClientApp {

	public static void main(String[] args) {
		
		productService PSer = new productService();
		
		customerService custSer = new customerService();
		
		
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("\n1.Add new Product");
			System.out.println("2.View All Product");
			System.out.println("3.Search Product");                          
			System.out.println("4.Add New Order");
			System.out.println("5.View All Orders");
			System.out.println("6.Cancel Order");
			System.out.println("7.Modify Order Data");
			System.out.println("8.Create Customer Bill");
			System.out.println("9.View Customer Bill");
			System.out.println("10.Display All Profit");
			
			int choice;
			System.out.println("Enter your choice");
			choice=sc.nextInt();
			
			switch(choice) {
			case 1:
				//Add new Product
				System.out.println("Enter product id ,name and price");
				
				int id=sc.nextInt();
				sc.nextLine();
				String name=sc.nextLine();
				int price=sc.nextInt();
				productModel pmodel = new productModel(id,name,price);
				
				boolean b = PSer.isAddProduct(pmodel);
				if(b) {
					System.out.println("Product add successfully");
				}else {
					System.out.println("Not Added");
				}
				break;
				
			case 2:
				//View All Product
				System.out.println("All Product Details Here !");
				System.out.println("------------------------------------------------------------------");
				PSer.getAllProduct();
				break;
				
			case 3:
				//Search Product
				System.out.println("1.Search by ID\n2.Search by Name\n3.Search by Price");
				int ch=sc.nextInt();
				switch(ch) {
				case 1:
					System.out.println("Enter product ID");
					int idd=sc.nextInt();
					System.out.println("------------------------------------------------------------------");
					int d1=PSer.SearchID(idd);
					if(d1!=1) {
						System.out.println("Product Not Found");
						System.out.println("------------------------------------------------------------------");
					}
					break;
					
				case 2:
					System.out.println("Enter product Name");
					sc.nextLine();
					String nam=sc.nextLine();
					System.out.println("------------------------------------------------------------------");
					int d2=PSer.SearchName(nam);
					if(d2!=1) {
						System.out.println("Product Not Found");
						System.out.println("------------------------------------------------------------------");
					}
					break;
					
				case 3:
					System.out.println("Enter product Price");
					int pri=sc.nextInt();
					System.out.println("------------------------------------------------------------------");
					int d3=PSer.SearchPrice(pri);
					if(d3!=1) {
						System.out.println("Product Not Found");
						System.out.println("------------------------------------------------------------------");
					}
					break;
				
				default:
					System.out.println("Option not found");
				}
				break;
				
			case 4:
				//   Add New Order
				int Lch;
				do {
				System.out.println("1.Login Customer\n2.Registration Customer");
				System.out.println("Enter your choice");
				Lch=sc.nextInt();
				switch(Lch) {
				case 1:                                     // login for customer
					System.out.println("Login Here!");
					System.out.println("Enter Username");
					sc.nextLine();
					String username=sc.nextLine();
					System.out.println("Enter Password");
					int password=sc.nextInt();
					
					boolean bl=custSer.getLoginDetails(username, password);
					if(bl) {
						System.out.println("Login Successfully !\nWelcome back "+username);
						
						System.out.println("Enter order ID");// order for customer
						int oid = sc.nextInt();
						System.out.println("Enter order count"); 
						int count=sc.nextInt();
						
						String pName[] = new String[count]; // product name
						int qty[] = new int[count];         // product quantity
						int totalPri[]=new int[count];      // total price
						
						for(int i=0;i<count ;i++) {
						System.out.println("Enter product name");                           
						pName[i]= new String();
						sc.nextLine();
					    pName[i] = sc.nextLine();
						System.out.println("Enter product quantity");
						qty[i] = sc.nextInt();
						
						String nam=pName[i];
						
						custSer.setProser(PSer);
						
						int bcheck=custSer.checkProduct(nam);  
					// here check product available or not
						if(bcheck!=0) {
							totalPri[i]=bcheck*qty[i];
							System.out.println("product added successfully");
						}else {
							System.out.println("product not available");
							--i;
						    }
						}
						customerModel cm = new customerModel(username,oid,pName,qty,totalPri);
						custSer.setOrderData(cm);
						System.out.println("Order Added successfully");
						
					}else {
						System.out.println("Please Enter correct Username and Password");
					}
				    break;
				
				case 2:                                       // registration for new customer
					System.out.println("Welcome ! new Customer");
					System.out.println("Registration Here!");
					System.out.println("Enter Username");
					sc.nextLine();
					username=sc.nextLine();
					System.out.println("Enter Password");
					password=sc.nextInt();
					custSer.setUserPass(username, password);
					System.out.println("Registration Successful");
					break;
					
				    }
					System.out.println("( 1.continue 0.ExitLogin )");
					Lch=sc.nextInt();
				}while(Lch!=0);
				
				break;// case 4 break
			
			case 5:
				System.out.println("Displayed All Customers Orders");
				System.out.println("------------------------------------------------------------------");
				custSer.allOrderDisplays();
				
				break;
				
				// Cancel Order
			case 6:
				System.out.println("If We want Cancel Order\nEnter Order ID");
				int OrdID = sc.nextInt();
				boolean c1 = custSer.DeleteOrder(OrdID);
				if(c1) {
					System.out.println("Order Cancel Successfully");
				}else {
					System.out.println("Order Not Cancel");
				}
				break;
				
				// modify customer order data
			case 7:
				System.out.println("Enter Customer username And Order ID");
				sc.nextLine();
				String nameU = sc.nextLine();
				int orID = sc.nextInt();
				boolean m1=custSer.modifyOrderData(nameU,orID);
				if(m1)
				  System.out.println("Updated Successfully");
					else {
					
				}
				
				break;
				
			case 8:
				System.out.println("Generate Bill Here !");
				System.out.println("Enter order no");
				int bil = sc.nextInt();
				int billTotal=custSer.calBill(bil);          // check bill present or not
				
				int GrandBill=billTotal+(int)(billTotal*0.1);
				if(billTotal!=0) {
					System.out.println("Customer Bill Found");
					System.out.println("Pay Total Bill Amount\n$"+GrandBill);
					int conBill=sc.nextInt();
					if(GrandBill==conBill) {
						System.out.println("Thanks ! order Conformed");
						custSer.setPlacedOrder(bil,GrandBill);                    // set placed order data
					}else {
						System.out.println("Not Paid Amount");
					}
					
				}else {
					System.out.printf("Sorry  ! Customer Bill Not Found\n");
				}
				break;
				
			case 9:
				System.out.println("Enter bill no");
				int no=sc.nextInt();
				System.out.println("------------------------------------------------------------------");
				
				int b5=custSer.getPlacedOrdersInfo(no);
				if(b5!=1) {
					System.out.println("Order Not Found");
					System.out.println("------------------------------------------------------------------");
				}
				break;
				
			   // Display All Profit
			case 10:
				System.out.println("All Profit Here !");
				int profitAll=custSer.getAllProfit();
				if(profitAll!=0) {
					System.out.println("==============================");
					System.out.println("Total Profit :$"+profitAll);
					System.out.println("==============================");
				}else {
					System.out.println("Profit not found");
				}
				break;
			
			default:
				System.out.println("Wrong choice");
			}
		}while(true);

	}

}
