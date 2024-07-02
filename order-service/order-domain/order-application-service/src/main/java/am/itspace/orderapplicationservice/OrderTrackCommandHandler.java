package am.itspace.orderapplicationservice;

import am.itspace.orderapplicationservice.dto.track.TrackOrderQuery;
import am.itspace.orderapplicationservice.dto.track.TrackOrderResponse;
import am.itspace.orderapplicationservice.mapper.OrderDataMapper;
import am.itspace.orderapplicationservice.ports.output.repository.OrderRepository;
import am.itspace.orderdomaincore.entity.Order;
import am.itspace.orderdomaincore.exception.OrderNotFoundException;
import am.itspace.orderdomaincore.valueobject.TrackingId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult
                = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));

        if (orderResult.isEmpty()) {
            log.warn("Could not find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Could not find order with tracking id: " + trackOrderQuery.getOrderTrackingId());
        }

        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
