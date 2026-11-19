package ra.demo.service.impl;

import org.springframework.stereotype.Service;
import ra.demo.dto.response.Product;
import ra.demo.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProducts() {
        return List.of(
                new Product(1L, "Product 1", 10.0, "C01"),
                new Product(2L, "Product 2", 20.0, "C01"),
                new Product(3L, "Product 3", 126.0, "C02"),
                new Product(4L, "Product 4", 15.0, "C03")
        );
    }
}
