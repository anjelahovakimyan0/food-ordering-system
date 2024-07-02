package am.itspace.orderdomaincore.event;

import am.itspace.commondomain.entity.event.DomainEvent;
import am.itspace.orderdomaincore.entity.Order;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class OrderEvent implements DomainEvent<Order> {

    private final Order order;

    private final ZonedDateTime createdAt;

}
