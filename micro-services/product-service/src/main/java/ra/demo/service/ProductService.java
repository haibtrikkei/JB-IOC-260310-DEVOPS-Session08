package ra.demo.service;

import org.springframework.stereotype.Service;
import ra.demo.dto.response.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
}
