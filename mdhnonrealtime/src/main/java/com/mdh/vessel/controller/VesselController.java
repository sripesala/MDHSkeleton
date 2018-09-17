package com.mdh.vessel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdh.vessel.dom.VesselReq;
import com.mdh.vessel.entity.VesselParticulars;
import com.mdh.vessel.service.IVesselParticularService;

@RestController
@RequestMapping("/vessel")
public class VesselController implements IVesselController{

	@Autowired
	IVesselParticularService VesselParticularService;
	
	@GetMapping("/particulars")
	public List<VesselParticulars> getVesselParticulars(@RequestParam("vesselName") String vesselName,
			@RequestParam("callSign") String callSign, @RequestParam("imoNumber") String imoNumber) {
		System.out.println("VesselPArticulars >"+vesselName);
		VesselReq vesselReq = new VesselReq();
		vesselReq.setCallSign(callSign);
		vesselReq.setImoNumber(Integer.parseInt(imoNumber));
		vesselReq.setVesselName(vesselName);
		return VesselParticularService.getVesselParticulars(vesselReq);
	}
}
