/**
 * 
 */
package com.mdh.vessel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdh.vessel.dom.VesselReq;
import com.mdh.vessel.entity.VesselParticulars;
import com.mdh.vessel.repository.VesselParticularsRepository;

/**
 * @author Srini
 *
 */

@Service
public class VesselParticularService implements IVesselParticularService{

	@Autowired
	VesselParticularsRepository vesselParticularsRep;
	
	@Override
	public List<VesselParticulars> getVesselParticulars(VesselReq vesselReq){
		return vesselParticularsRep.findAll();
	}

}
