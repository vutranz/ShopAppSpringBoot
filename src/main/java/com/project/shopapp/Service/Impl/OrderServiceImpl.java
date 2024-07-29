package com.project.shopapp.Service.Impl;

import com.project.shopapp.DTO.OrderDTO;
import com.project.shopapp.Models.Order;
import com.project.shopapp.Constants.OrderStatus;
import com.project.shopapp.Models.User;
import com.project.shopapp.Repository.OrderRepository;
import com.project.shopapp.Repository.UserRepository;
import com.project.shopapp.Service.OrderService;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private  final UserRepository userRepository;
    private  final OrderRepository orderRepository;
    private  final ModelMapper modelMapper;

    public OrderResponse createOrder(OrderDTO orderDTO) throws Exception {
        //kiểm tra id user đã tồn tại chưa;
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("can't find with id "+ orderDTO.getUserId()));
        //cach 1: convert orderDTO => order
        //cach 2: dùng thư viện mapper
        //tạo 1 luồng bảng ánh xạ riêng để kiểm xoát ánh xạ
        modelMapper.typeMap(OrderDTO.class, Order.class).addMappings(mapper -> mapper.skip(Order::setId));
        Order order = new Order();
        modelMapper.map(orderDTO,order);
        order.setUser(user);
//        order.setShippedMethod(orderDTO.getShippingMethod());
//        order.setShippedAddress(orderDTO.getShippingAddress());
//        order.setTrackingNumber(order.getTrackingNumber());
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        //kiểm tra shipping date > 1 ngày
        LocalDate shippingDate = orderDTO.getShippingData() == null ? LocalDate.now() : orderDTO.getShippingData();

        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Shipping date must be today or later!");
        }
        order.setShippedDate(shippingDate);
        order.setActive(true);
        orderRepository.save(order);
        return modelMapper.map(order,OrderResponse.class);
    }

    @Override
    public Order getOrder(Long id) throws Exception {
        return orderRepository.findById(id).orElse(null);

    }

    public Order updateOrder(Long id, OrderDTO orderDTO){
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new DateTimeException("can not order with id"+id));

        User existingUser = userRepository.findById(orderDTO.getUserId()).orElseThrow(() ->
                new DateTimeException("can not user with id"+id));

        modelMapper.typeMap(OrderDTO.class,Order.class).addMappings(mapper -> mapper.skip(Order::setId));

        modelMapper.map(orderDTO,order);
        order.setUser(existingUser);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order= orderRepository.findById(id).orElse(null);
        if(order!=null)
        {
            order.setActive(false);
            orderRepository.save(order);
        }
    }

    public List<Order> findByUserId(Long userId){
        return orderRepository.findByUserId(userId);
    }

}
