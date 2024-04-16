package com.tp.facture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tp.facture.entity.TypeArticle;

@Repository

public interface TypeArticleRepository extends JpaRepository<TypeArticle,String> {

}