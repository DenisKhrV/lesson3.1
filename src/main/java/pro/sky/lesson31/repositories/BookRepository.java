package pro.sky.lesson31.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.lesson31.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
