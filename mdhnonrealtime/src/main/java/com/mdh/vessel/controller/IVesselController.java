/**
 * 
 */
package com.mdh.vessel.controller;

import java.util.List;

import com.mdh.vessel.entity.VesselParticulars;

/**
 * @author Srini
 *
 */
public interface IVesselController {

	public List<VesselParticulars> getVesselParticulars( String vesselName,String callSign, String imoNumber);
}
