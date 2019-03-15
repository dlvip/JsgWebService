package com.old.time.repository;

import com.old.time.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    /**
     * 是否已经存在
     *
     * @param isbn
     * @return
     */
    boolean existsByIsbn10OrIsbn13(String isbn, String isbn13);

    /**
     * 获取图书信息
     *
     * @param isbn
     * @return
     */
    BookEntity findBookEntityByIsbn10OrIsbn13(String isbn, String isbn13);

    /**
     * 获取图书信息
     *
     * @param id
     * @return
     */
    BookEntity findBookEntityById(Integer id);

}
