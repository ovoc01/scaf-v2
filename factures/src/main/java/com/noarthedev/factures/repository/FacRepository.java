package com.noarthedev.factures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.factures.entity.Fac;

@Repository

public interface FacRepository extends JpaRepository<Fac,Long> {

}