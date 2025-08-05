package com.sanchi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sanchi.model.Order;
import java.util.List;




public interface OrderRepository extends JpaRepository<Order,Long> {


    List<Order> findByUserId(Long userId);
}
