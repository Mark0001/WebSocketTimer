package mark0001.WebSocketPratice.hello;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketUtil.class);

    @Autowired
    private final SimpMessagingTemplate messageTemplate = null;

    @PostConstruct
    public void init() {
        logger.info("WebSocketUtil Initialized");
        final Timer timer = new Timer();
        timer.schedule(new DateTask(), 1000, 1000);
    }

    //    public void sendTime() {
    //        WebSocketUtil.this.messageTemplate.convertAndSend("/topic/time", new Date().toString());
    //    }

    class DateTask extends TimerTask {
        @Override
        public void run() {
            WebSocketUtil.this.messageTemplate.convertAndSend("/topic/time", new Date().toString());
        }
    }

}
