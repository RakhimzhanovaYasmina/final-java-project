package kz.rakhimzhanova.finaljavaproject.repository;

import kz.rakhimzhanova.finaljavaproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleContainingIgnoreCase(String title);
}