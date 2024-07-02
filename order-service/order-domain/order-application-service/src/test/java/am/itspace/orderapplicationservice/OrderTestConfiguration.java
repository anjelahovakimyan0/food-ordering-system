package am.itspace.orderapplicationservice;

import am.itspace.orderapplicationservice.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagedPublisher;
import am.itspace.orderapplicationservice.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import am.itspace.orderapplicationservice.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import am.itspace.orderapplicationservice.ports.output.repository.CustomerRepository;
import am.itspace.orderapplicationservice.ports.output.repository.OrderRepository;
import am.itspace.orderapplicationservice.ports.output.repository.RestaurantRepository;
import am.itspace.orderdomaincore.OrderDomainService;
import am.itspace.orderdomaincore.OrderDomainServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "am.itspace.orderapplicationservice")
public class OrderTestConfiguration {

    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMessagedPublisher orderCancelledPaymentRequestMessagedPublisher() {
        return Mockito.mock(OrderCancelledPaymentRequestMessagedPublisher.class);
    }

    @Bean
    public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher() {
        return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public RestaurantRepository restaurantRepository() {
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
