package org.shop.service;
import java.util.Scanner;
import org.shop.model.customerModel;
import org.shop.model.productModel;
import org.shop.repository.customerRegistration;
import org.shop.repository.productRepository;

import java.util.*;
public class customerService {
	
	customerRegistration custReg = new customerRegistration();
	productRepository prepo=null;
	
	public void setUserPass(String name,int pass) {
		ArrayList newRegister = new ArrayList();
		newRegister.add(name);
		newRegister.add(pass);
		
		custReg.setLogin(newRegister);
	}
	
	public boolean getLoginDetails(String user ,int pass) {      // check login data
		ArrayList<ArrayList> infoLogin = custReg.getLogin();
		boolean flag=false;
		for(int i=0;i<infoLogin.size();i++){
            Object u = infoLogin.get(i).get(0);
            Object p= infoLogin.get(i).get(1);
            
            if(user.equals(u)&& pass==(int)p) {
            	flag=true;
            	break;
            }
         }
		return flag;
		
	}
	
	public void setProser(productService ps) {
		prepo=ps.getpepo();
	}

	
	public int checkProduct(String nam) { 
                                                   //		 check product available or not
        ArrayList info=prepo.getProduct();
		
         int flag=0;
		for(Object obj:info) {
			productModel p = (productModel)obj;
			if(nam.equals(p.getName())) {
				flag=p.getPrice();
				break;
			}
		}
		return flag;
	}
	
	
	
	public void setOrderData(customerModel cm) {    // set all orders details customer wise
	    	custReg.setOrderDetail(cm);
	}
     
	public void allOrderDisplays() {
		ArrayList orderInfo = custReg.getOrders();                                // case 5 : display all order data
		for(Object obj:orderInfo) {
		    customerModel c = (customerModel)obj;
		    
		    System.out.println("Customer Name: "+c.getCustName()+"\n"+"Customer Order ID :"+c.getOid());
		    for(int i=0;i<c.getProName().length;i++) {
		    	System.out.println("Product Name :"+c.getProName()[i]+"\tQuantity :"+c.getQuantity()[i]+"\ttotal Price :"+c.getTotalPrice()[i]);
		    }
		    System.out.println("------------------------------------------------------------------");
		}
	}
	
	
	
	// case 6 : cancel order
	
	public boolean DeleteOrder(int ordID) {
		ArrayList info = custReg.getOrders();
		boolean b =false;
		
		for(Object obj:info) {
			customerModel c = (customerModel)obj;
			if(ordID==c.getOid()) {
				b=info.remove(obj);
				break;
			}
			
		}
		return b;
	}
	
	
	// case 7: modify customer order data
	
	public boolean modifyOrderData(String nameU ,int orID) {
		ArrayList info = custReg.getOrders();
		boolean flag = false;
		for(Object obj:info) {
			customerModel c = (customerModel)obj;
			if(nameU.equals(c.getCustName()) && orID == c.getOid()) {
				int qty[]=new int[c.getQuantity().length];
				int toPri[]= new int[c.getTotalPrice().length];
				
				for(int i=0;i<c.getProName().length;i++) {
					System.out.println("product name :"+c.getProName()[i]+"\tproduct quantity :"+c.getQuantity()[i]);
					System.out.println("Enter new quantity");
					Scanner sc = new Scanner(System.in);
				    qty[i]=sc.nextInt();
				    
				    ArrayList a = prepo.getProduct();
				    int pri=0;                                    // check product name and set price
				    for(Object ob :a) {
				    	productModel p = (productModel)ob;
				    	if(c.getProName()[i].equals(p.getName())) {
				    		pri=p.getPrice();
				    		break;
				    	}
				    }
				    toPri[i]=qty[i]*pri;
				}
				c.setQuantity(qty);
				c.setTotalPrice(toPri);
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	
	public int calBill(int bil) {                            // case 8: calculate total bill
		ArrayList order = custReg.getOrders();
		int totalBill =0;
		for(Object obj:order) {
			customerModel c =(customerModel)obj;
			if(bil==c.getOid()) {
				for(int i=0;i<c.getProName().length;i++) {
					totalBill = totalBill + c.getTotalPrice()[i];
				}	
			}
		}
		return totalBill;
	}
	
	public void setPlacedOrder(int bil, int GrandBill) {            // case 8: stored data placed orders
		ArrayList order = custReg.getOrders();
		
		for(Object obj:order) {
			customerModel c =(customerModel)obj;
			if(c.getOid()==bil) {
				customerModel cm = new customerModel(c.getCustName(),c.getOid(),c.getProName(),c.getQuantity(),c.getTotalPrice());
				cm.setBill(GrandBill);
				custReg.setConformOrder(cm);
			}	
		}
	}
	
	public int getPlacedOrdersInfo(int no) {
		int flag=0;
		ArrayList placedOrders = custReg.getPlacedOrder();         // case 9: display placed orders
		for(Object obj:placedOrders) {
			customerModel c = (customerModel)obj;
			if(no==c.getOid()) {
			System.out.println("Customer Name: "+c.getCustName()+"\n"+"Order Conform ID :"+c.getOid());
		    for(int i=0;i<c.getProName().length;i++) {
		    	System.out.println("Product Name :"+c.getProName()[i]+"\tQuantity :"+c.getQuantity()[i]+"\ttotal Price :"+c.getTotalPrice()[i]);
		    }
		    System.out.println("Status :$"+c.getBill()+" Paid");
		    System.out.println("------------------------------------------------------------------");
		    flag=1;
		    break;
			}
		}
		return flag;
	}
	
	                                                         //case 10: calculate all profit
	public int getAllProfit() {
		ArrayList placed = custReg.getPlacedOrder();
		int profit=0;
		for(Object obj:placed) {
			int totalBill=0;
			customerModel c = (customerModel)obj;
			for(int i=0;i<c.getProName().length;i++) {
				totalBill = totalBill + c.getTotalPrice()[i];
			}
		    profit = profit + (int)(totalBill*0.1);
		}
		return profit;
	}
}
