package com.pieceofcake.auth_service.kafka.producer;

import com.pieceofcake.auth_service.kafka.producer.event.SignUpEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignUpKafkaProducer {
    private final KafkaTemplate<String, SignUpEvent> signUpKafkaTemplate;

    public void sendSignUpEvent(SignUpEvent signUpEvent) {
        CompletableFuture<SendResult<String, SignUpEvent>> future =
                signUpKafkaTemplate.send("member-signup-data", signUpEvent);
    }
}
