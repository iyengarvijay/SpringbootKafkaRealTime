//package net.javaguides;
//import com.launchdarkly.eventsource.MessageEvent;
//import com.launchdarkly.eventsource.background.BackgroundEventHandler;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import java.util.logging.Logger;
//
//public class WikimediaChangesHandler implements BackgroundEventHandler {
//
//    private KafkaTemplate<String, String> kafkaTemplate;
//    private String topic;
//    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(WikimediaChangesHandler.class);
//
//    @Override
//    public void onOpen() throws Exception {
//
//    }
//
//
//    public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
//        this.kafkaTemplate = kafkaTemplate;
//        this.topic = topic;
//    }
//
//    @Override
//    public void onClosed() throws Exception {
//
//    }
//
//    @Override
//    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
//        LOGGER.info(String.format("event data -> %s", messageEvent.getData()));
//
//        kafkaTemplate.send(topic, messageEvent.getData());
//    }
//
//    @Override
//    public void onComment(String s) throws Exception {
//
//    }
//
//    @Override
//    public void onError(Throwable throwable) {
//
//    }
//}
package net.javaguides;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikimediaChangesHandler implements BackgroundEventHandler {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);

    @Override
    public void onOpen() throws Exception {

    }

    public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("event data -> %s", messageEvent.getData()));
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
