package am.itspace.orderapplicationservice;

import am.itspace.orderapplicationservice.dto.message.PaymentResponse;
import am.itspace.orderapplicationservice.ports.input.message.listener.payment.PaymentMethodMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Slf4j
public class PaymentMethodMessageListenerImpl implements PaymentMethodMessageListener {

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {

    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {

    }
}
