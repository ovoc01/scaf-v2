package com.noarthedev.factures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.factures.entity.UniteArticle;

@Repository

public interface UniteArticleRepository extends JpaRepository<UniteArticle,String> {

}