package am.itspace.orderapplicationservice.ports.output.message.publisher.payment;

import am.itspace.commondomain.entity.event.publisher.DomainEventPublisher;
import am.itspace.orderdomaincore.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
