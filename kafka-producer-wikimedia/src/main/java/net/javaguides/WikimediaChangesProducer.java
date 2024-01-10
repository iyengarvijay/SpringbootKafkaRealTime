package net.javaguides;

import com.launchdarkly.eventsource.ConnectStrategy;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.StreamException;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import jdk.jfr.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service

public class WikimediaChangesProducer {
    private static final Logger Logger = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage() throws InterruptedException, StreamException {
        String topic = "wikimedia_recentchange";

        //to read real-time stream data from wikimedia, we use event source
        BackgroundEventHandler backgroundEventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder eventSourceBuilder = new EventSource.Builder(ConnectStrategy.http(URI.create(url)).connectTimeout(10, TimeUnit.SECONDS));
        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(backgroundEventHandler, eventSourceBuilder);
        BackgroundEventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }
}
