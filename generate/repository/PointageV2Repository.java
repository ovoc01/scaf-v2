package com.noarthedev.scaffolding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.scaffolding.entity.PointageV2;

@Repository

public class PointageV2Repository extends JpaRepository<PointageV2,String> {

}