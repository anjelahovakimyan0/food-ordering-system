package am.itspace.orderapplicationservice.ports.input.message.listener.payment;

import am.itspace.orderapplicationservice.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);

}
