package mark0001.WebSocketPratice.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JobComponent {
    private static Logger LOGGER = LoggerFactory.getLogger(JobComponent.class);

    private final Map<String, List<JobDetail>> jobsMap = new HashMap<>();

    private final String groupName = "eatGroup";

    private Scheduler scheduler;

    @PostConstruct
    private void init() {
        LOGGER.info("batch component initialized");
        try {
            this.scheduler = new StdSchedulerFactory().getScheduler();
        } catch (final SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
        startSchedule();

    }

    public void startSchedule() {
        LOGGER.info("ad batch startup");

        final List<JobDetail> jobDetails = new ArrayList<>();
        //        final Trigger trigger = TriggerBuilder.newTrigger().withIdentity("adTimeTirgger", groupName)
        //                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

        try {

            // 上架下架狀態通知

            final JobDetail job = JobBuilder.newJob(SomeJob.class).withIdentity("eatTheNotice", this.groupName).build();
            jobDetails.add(job);
            final Trigger trigger = TriggerBuilder.newTrigger().withIdentity("eatTheNoticeTirgger", this.groupName)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ? *")).build();
            this.scheduler.scheduleJob(job, trigger);
            this.jobsMap.put(this.groupName, jobDetails);
            this.scheduler.start();
        } catch (

        final SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void stopScheduler() {
        LOGGER.info("ad batch shutdown");
        try {
            if (!this.scheduler.isShutdown()) {
                if (this.jobsMap.get(this.groupName) != null) {
                    for (final JobDetail jobDetail : this.jobsMap.get(this.groupName)) {
                        this.scheduler.deleteJob(jobDetail.getKey());
                    }
                }
                this.scheduler.shutdown();
                this.scheduler = new StdSchedulerFactory().getScheduler();
            }
        } catch (final SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void setScheduler() {
        stopScheduler();
        startSchedule();
    }

    //    @SuppressWarnings("deprecation")
    //    private String dateToCronExpression(final Date date) {
    //        final StringBuffer cronExpression = new StringBuffer();
    //        final String year = String.valueOf(date.getYear() + 1900);
    //        final String month = String.valueOf(date.getMonth() + 1);
    //        final String day = String.valueOf(date.getDate());
    //        final String hour = String.valueOf(date.getHours());
    //        final String minute = String.valueOf(date.getMinutes());
    //        final String second = String.valueOf(date.getSeconds());
    //
    //        cronExpression.append(second);
    //        cronExpression.append(" ");
    //        cronExpression.append(minute);
    //        cronExpression.append(" ");
    //        cronExpression.append(hour);
    //        cronExpression.append(" ");
    //        cronExpression.append(day);
    //        cronExpression.append(" ");
    //        cronExpression.append(month);
    //        cronExpression.append(" ");
    //        cronExpression.append("?");
    //        cronExpression.append(" ");
    //        cronExpression.append(year);
    //        return cronExpression.toString();
    //    }

}
