package com.noarthedev.scaffolding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.scaffolding.entity.SourceEnergie;

@Repository

public class SourceEnergieRepository extends JpaRepository<SourceEnergie,String> {

}