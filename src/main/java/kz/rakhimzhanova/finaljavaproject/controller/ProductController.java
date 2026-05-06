package kz.rakhimzhanova.finaljavaproject.controller;

import jakarta.validation.Valid;
import kz.rakhimzhanova.finaljavaproject.entity.Product;
import kz.rakhimzhanova.finaljavaproject.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product product
    ) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/paged")
    public Page<Product> getProductsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {

        return productService.getProductsWithPagination(
                page,
                size,
                sortBy
        );
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam String title
    ) {

        return productService.searchProductsByTitle(title);
    }
}