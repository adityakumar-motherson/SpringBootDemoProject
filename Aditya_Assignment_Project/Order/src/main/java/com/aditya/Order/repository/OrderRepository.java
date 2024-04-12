package com.aditya.Order.repository;

import com.aditya.Order.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderProduct,Long> {
}
