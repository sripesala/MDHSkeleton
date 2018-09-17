/**
 * 
 */
package com.mdh.vessel.dom;

/**
 * @author Srini
 *
 */
public class VesselReq {
	private String vesselName; 
	private String callSign;
	private Integer imoNumber;
	
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
	/**
	 * @return the callSign
	 */
	public String getCallSign() {
		return callSign;
	}
	/**
	 * @param callSign the callSign to set
	 */
	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}
	/**
	 * @return the imoNumber
	 */
	public Integer getImoNumber() {
		return imoNumber;
	}
	/**
	 * @param imoNumber the imoNumber to set
	 */
	public void setImoNumber(Integer imoNumber) {
		this.imoNumber = imoNumber;
	}
}
