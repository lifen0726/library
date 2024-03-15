package com.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Inventory")
public class Inventory {

    @Id
    private int inventoryId;
    private String ISBN;
    private LocalDateTime storeTime;
    private String status;
    
    // Getters and Setters
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public LocalDateTime getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(LocalDateTime storeTime) {
		this.storeTime = storeTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", ISBN=" + ISBN + ", storeTime=" + storeTime + ", status="
				+ status + "]";
	}
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
    
}

