package com.ljy.dddstudy.presentation.order;

import com.ljy.dddstudy.presentation.order.request.place.PlaceOrderRequest;
import com.ljy.dddstudy.services.order.application.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> place(@RequestHeader("userId") String orderer,
                                      @Valid @RequestBody PlaceOrderRequest request) {
        Long orderId = orderService.place(request.toDto(), orderer);
        return ResponseEntity.created(URI.create("/api/v1/orders/" + orderId)).build();
    }
    
}
