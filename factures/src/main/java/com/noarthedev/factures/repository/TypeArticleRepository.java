package com.noarthedev.factures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.factures.entity.TypeArticle;

@Repository

public interface TypeArticleRepository extends JpaRepository<TypeArticle,String> {

}