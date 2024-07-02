package am.itspace.orderapplicationservice.ports.output.repository;

import am.itspace.orderdomaincore.entity.Order;
import am.itspace.orderdomaincore.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);

}
