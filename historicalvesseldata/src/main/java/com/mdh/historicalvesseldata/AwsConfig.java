/**
 * 
 */
package com.mdh.historicalvesseldata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

/**
 * @author srini
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class AwsConfig {

    @Value("${aws.access.key.id}") String awsKeyId;
    @Value("${aws.access.key.secret}") String awsKeySecret;
    @Value("${aws.region}") String awsRegion;
    @Value("${aws.s3.file.bucket}") String awss3filebucket; 
 
    @Bean(name = "awsKeyId") 
    public String getAWSKeyId() {
        return awsKeyId;
    }
 
    @Bean(name = "awsKeySecret") 
    public String getAWSKeySecret() {
        return awsKeySecret;
    }
 
    @Bean(name = "awsRegion") 
    public Region getAWSPollyRegion() {
        return Region.getRegion(Regions.fromName(awsRegion));
    }
 
    @Bean(name = "awsCredentialsProvider") 
    public AWSCredentialsProvider getAWSCredentials() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.awsKeyId, this.awsKeySecret);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }
 
    @Bean(name = "awss3filebucket") 
    public String getAwss3filebucket() {
        return awss3filebucket;
    }
}