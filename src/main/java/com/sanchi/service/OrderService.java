package com.sanchi.service;

import com.sanchi.domain.OrderType;
import com.sanchi.model.Coin;
import com.sanchi.model.Order;
import com.sanchi.model.OrderItem;
import com.sanchi.model.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, OrderItem orderItem, OrderType orderType);

    Order getOrderById(Long orderId) throws Exception;

    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String assetSymbol);

    Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;

}
