package tw.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.library.model.BorrowingRecord;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {

	BorrowingRecord findByUserIdAndInventoryId(int userId, int inventoryId);
}
