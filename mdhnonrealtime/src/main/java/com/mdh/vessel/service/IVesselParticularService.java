/**
 * 
 */
package com.mdh.vessel.service;

import java.util.List;

import com.mdh.vessel.dom.VesselReq;
import com.mdh.vessel.entity.VesselParticulars;

/**
 * @author Srini
 *
 */
public interface IVesselParticularService {
	public List<VesselParticulars> getVesselParticulars(VesselReq vesselReq);
}
