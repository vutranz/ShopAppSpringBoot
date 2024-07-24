package com.project.shopapp.Service;

import com.project.shopapp.DTO.OrderDTO;
import com.project.shopapp.DTO.OrderDetailDTO;
import com.project.shopapp.Models.OrderDetail;
import com.project.shopapp.exception.DataNotFoundException;

import java.util.List;

public interface OrderDetailService {

    public OrderDetail createOrderDetail(OrderDetailDTO orderDetail) throws DataNotFoundException;
    public OrderDetail getOrderDetail(Long id) throws DataNotFoundException;
    public OrderDetail updateOrderDetail(Long id,OrderDetailDTO orderDetailDTO) throws DataNotFoundException;
    public void deleteOrderDetail(Long id);
    public List<OrderDetail> findByOrderId(Long orderId);
}
