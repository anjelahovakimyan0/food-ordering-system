package am.itspace.orderapplicationservice.ports.input.message.listener.payment;

import am.itspace.orderapplicationservice.dto.message.PaymentResponse;
import jakarta.validation.Valid;

public interface PaymentMethodMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);

}
