/**
 * 
 */
package com.mdh.vessel;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mdh.vessel.controller.IVesselController;
import com.mdh.vessel.controller.VesselController;
import com.mdh.vessel.dom.VesselReq;
import com.mdh.vessel.entity.VesselParticulars;
import com.mdh.vessel.service.IVesselParticularService;

/**
 * @author Srini
 *
 */

//@RunWith(SpringRunner.class)
//@WebMvcTest(value = VesselController.class,secure=false)
public class VesselControllerTest_nodoc {


   @Autowired
   private MockMvc mvc;
   
   @MockBean
	private IVesselParticularService VesselParticularService;
	
	
	@InjectMocks
	VesselController vesselcontroler;
	
	
   
	@Test
	public void testGetVesselParticulars() {
		String imoNumber = "1212";
		String vesselName = "Window";
		String callSign = "callSign";

		List<VesselParticulars> list = new ArrayList<>();
		VesselParticulars v = new VesselParticulars();
		v.setVesselName("v1");
		v.setVesselRecordId(9299);
		list.add(v);
		
		when(VesselParticularService.getVesselParticulars(Mockito.any(VesselReq.class))).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/vessel/particulars") .param("vesselName", vesselName)
	            .param("callSign", callSign).param("imoNumber", imoNumber); 
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			String expected = "[{\"vesselRecordId\":null,\"vesselName\":\"v1\"}]";
			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	

}
