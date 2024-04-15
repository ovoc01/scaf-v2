package com.noarthedev.factures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.factures.entity.DetailFacture;

@Repository

public interface DetailFactureRepository extends JpaRepository<DetailFacture,String> {

}