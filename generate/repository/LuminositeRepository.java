package com.noarthedev.scaffolding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.scaffolding.entity.Luminosite;

@Repository

public class LuminositeRepository extends JpaRepository<Luminosite,String> {

}