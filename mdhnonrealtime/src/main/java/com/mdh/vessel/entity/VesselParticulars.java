/**
 * 
 */
package com.mdh.vessel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Srini
 *
 */


public class VesselParticulars {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer vesselRecordId;
	
	
	private String vesselName; 
	
	/**
	 * @return the vesselRecordId
	 */
	
	

	public Integer getVesselRecordId() {
		return vesselRecordId;
	}

	/**
	 * @param vesselRecordId the vesselRecordId to set
	 */
	public void setVesselRecordId(Integer vesselRecordId) {
		vesselRecordId = this.vesselRecordId;
	}

	/**
	 * @return the vesselName
	 */
	public String getVesselName() {
		return vesselName;
	}

	/**
	 * @param vesselName the vesselName to set
	 */
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}



}
