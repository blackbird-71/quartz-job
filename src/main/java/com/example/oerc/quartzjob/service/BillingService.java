package com.example.oerc.quartzjob.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.HttpHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BillingService {

    private Logger log = LoggerFactory.getLogger(getClass());

    public static final long EXECUTION_TIME = 5000L;

    private AtomicInteger count = new AtomicInteger();

    public void callBillingProcess() {

        log.info("The sample job has begun...");
        ejecutarJob();
        try {
            Thread.sleep(EXECUTION_TIME);
        } catch (InterruptedException e) {
            log.error("Error while executing sample job", e);
        } finally {
            count.incrementAndGet();
            log.info("Sample job has finished...");
        }
    }

    public int getNumberOfInvocations() {
        return count.get();
    }

    private void ejecutarJob() {
        log.info("Ejecuta el job");
        /*
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p1 = runtime.exec("cmd /c C:\\Proyectos\\Oscar\\prueba.bat");
            //Process p1 = runtime.exec("cmd /c start C:\\Proyectos\\Oscar\\prueba.bat");
            InputStream is = p1.getInputStream();
            int i = 0;
            StringBuffer sb = new StringBuffer();
            while( (i = is.read() ) != -1) {
                sb.append((char)i);
            }
            log.info(sb.toString());
        } catch(IOException ioException) {
            log.info(ioException.getMessage());
        }
        */
        /*
            echo "********************************************************************************"
            echo "*                             SE EJECUTO EL PROGRAMA                           *"
            echo "********************************************************************************"
         */
        try {
            String path = "/home/refactorizando/script.sh";
            String[] command = {"sh",path};
            Process process = Runtime.getRuntime().exec(command);

            String commandRead;

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((commandRead = stdInput.readLine()) != null) {
                log.info(commandRead);
            }
            stdInput.close();
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((commandRead = stdError.readLine()) != null) {
                log.info(commandRead);
            }
            stdInput.close();
        } catch(IOException ioException) {
            log.info(ioException.getMessage());
        }
    }

}
