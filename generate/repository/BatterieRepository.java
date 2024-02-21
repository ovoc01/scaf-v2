package com.noarthedev.scaffolding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.scaffolding.entity.Batterie;

@Repository

public class BatterieRepository extends JpaRepository<Batterie,String> {

}