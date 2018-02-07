package mark0001.WebSocketPratice.hello;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class SomeJob implements Job {
    private static Logger LOGGER = LoggerFactory.getLogger(SomeJob.class);

    
    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("do some in this job");
        ApplicationContext context1 = SpringContext.getAppContext();
        WebSocketUtil  webSocketUtil = (WebSocketUtil)context1.getBean("webSocketUtil");   
        webSocketUtil.callEatTheNotice("吃通知囉");
    }
}
