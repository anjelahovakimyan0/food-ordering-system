package am.itspace.ordermessaging.listener.kafka;

import am.itspace.kafkaconsumer.KafkaConsumer;
import am.itspace.kafkamodel.kafka.order.avro.model.OrderApprovalStatus;
import am.itspace.kafkamodel.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import am.itspace.orderapplicationservice.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;
import am.itspace.ordermessaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static am.itspace.orderdomaincore.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Component
@Slf4j
@RequiredArgsConstructor
public class RestaurantApprovalResponseKafkaListener implements KafkaConsumer<RestaurantApprovalResponseAvroModel> {

    private final RestaurantApprovalResponseMessageListener restaurantApprovalResponseMessageListener;

    private final OrderMessagingDataMapper orderMessagingDataMapper;

    @Override
    @KafkaListener(id = "${kafka-consumer-confid.restaurant-approval-consumer-group-id}",
            topics = "${order-service.restaurant-approval-response-topic-name}")
    public void receive(@Payload List<RestaurantApprovalResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.REPLY_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of retaurant approval responses received with key: {}, partitions: {} and offsets: {}",
                messages.size(), keys.toString(), partitions.toString(), offsets.toString());

        messages.forEach(restaurantApprovalResponseAvroModel -> {
            if (OrderApprovalStatus.APPROVED == restaurantApprovalResponseAvroModel.getOrderApprovalStatus()) {
                log.info("Processing approved order for order id: {}",
                        restaurantApprovalResponseAvroModel.getOrderId());

                restaurantApprovalResponseMessageListener.orderApproved(orderMessagingDataMapper
                        .approvalResponseAvroModelToApprovalResponse(restaurantApprovalResponseAvroModel));
            } else if (OrderApprovalStatus.REJECTED == restaurantApprovalResponseAvroModel.getOrderApprovalStatus()) {
                log.info("Processing rejected order for order id: {}, with failure messagges: {}",
                        restaurantApprovalResponseAvroModel.getOrderId(),
                        String.join(FAILURE_MESSAGE_DELIMITER, restaurantApprovalResponseAvroModel.getFailureMessages()));

                restaurantApprovalResponseMessageListener.orderRejected(orderMessagingDataMapper
                        .approvalResponseAvroModelToApprovalResponse(restaurantApprovalResponseAvroModel));
            }
        });
    }
}
