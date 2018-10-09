/**
 * 
 */
package com.mdh.historicalvesseldata.controller;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.mdh.historicalvesseldata.service.AmazonClient;

/**
 * @author srini
 *
 */

@RestController
@RequestMapping("/aws")
public class AwsBucketController {
	
	
	  private AmazonClient amazonClient;

	    @Autowired
	    AwsBucketController(AmazonClient amazonClient) {
	        this.amazonClient = amazonClient;
	    }

	    @GetMapping("/uploadFile/{destfilename}")
	    public void uploadFile(@PathVariable String destfilename) {
	         this.amazonClient.uploadFile(destfilename+".txt", "C:\\logs\\test.txt");
	    }
	    
	    @GetMapping("/fetchall")
	    public List<S3ObjectSummary> listFiles(){
	    	return this.amazonClient.fetchList();
	    }
	    
	    @GetMapping("/downloaduri/{keyname}")
	    public String generateDynamicUrl(@PathVariable String keyname) {
	    	return this.amazonClient.generateDynamicUrl(keyname);
	    }


}
