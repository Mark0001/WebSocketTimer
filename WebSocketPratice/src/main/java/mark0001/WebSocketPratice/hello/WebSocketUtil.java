package mark0001.WebSocketPratice.hello;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUtil {

    @Autowired
    private final SimpMessagingTemplate messageTemplate = null;

    @PostConstruct
    public void init() {
        final Timer timer = new Timer();
        timer.schedule(new DateTask(), 1000, 1000);
    }

    //    public void sendTime() {
    //        WebSocketUtil.this.messageTemplate.convertAndSend("/topic/time", new Date().toString());
    //    }

    class DateTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("任務時間：" + new Date());
            WebSocketUtil.this.messageTemplate.convertAndSend("/topic/time", new Date().toString());
        }
    }

}
