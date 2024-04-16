package com.tp.facture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tp.facture.entity.Fac;

@Repository

public interface FacRepository extends JpaRepository<Fac,Long> {

}