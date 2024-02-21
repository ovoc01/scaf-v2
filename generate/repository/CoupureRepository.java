package com.noarthedev.scaffolding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.scaffolding.entity.Coupure;

@Repository

public class CoupureRepository extends JpaRepository<Coupure,String> {

}