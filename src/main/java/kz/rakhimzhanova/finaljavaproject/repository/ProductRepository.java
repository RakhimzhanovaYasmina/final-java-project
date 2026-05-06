package kz.rakhimzhanova.finaljavaproject.repository;

import kz.rakhimzhanova.finaljavaproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}