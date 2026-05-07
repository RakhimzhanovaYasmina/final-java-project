package kz.rakhimzhanova.finaljavaproject.service;

import kz.rakhimzhanova.finaljavaproject.entity.Product;
import kz.rakhimzhanova.finaljavaproject.exception.ResourceNotFoundException;
import kz.rakhimzhanova.finaljavaproject.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id)

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "Product not found with id " + id
                        )
                );
    }

    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {

        Product product =
                productRepository.findById(id)

                        .orElseThrow(() ->

                                new ResourceNotFoundException(
                                        "Product not found"
                                )
                        );

        product.setTitle(updatedProduct.getTitle());

        product.setDescription(updatedProduct.getDescription());

        product.setPrice(updatedProduct.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {

        Product product =
                productRepository.findById(id)

                        .orElseThrow(() ->

                                new ResourceNotFoundException(
                                        "Product not found"
                                )
                        );

        productRepository.delete(product);
    }

    public Page<Product> getProductsWithPagination(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable = PageRequest.of(

                page,

                size,

                Sort.by(sortBy)
        );

        return productRepository.findAll(pageable);
    }

    public List<Product> searchProductsByTitle(String title) {

        return productRepository
                .findByTitleContainingIgnoreCase(title);
    }
}