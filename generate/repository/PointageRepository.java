package com.noarthedev.scaffolding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.scaffolding.entity.Pointage;

@Repository

public class PointageRepository extends JpaRepository<Pointage,String> {

}