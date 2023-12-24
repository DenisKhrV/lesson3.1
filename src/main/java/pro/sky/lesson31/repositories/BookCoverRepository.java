package pro.sky.lesson31.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.lesson31.model.BookCover;

import java.util.Optional;

public interface BookCoverRepository extends JpaRepository<BookCover, Long> {

    Optional<BookCover> findByBookId(Long bookId);

}
