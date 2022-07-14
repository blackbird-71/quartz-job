package com.example.oerc.quartzjob.schedule;

import com.example.oerc.quartzjob.service.BillingService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class BillingJob implements Job {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private BillingService bs;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

        bs.callBillingProcess();

        log.info("Next job scheduled @ {}", context.getNextFireTime());
    }

}
