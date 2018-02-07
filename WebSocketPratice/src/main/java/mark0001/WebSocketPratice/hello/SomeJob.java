package mark0001.WebSocketPratice.hello;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class SomeJob implements Job {
    private static Logger LOGGER = LoggerFactory.getLogger(SomeJob.class);

    @Autowired
    WebSocketUtil webSocketUtil;

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        LOGGER.info("do some in this job");
        this.webSocketUtil.callEatTheNotice("吃通知囉");
    }
}
