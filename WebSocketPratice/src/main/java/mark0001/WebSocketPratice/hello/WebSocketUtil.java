package mark0001.WebSocketPratice.hello;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketUtil.class);

    @Autowired
    private final SimpMessagingTemplate messageTemplate = null;

    @Scheduled(fixedRate = 1000)
    private void callWebSocket() {
        logger.debug("send to page");
        WebSocketUtil.this.messageTemplate.convertAndSend("/topic/time", new Date().toString());
    }

    public void callEatTheNotice(String notice) {
        WebSocketUtil.this.messageTemplate.convertAndSend("/topic/eatTheNotice", notice);
    }

}
