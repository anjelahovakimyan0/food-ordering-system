package am.itspace.ordermessaging.listener.kafka;

import am.itspace.kafkaconsumer.KafkaConsumer;
import am.itspace.kafkamodel.kafka.order.avro.model.PaymentResponseAvroModel;
import am.itspace.kafkamodel.kafka.order.avro.model.PaymentStatus;
import am.itspace.orderapplicationservice.ports.input.message.listener.payment.PaymentResponseMessageListener;
import am.itspace.ordermessaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentResponseKafkaListener implements KafkaConsumer<PaymentResponseAvroModel> {

    private final PaymentResponseMessageListener paymentResponseMessageListener;

    private final OrderMessagingDataMapper orderMessagingDataMapper;

    @Override
    //KafkaListener annotation is a spring boot annotation to create kafka consumer from a simple method
    @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}",
            topics = "${order-service.payment-response-topic-name}")
    // id and topic will come from application configuration
    public void receive(@Payload List<PaymentResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
//                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Long> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
//                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID)List<Integer> partitions,//TODO
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number  of payment responses received with key: {}, partitions: {} and offsets: {}",
                messages.size(), keys.toString(), partitions.toString(), offsets.toString());

        messages.forEach(paymentResponseAvroModel -> {
            if (PaymentStatus.COMPLETED == paymentResponseAvroModel.getPaymentStatus()) {
                log.info("Processing successful payment for order id: {}", paymentResponseAvroModel.getOrderId());
                paymentResponseMessageListener.paymentCompleted(orderMessagingDataMapper
                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
            } else if (PaymentStatus.CANCELLED == paymentResponseAvroModel.getPaymentStatus()
                    || PaymentStatus.FAILED == paymentResponseAvroModel.getPaymentStatus()) {
                log.info("Processing unsuccessful payment for order id: {}", paymentResponseAvroModel.getOrderId());
                paymentResponseMessageListener.paymentCancelled(orderMessagingDataMapper
                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
            }
        });
    }
}
