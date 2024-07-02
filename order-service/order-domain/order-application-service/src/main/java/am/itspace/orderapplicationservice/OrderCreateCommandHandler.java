package am.itspace.orderapplicationservice;

import am.itspace.orderapplicationservice.dto.create.CreateOrderCommand;
import am.itspace.orderapplicationservice.dto.create.CreateOrderResponse;
import am.itspace.orderapplicationservice.mapper.OrderDataMapper;
import am.itspace.orderapplicationservice.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import am.itspace.orderdomaincore.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreateCommandHandler {

    private final OrderCreateHelper orderCreateHelper;

    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;//TODO publish

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
        log.info("Order is created with id {}", orderCreatedEvent.getOrder().getId().getValue());
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        return orderDataMapper.orderToCreateOrderResponse(
                orderCreatedEvent.getOrder(), "Order Created Successfully");
    }
}
