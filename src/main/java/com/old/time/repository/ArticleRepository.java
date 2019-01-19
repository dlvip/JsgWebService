package com.old.time.repository;

import com.old.time.domain.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    /**
     * 查询文章
     *
     * @param id
     * @return
     */
    ArticleEntity findArticleEntityById(Integer id);

}
