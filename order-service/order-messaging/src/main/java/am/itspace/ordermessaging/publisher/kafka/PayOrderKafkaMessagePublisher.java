package am.itspace.ordermessaging.publisher.kafka;

import am.itspace.kafkamodel.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import am.itspace.kafkaproducer.service.KafkaProducer;
import am.itspace.orderapplicationservice.config.OrderServiceConfigData;
import am.itspace.orderapplicationservice.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import am.itspace.orderdomaincore.event.OrderPaidEvent;
import am.itspace.ordermessaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PayOrderKafkaMessagePublisher implements OrderPaidRestaurantRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;

    private final OrderServiceConfigData orderServiceConfigData;

    private final KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer; //cause I wil send data to RestaurantApprovalRequest topic, which type is RestaurantApprovalRequestAvroModel

    private final OrderKafkaMessageHelper orderKafkaMessageHelper;

    @Override
    public void publish(OrderPaidEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();

        try {
            RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel =
                    orderMessagingDataMapper.orderPaidEventToRestaurantApprovalRequestAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getRestaurantApprovalRequestTopicName(),
                    orderId, restaurantApprovalRequestAvroModel,
                    orderKafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getRestaurantApprovalRequestTopicName(),
                            restaurantApprovalRequestAvroModel, orderId, "RestaurantApprovalRequestAvroModel"));

            log.info("RestaurantApprovalRequestAvroModel sent to kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error("Error while sending RestaurantApprovalRequestAvroModel message " +
                    "to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
