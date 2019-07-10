package com.old.time.repository;

import com.old.time.domain.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    /**
     * 获取图书列表
     *
     * @param weight
     * @param pageable
     * @return
     */
    List<BookEntity> findRBookEntitiesByWeight(String weight, Pageable pageable);

    /**
     * @param tabId
     * @param pageable
     * @return
     */
    List<BookEntity> findBookEntitiesByTabId(String tabId, Pageable pageable);

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
