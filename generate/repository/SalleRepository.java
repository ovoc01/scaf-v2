package com.noarthedev.scaffolding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.scaffolding.entity.Salle;

@Repository

public class SalleRepository extends JpaRepository<Salle,String> {

}