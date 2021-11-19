package com.nexio.api.ms.openfeign.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignClientConfig {
	 @Bean
	    public Logger.Level feignLoggerLevel() {
	        return Logger.Level.FULL;
	    }


	    @Bean
	    public ErrorDecoder errorDecoder() {
	        return new CustomErrorDecoder();
	    }

	    @Bean
	    public OkHttpClient client() {
	        return new OkHttpClient();
	    }

//	    @Bean
//	    public ErrorDecoder errorDecoder() {
//	        return new ErrorDecoder.Default();
//	    }
//	    	    
	    /**
	     * decomment for using this interceptor 
 		 * remove @Bean from the requestInterceptor()
	     * @return
	     */
	    // @Bean
	    public RequestInterceptor requestInterceptor() {
	        return requestTemplate -> {
//	            requestTemplate.header("user", "zeddini");
//	            requestTemplate.header("password", "zedini");
	            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
	        };
	    }

	    /**
	     * decomment for using this interceptor 
 		 * remove @Bean from the requestInterceptor()
	     * @return
	     */
	    // @Bean
	    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
	        return new BasicAuthRequestInterceptor("zeddini", "zeddini");
	    }
}
