package com.mdh.vessel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mdh.vessel.entity.VesselParticulars;

@Repository
@Transactional
public interface VesselParticularsRepository extends JpaRepository<VesselParticulars, Integer>{

}
