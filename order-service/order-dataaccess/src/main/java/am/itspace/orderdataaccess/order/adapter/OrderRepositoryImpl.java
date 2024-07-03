package am.itspace.orderdataaccess.order.adapter;

import am.itspace.orderapplicationservice.ports.output.repository.OrderRepository;
import am.itspace.orderdataaccess.order.mapper.OrderDataAccessMapper;
import am.itspace.orderdataaccess.order.repository.OrderJpaRepository;
import am.itspace.orderdomaincore.entity.Order;
import am.itspace.orderdomaincore.valueobject.TrackingId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component //spring managed bean
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    private final OrderDataAccessMapper orderDataAccessMapper;

    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(
                orderJpaRepository.save(orderDataAccessMapper.orderToOrderEntity(order)));
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJpaRepository.findByTrackingId(trackingId.getValue())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
