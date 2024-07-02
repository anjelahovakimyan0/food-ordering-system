package am.itspace.orderapplicationservice.ports.input.service;

import am.itspace.orderapplicationservice.dto.create.CreateOrderCommand;
import am.itspace.orderapplicationservice.dto.create.CreateOrderResponse;
import am.itspace.orderapplicationservice.dto.track.TrackOrderQuery;
import am.itspace.orderapplicationservice.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);

}
