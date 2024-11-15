package org.shop.repository;

import java.util.*;

import org.shop.model.productModel;

public class productRepository {

	ArrayList al = new ArrayList();// PRODUCT LIST
	
	public boolean isAddProduct(productModel pmodel) {
		boolean b = al.add(pmodel);
		if(b) {
			return true;
		}else
		{
			return false;
		}
	}
	
	public ArrayList getProduct() {
		return al;
	}
	

	

	
}
