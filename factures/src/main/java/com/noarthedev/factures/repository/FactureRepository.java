package com.noarthedev.factures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.factures.entity.Facture;

@Repository

public interface FactureRepository extends JpaRepository<Facture,String> {

}