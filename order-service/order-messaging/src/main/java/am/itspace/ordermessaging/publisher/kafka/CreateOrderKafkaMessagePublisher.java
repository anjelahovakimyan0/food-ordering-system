package am.itspace.ordermessaging.publisher.kafka;

import am.itspace.kafkamodel.kafka.order.avro.model.PaymentRequestAvroModel;
import am.itspace.kafkamodel.kafka.order.avro.model.PaymentResponseAvroModel;
import am.itspace.kafkaproducer.service.KafkaProducer;
import am.itspace.orderapplicationservice.config.OrderServiceConfigData;
import am.itspace.orderapplicationservice.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import am.itspace.orderdomaincore.event.OrderCreatedEvent;
import am.itspace.ordermessaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderKafkaMessagePublisher implements OrderCreatedPaymentRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;

    private final OrderServiceConfigData orderServiceConfigData;

    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;

    private final OrderKafkaMessageHelper orderKafkaMessageHelper;

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();
        log.info("Received OrderCreatedEvent for order id: {}", orderId);

        try {
            PaymentRequestAvroModel paymentRequestAvroModel
                    = orderMessagingDataMapper.orderCreatedEventToPaymentRequestAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
                    orderId, paymentRequestAvroModel,
                    orderKafkaMessageHelper.getKafkaCallback(
                            orderServiceConfigData.getPaymentResponseTopicName(), paymentRequestAvroModel,
                            orderId, "PaymentRequestAvroModel"));

            log.info("PaymentRequestAvroModel sent to Kafka for order id: {}", paymentRequestAvroModel.getOrderId());
        } catch (Exception e) {
            log.error("Error while sending PaymentRequestAvroModel messasge" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
