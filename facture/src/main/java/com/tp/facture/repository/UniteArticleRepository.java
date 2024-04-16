package com.tp.facture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tp.facture.entity.UniteArticle;

@Repository

public interface UniteArticleRepository extends JpaRepository<UniteArticle,String> {

}