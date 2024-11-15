package org.shop.repository;
import java.util.*;

import org.shop.model.customerModel;
public class customerRegistration {

	ArrayList<ArrayList> login = new ArrayList<ArrayList>(); // for customer login
	
	ArrayList order = new ArrayList();   // for customer order
	
	ArrayList conformOrders = new ArrayList();         // order placed data
	
	public void setLogin(ArrayList usPass) {
		login.add(usPass);
	}
	
	public ArrayList<ArrayList> getLogin() {
		return login;
	}
	
	public void setOrderDetail(customerModel cm) {
		order.add(cm);
	}
	
	public ArrayList getOrders() {
		return order;
	}
	
    public void setConformOrder(customerModel cm) {
          conformOrders.add(cm);		
	}
    
    public ArrayList getPlacedOrder() {
    	return conformOrders;
    }
}
