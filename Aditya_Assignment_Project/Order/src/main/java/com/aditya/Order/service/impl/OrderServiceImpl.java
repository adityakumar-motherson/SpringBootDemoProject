package com.aditya.Order.service.impl;

import com.aditya.Order.entity.OrderProduct;
import com.aditya.Order.payload.Inventory;
import com.aditya.Order.repository.OrderRepository;
import com.aditya.Order.service.OrderService;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderProduct> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderProduct getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    @Transactional
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    public OrderProduct createOrder(OrderProduct order) throws URISyntaxException {
          //Get product details based on product id
            Inventory inventory = restTemplate.getForObject("http://localhost:9001/api/productinventory/" + order.getProductId(), Inventory.class);

            //Validate Product Quantity with ordered Quantity
            var availableQty = inventory != null ? inventory.getQty() : null;
            if (availableQty != null) {
                var RemainingQty = availableQty - order.getOrderQty();
                //Update the product quantity in database if ordred quantiy is available
                if (RemainingQty >= 0) {
                    inventory.setQty(RemainingQty);
                    //set the uri with json formate
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<Inventory> httpEntity = new HttpEntity<>(inventory, headers);
                    URI uri = new URI("http://localhost:9001/api/productinventory");
                    //Save the inventory with updated qty
                    Inventory inventory1 = restTemplate.postForObject(uri, httpEntity, inventory.getClass());
                    //update  the order status
                    order.setOrderStatus("Created");
                } else {
                    //update  the order status in case product quantity less than order qty
                    order.setOrderStatus("Failed");
                }
            } else {
                //update  the order status in case product quantity not found
                order.setOrderStatus("Failed");
            }
            //save the order
            return orderRepository.save(order);
        }
    }


