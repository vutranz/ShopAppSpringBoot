package com.project.shopapp.Service.Impl;

import com.project.shopapp.DTO.OrderDetailDTO;
import com.project.shopapp.Models.Order;
import com.project.shopapp.Models.OrderDetail;
import com.project.shopapp.Models.Product;
import com.project.shopapp.Repository.OrderDetailRepository;
import com.project.shopapp.Repository.OrderRepository;
import com.project.shopapp.Repository.ProductRepository;
import com.project.shopapp.Service.OrderDetailService;
import com.project.shopapp.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImp implements OrderDetailService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("can not id "+ orderDetailDTO.getOrderId()));
        Product product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException("can not id "+ orderDetailDTO.getProductId()));

        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .price(orderDetailDTO.getPrice())
                .numberOfProduct(orderDetailDTO.getNumberOfProduct())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .color(orderDetailDTO.getColor())
                .build();
        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetail getOrderDetail(Long id) throws DataNotFoundException {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("not found id OrderDetail "+ id));
    }

    public OrderDetail updateOrderDetail(Long id,OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
        OrderDetail orderDetail = orderDetailRepository.findById(id).
                orElseThrow(() -> new DataNotFoundException("can not find oderdetail id"+id));

        Order order = orderRepository.findById(orderDetailDTO.getOrderId()).
                orElseThrow(() -> new DataNotFoundException("can not find order with  id"+orderDetailDTO.getOrderId()));

        Product product = productRepository.findById(orderDetailDTO.getProductId()).
                orElseThrow(() -> new DataNotFoundException("can not find order with  id"+orderDetailDTO.getProductId()));

        orderDetail.setPrice(orderDetailDTO.getPrice());
        orderDetail.setNumberOfProduct(orderDetailDTO.getNumberOfProduct());
        orderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
        orderDetail.setColor(orderDetailDTO.getColor());
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);

        return orderDetailRepository.save(orderDetail);
    }
    public void deleteOrderDetail(Long id){
        orderDetailRepository.deleteById(id);
    }
    public List<OrderDetail> findByOrderId(Long orderId){
        return orderDetailRepository.findByOrderId(orderId);
    }
}
