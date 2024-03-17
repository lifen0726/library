package tw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.library.model.BorrowingRecord;
import tw.library.model.Inventory;
import tw.library.repository.BorrowingRecordRepository;
import tw.library.repository.InventoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    public BorrowingRecord getBorrowingRecordById(int id) {
        return borrowingRecordRepository.findById(id).orElse(null);
    }

    public BorrowingRecord createBorrowingRecord(BorrowingRecord borrowingRecord) {
        return borrowingRecordRepository.save(borrowingRecord);
    }
    
    public BorrowingRecord borrowBook(int userId, int inventoryId) {
        // 从库存中获取要借出的图书
        Inventory inventory = inventoryRepository.findById(inventoryId).orElse(null);
        BorrowingRecord existingRecord = borrowingRecordRepository.findByUserIdAndInventoryId(userId, inventoryId);

        System.out.println(inventory);
        System.out.println(existingRecord);
        // 如果库存为空或者状态不是在库，返回null表示借书失败
        if (inventory == null || !inventory.getStatus().equals("在庫")) {
            return null;
        }

        // 更新库存状态为出借中
        inventory.setStatus("出借中");
        inventoryRepository.save(inventory);

        // 创建借书记录
        BorrowingRecord newRecord = new BorrowingRecord();
        newRecord.setUserId(userId);
        newRecord.setInventoryId(inventoryId);
        newRecord.setBorrowingTime(new Date());

      //If there's an existing record but the book is already returned, update the existing record
        if (existingRecord != null && existingRecord.getReturnTime() != null) {
        	existingRecord.setBorrowingTime(new Date()); // Reset borrowing time
        	existingRecord.setReturnTime(null); // Reset return time
            return borrowingRecordRepository.save(existingRecord); // Update the existing record
        }
        // 保存借书记录
        return borrowingRecordRepository.save(newRecord);
    }
    


    public BorrowingRecord returnBook(int userId, int inventoryId) {
        BorrowingRecord record = borrowingRecordRepository.findByUserIdAndInventoryId(userId, inventoryId);
        if (record != null) {
            record.setReturnTime(new Date());
            borrowingRecordRepository.save(record);

            // 找到对应的库存记录并更新状态为“在库”
            Inventory inventory = inventoryRepository.findById(inventoryId).orElse(null);
            if (inventory != null) {
                inventory.setStatus("在庫");
                inventoryRepository.save(inventory);
            }

            return record;
        }
        return null;
    }

}

