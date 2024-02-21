package com.noarthedev.scaffolding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.scaffolding.entity.Secteur;

@Repository

public class SecteurRepository extends JpaRepository<Secteur,String> {

}