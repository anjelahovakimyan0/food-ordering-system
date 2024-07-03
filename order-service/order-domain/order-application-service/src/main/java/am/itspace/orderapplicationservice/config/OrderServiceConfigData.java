package am.itspace.orderapplicationservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "order-service") //this class will load the configuration data from the order-service prefix from the configuration file(application.yml)
public class OrderServiceConfigData {

    private String paymentRequestTopicName;

    private String paymentResponseTopicName;

    private String restaurantApprovalRequestTopicName;

    private String restaurantApprovalResponseTopicName;

}
