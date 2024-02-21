package com.noarthedev.scaffolding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noarthedev.scaffolding.entity.LuminositeV2;

@Repository

public class LuminositeV2Repository extends JpaRepository<LuminositeV2,String> {

}