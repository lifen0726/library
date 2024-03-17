package tw.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "Borrowing_Records")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Borrowing_Records_id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "inventory_id")
    private int inventoryId;

    @Column(name = "borrowing_time")
    private Date borrowingTime;

    @Column(name = "return_time")
    private Date returnTime;

    // Getters and Setters
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public int getUserId() {
        return userId;
    }

	public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Date getBorrowingTime() {
        return borrowingTime;
    }

    public void setBorrowingTime(Date borrowingTime) {
        this.borrowingTime = borrowingTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "userId=" + userId +
                ", inventoryId=" + inventoryId +
                ", borrowingTime=" + borrowingTime +
                ", returnTime=" + returnTime +
                '}';
    }

    public BorrowingRecord() {
        super();
    }
}
