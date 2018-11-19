package com.barath.app.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="INVENTORY")
public class Inventory implements Serializable {

	private static final long serialVersionUID = 3478349780068265233L;

	@EmbeddedId
    private InventoryId inventoryId;

    @Column(name="PRODUCT_NAME")
    private String productName;
    

    @Column(name="LOCATION_NAME")
    private String locationName;
    

    @Column(name="QUANTITY")
    private int quantity;


	public InventoryId getInventoryId() {
		return inventoryId;
	}


	public void setInventoryId(InventoryId inventoryId) {
		this.inventoryId = inventoryId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Inventory(InventoryId inventoryId, String productName, String locationName, int quantity) {
		super();
		this.inventoryId = inventoryId;
		this.productName = productName;
		this.locationName = locationName;
		this.quantity = quantity;
	}


	


	public Inventory() {
		super();
		
	}


	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", productName=" + productName + ", locationName="
				+ locationName + ", quantity=" + quantity + "]";
	}


    
    
    


}
