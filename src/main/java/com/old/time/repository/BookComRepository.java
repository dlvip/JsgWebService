package com.old.time.repository;

import com.old.time.domain.BookComEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookComRepository extends JpaRepository<BookComEntity, Integer> {

    /**
     * 获取图书评论列表
     *
     * @param bookId
     * @return
     */
    List<BookComEntity> findBookComEntitiesByBookId(Integer bookId);



}
