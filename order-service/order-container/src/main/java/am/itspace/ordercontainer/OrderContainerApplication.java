package am.itspace.ordercontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "am.itspace.order.service.dataaccess")
@EntityScan(basePackages = "am.itspace.order.service.dataaccess")
@SpringBootApplication(scanBasePackages = "am.itspace")
public class OrderContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderContainerApplication.class, args);
    }

}
