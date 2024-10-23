package com.playground.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.RecordDeserializationException;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaContainerErrorHandler implements CommonErrorHandler {
    @Override
    public boolean handleOne(Exception e, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        log.error("Failed to process record {}", record.value(), e);
        return true;
    }

    @Override
    public void handleOtherException(Exception e, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        if (e instanceof RecordDeserializationException rex) {
            consumer.seek(rex.topicPartition(), rex.offset() + 1L);
            consumer.commitSync();
        }
        log.error("General failure in kafka listener container", e);
    }
}
