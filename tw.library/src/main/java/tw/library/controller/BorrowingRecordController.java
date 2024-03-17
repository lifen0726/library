package tw.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tw.library.model.BorrowingRecord;
import tw.library.service.BorrowingRecordService;

import java.util.List;

@RestController
@RequestMapping("/borrowing-records")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping
    public ResponseEntity<List<BorrowingRecord>> getAllBorrowingRecords() {
        List<BorrowingRecord> borrowingRecords = borrowingRecordService.getAllBorrowingRecords();
        return new ResponseEntity<>(borrowingRecords, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecord> getBorrowingRecordById(@PathVariable("id") int id) {
        BorrowingRecord borrowingRecord = borrowingRecordService.getBorrowingRecordById(id);
        if (borrowingRecord != null) {
            return new ResponseEntity<>(borrowingRecord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/borrow/{userId}/{inventoryId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable("userId") int userId,
                                                      @PathVariable("inventoryId") int inventoryId) {
        BorrowingRecord borrowingRecord = borrowingRecordService.borrowBook(userId, inventoryId);
        if (borrowingRecord != null) {
            return new ResponseEntity<>(borrowingRecord, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/return-book/{userId}/{inventoryId}")
    public BorrowingRecord returnBook(@PathVariable int userId, @PathVariable int inventoryId) {
        return borrowingRecordService.returnBook(userId, inventoryId);
    }

}



