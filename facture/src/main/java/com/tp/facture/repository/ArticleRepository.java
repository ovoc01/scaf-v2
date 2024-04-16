package com.tp.facture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tp.facture.entity.Article;

@Repository

public interface ArticleRepository extends JpaRepository<Article,String> {

}