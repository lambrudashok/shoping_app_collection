package org.shop.service;

import java.util.ArrayList;

import org.shop.model.productModel;
import org.shop.repository.productRepository;

public class productService {

	productRepository PR = new productRepository();
	
	// logic for Add new product
	public boolean isAddProduct(productModel pmodel) {
		return PR.isAddProduct(pmodel);
	}
	
	//logic for case 2 Display All product
	public void getAllProduct() {
		ArrayList info = PR.getProduct();
		for(Object obj:info) {
			productModel p = (productModel)obj;
			System.out.println("Product ID :"+p.getId()+"\tProduct Name :"+p.getName()+"\tProduct Price :"+p.getPrice());
		}
		System.out.println("------------------------------------------------------------------");
	}
	
	
	//logic for case 3 search by id
	public int SearchID(int idd) {
         ArrayList info = PR.getProduct();
         int flag=0;
		for(Object obj:info) {
			productModel p = (productModel)obj;
			if(idd==p.getId()) {
				System.out.println("Product ID :"+p.getId()+"\tProduct Name :"+p.getName()+"\tProduct Price :"+p.getPrice());
				System.out.println("------------------------------------------------------------------");
				flag=1;
				break;
			}	
		}
		return flag;
	}
	
	// logic for search name
	public int SearchName(String nam) {
		int flag=0;
        ArrayList info = PR.getProduct();
		for(Object obj:info) {
			productModel p = (productModel)obj;
			if(nam.equals(p.getName())) {
				System.out.println("Product ID :"+p.getId()+"\tProduct Name :"+p.getName()+"\tProduct Price :"+p.getPrice());
				System.out.println("------------------------------------------------------------------");
				flag=1;
				break;
			}
		}
		return flag;
	}
	
	// logic for search by price
	public int SearchPrice(int pri) {
		int flag = 0;
        ArrayList info = PR.getProduct();
		for(Object obj:info) {
			productModel p = (productModel)obj;
			if(pri==p.getPrice()) {
				System.out.println("Product ID :"+p.getId()+"\tProduct Name :"+p.getName()+"\tProduct Price :"+p.getPrice());
				System.out.println("------------------------------------------------------------------");
				flag=1;
			}
		}
		return flag;
	}
	
	public productRepository getpepo() {
		return PR;
	}
	
}
