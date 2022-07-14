package com.example.oerc.quartzjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzJobApplication {

	//WORKAROUND. TO BE REMOVED. Avoid problems with wrong CN of  self signed certificate
	static {
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
				new javax.net.ssl.HostnameVerifier(){

					public boolean verify(String hostname,
										  javax.net.ssl.SSLSession sslSession) {
						if (hostname.equals("localhost")) {
							return true;
						}
						return false;
					}
				});
	}

	public static void main(String[] args) {
		SpringApplication.run(QuartzJobApplication.class, args);
	}

}
