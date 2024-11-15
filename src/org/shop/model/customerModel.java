package org.shop.model;

import java.util.Arrays;

public class customerModel {
	private String custName;
	private int oid; // order id
	private String proName[];
	private int quantity[];
	private int totalPrice[];
	private int bill;

	

	public customerModel() {
	}

	public customerModel(String custName, int oid, String[] proName, int[] quantity,int[] totalPrice) {
		this.custName = custName;
		this.proName = proName;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.oid = oid;
	}
	
	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	public int[] getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int[] totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String[] getProName() {
		return proName;
	}

	public void setProName(String[] proName) {
		this.proName = proName;
	}

	public int[] getQuantity() {
		return quantity;
	}

	public void setQuantity(int[] quantity) {
		this.quantity = quantity;
	}


}
