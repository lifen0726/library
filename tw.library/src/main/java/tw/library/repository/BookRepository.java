package tw.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    // 可以在這裡定義自定義的查詢方法
}

