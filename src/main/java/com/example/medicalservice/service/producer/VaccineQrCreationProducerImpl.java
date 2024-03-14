package com.example.medicalservice.service.producer;

import com.example.medicalservice.model.dto.VaccineQrMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaccineQrCreationProducerImpl implements VaccineQrCreationProducer{

    @Value("${kafka.topic.vaccineQrTopicName}")
    private String createQrTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendCreateQr(VaccineQrMessageDto message) {
        log.info("Sending message {} to topic {}", message, createQrTopic);
        kafkaTemplate.send(createQrTopic, message.getCitizenId().toString(), message);
        log.info("Message {} sent", message);
    }
}
