package cl.gonmunoz.microservicesstatemachine.config;

import cl.gonmunoz.microservicesstatemachine.domain.PaymentEvent;
import cl.gonmunoz.microservicesstatemachine.domain.PaymentState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
@Slf4j
public class StateMachineConfig extends StateMachineConfigurerAdapter<PaymentState, PaymentEvent> {


    @Override
    public void configure(StateMachineStateConfigurer<PaymentState, PaymentEvent> states) throws Exception {
        // We set the state of our machine
        states.withStates()
                .initial(PaymentState.NEW)
                .states(EnumSet.allOf(PaymentState.class)) // We obtain all states
                .end(PaymentState.AUTH) //End state successful
                .end(PaymentState.PRE_AUTH_ERROR) //End state error
                .end(PaymentState.AUTH_ERROR); //End state error
    }
}
