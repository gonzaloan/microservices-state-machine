package cl.gonmunoz.microservicesstatemachine.config;

import cl.gonmunoz.microservicesstatemachine.domain.PaymentEvent;
import cl.gonmunoz.microservicesstatemachine.domain.PaymentState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

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

    @Override
    public void configure(StateMachineTransitionConfigurer<PaymentState, PaymentEvent> transitions) throws Exception {
       transitions.withExternal().source(PaymentState.NEW)
               .target(PaymentState.NEW) // We set events related with the states. So, new state, the event is to pre authorize
               .event(PaymentEvent.PRE_AUTHORIZE)
               .and()
               // I go from New state to pre_auth when i get PRE_AUTH_APPROVED
               .withExternal().source(PaymentState.NEW).target(PaymentState.PRE_AUTH).event(PaymentEvent.PRE_AUTH_APPROVED)
               .and()
               // I go from New state to pre_auth_ERROR when i get PRE_AUTH_DECLINED
               .withExternal().source(PaymentState.NEW).target(PaymentState.PRE_AUTH_ERROR).event(PaymentEvent.PRE_AUTH_DECLINED);

    }
}
