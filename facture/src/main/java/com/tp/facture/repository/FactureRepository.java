package com.tp.facture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tp.facture.entity.Facture;

@Repository

public interface FactureRepository extends JpaRepository<Facture,String> {

}