package kz.rakhimzhanova.finaljavaproject.repository;

import kz.rakhimzhanova.finaljavaproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}