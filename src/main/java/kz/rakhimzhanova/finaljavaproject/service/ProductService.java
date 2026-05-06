package kz.rakhimzhanova.finaljavaproject.service;

import kz.rakhimzhanova.finaljavaproject.entity.Product;
import kz.rakhimzhanova.finaljavaproject.repository.ProductRepository;
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

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {

        Product product = productRepository.findById(id)
                .orElseThrow();

        product.setTitle(updatedProduct.getTitle());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow();
    }
}