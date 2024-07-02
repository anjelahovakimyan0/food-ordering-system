package am.itspace.orderapplicationservice.dto.message;

import am.itspace.commondomain.entity.valueObject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {

    private String id;

    private String sagaId;

    private String orderId;

    private String paymentId;

    private String customerId;

    private BigDecimal price;

    private Instant createdAt;

    private PaymentStatus paymentStatus;

    private List<String> failureMessages;

}
