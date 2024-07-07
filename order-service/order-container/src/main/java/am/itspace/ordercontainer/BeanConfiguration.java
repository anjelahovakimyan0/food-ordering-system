package am.itspace.ordercontainer;

import am.itspace.orderdomaincore.OrderDomainService;
import am.itspace.orderdomaincore.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
