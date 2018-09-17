/**
 * 
 */
package com.mdh.vessel;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mdh.vessel.controller.VesselController;
import com.mdh.vessel.dom.VesselReq;
import com.mdh.vessel.entity.VesselParticulars;
import com.mdh.vessel.service.IVesselParticularService;

/**
 * @author Srini
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VesselControllerTest {

	@Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    
    @MockBean
 	private IVesselParticularService VesselParticularService;
 	
 	
 	@InjectMocks
 	VesselController vesselcontroler;
 	
 	 @Rule
     public JUnitRestDocumentation jUnitRestDocumentation = new JUnitRestDocumentation();


     @Before
     public void setup() {
    	 mockMvc = MockMvcBuilders
                 .webAppContextSetup(context)
                 .apply(documentationConfiguration(this.jUnitRestDocumentation).operationPreprocessors()
                	.withRequestDefaults(Preprocessors.prettyPrint()) 
                 	.withResponseDefaults(Preprocessors.prettyPrint())) 
                 .build();
     }
     
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
 		
 		RequestBuilder requestBuilder = RestDocumentationRequestBuilders.get(
 				"/vessel/particulars") .param("vesselName", vesselName)
 	            .param("callSign", callSign).param("imoNumber", imoNumber); 
 		try {
 			MvcResult result = mockMvc.perform(requestBuilder).andDo(document("index")).andReturn();
 			MockHttpServletResponse response = result.getResponse();
 			String expected = "[{\"vesselRecordId\":null,\"vesselName\":\"v1\"}]";
 			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}	}
}
