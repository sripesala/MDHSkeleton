/**
 * 
 */
package com.mdh.historicalvesseldata.service;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.HttpMethod;
import com.amazonaws.Protocol;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
/**
 * @author srini
 *
 */
@Service
public class AmazonClient {

    @Autowired String awss3filebucket;
    
    private AmazonS3 amazonS3;
 
    public AmazonClient(@Autowired Region awsRegion, @Autowired AWSCredentialsProvider awsCredentialsProvider) {
    	ClientConfiguration clientConfig = new ClientConfiguration();
    	clientConfig.setProtocol(Protocol.HTTPS);
    	clientConfig.setProxyHost("proxy.ncs.com.sg");
    	clientConfig.setProxyPort(8080);
    	clientConfig.setProxyUsername("p1319460");
    	clientConfig.setProxyPassword("Godhelp$12");
        this.amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName()).withClientConfiguration(clientConfig).build();
    }
 
    public void uploadFile(String destfilename, String filePath) {
        try {
        	System.out.println("uploadFile");
        	File file = new File(filePath);
            this.amazonS3.putObject(new PutObjectRequest(this.awss3filebucket, destfilename, file).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }
    
    public List<S3ObjectSummary> fetchList(){
    	ObjectListing objectListing = this.amazonS3.listObjects(this.awss3filebucket);
    	return  objectListing.getObjectSummaries();
    }
    
    public String generateDynamicUrl(String objectKey) {
    	URL url = null ; 	
	    try {	
	        // Set the presigned URL to expire after one hour.
	        java.util.Date expiration = new java.util.Date();
	        long expTimeMillis = expiration.getTime();
	        expTimeMillis += 1000 * 60 * 60;
	        expiration.setTime(expTimeMillis);
	
	        // Generate the presigned URL.
	        System.out.println("Generating pre-signed URL.");
	        GeneratePresignedUrlRequest generatePresignedUrlRequest = 
	                new GeneratePresignedUrlRequest(this.awss3filebucket, objectKey)
	                .withMethod(HttpMethod.GET)
	                .withExpiration(expiration);
	        url = this.amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
	
	        System.out.println("Pre-Signed URL: " + url.toString());
		    }
		    catch(AmazonServiceException e) {
		        // The call was transmitted successfully, but Amazon S3 couldn't process 
		        // it, so it returned an error response.
		        e.printStackTrace();
		    }
		    catch(SdkClientException e) {
		        // Amazon S3 couldn't be contacted for a response, or the client
		        // couldn't parse the response from Amazon S3.
		        e.printStackTrace();
		    }
	    return url==null?"":url.toString();
	    }
  
}
