package com.barath.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class InventoryId implements Serializable {
	
	private static final long serialVersionUID = 2232237953886472466L;

	@Column(name="INVENTORY_ID")
	private Long inventoryId;
	
	@Column(name="STORE_ID")
	private Long storeId;

	public InventoryId(Long inventoryId, Long storeId) {
		super();
		this.inventoryId = inventoryId;
		this.storeId = storeId;
	}
	
		
	

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public InventoryId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InventoryId [inventoryId=" + inventoryId + ", storeId=" + storeId + "]";
	}
	
	

}
