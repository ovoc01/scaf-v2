package com.tp.facture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tp.facture.entity.DetailFacture;

@Repository

public interface DetailFactureRepository extends JpaRepository<DetailFacture,String> {

}