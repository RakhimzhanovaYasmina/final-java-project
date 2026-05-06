package kz.rakhimzhanova.finaljavaproject.service;

import kz.rakhimzhanova.finaljavaproject.entity.Order;
import kz.rakhimzhanova.finaljavaproject.exception.ResourceNotFoundException;
import kz.rakhimzhanova.finaljavaproject.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // GET ALL
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id " + id
                        )
                );
    }


    public Order updateOrder(Long id, Order updatedOrder) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id " + id
                        )
                );

        order.setCustomerName(updatedOrder.getCustomerName());
        order.setTotalPrice(updatedOrder.getTotalPrice());
        order.setStatus(updatedOrder.getStatus());

        return orderRepository.save(order);
    }


    public void deleteOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id " + id
                        )
                );

        orderRepository.delete(order);
    }
}