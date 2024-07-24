package com.project.shopapp.Service;

import com.project.shopapp.DTO.OrderDTO;
import com.project.shopapp.Models.Order;
import com.project.shopapp.responses.OrderResponse;

import java.util.List;

public interface OrderService {
    public OrderResponse createOrder(OrderDTO orderDTO) throws Exception;
    public Order getOrder(Long id) throws Exception;;
    public Order updateOrder(Long id, OrderDTO orderDTO);
    public void deleteOrder(Long id);
    public List<Order> findByUserId(Long userId);
}
