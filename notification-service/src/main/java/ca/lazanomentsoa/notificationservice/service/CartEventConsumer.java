package ca.lazanomentsoa.notificationservice.service;

import ca.lazanomentsoa.notificationservice.dto.CartCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Consumer;

@Service
public class CartEventConsumer {

//    @RabbitListener(queues = "${rabbitmq.queue.name}")
//    //parameter the type of message here CartCreatedEvent (object), but can be Map.of, String
//    public void handleCartEvent(CartCreatedEvent cartEvent){
//        System.out.println("Cart event received: " + cartEvent.toString());
//
////        long cartId = Long.parseLong(cartEvent.get("cartId").toString());
////        String status = cartEvent.get("status").toString();
//
//        System.out.println("Cart ID: " + cartEvent);
//        System.out.println("Status: " + cartEvent.getStatus());
//
//        //can update database
//        //send Notification
//        //send emails
//        //generate invoice
//    }


    @Bean
    public Consumer<CartCreatedEvent> cartCreated() {
        return event -> {

//        long cartId = Long.parseLong(cartEvent.get("cartId").toString());
//        String status = cartEvent.get("status").toString();

        System.out.println("Cart ID cloud stream: " + event.getCartId());
        System.out.println("Status cloud stream: " + event.getStatus());
        };
    }

}
