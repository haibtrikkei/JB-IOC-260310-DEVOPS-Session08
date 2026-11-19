package ra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ra.demo.call_api_remote.CallCategoryService;
import ra.demo.dto.response.ApiResponse;
import ra.demo.dto.response.Category;
import ra.demo.dto.response.Product;
import ra.demo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final RestTemplate restTemplate;
    private final CallCategoryService callCategoryService;

    public ProductController(ProductService productService, RestTemplate restTemplate, CallCategoryService callCategoryService) {
        this.productService = productService;
        this.restTemplate = restTemplate;
        this.callCategoryService = callCategoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getProducts() {
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Lấy danh sách sản phẩm thành công",
                productService.getProducts(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/call-category-service")
    public ResponseEntity<ApiResponse<String>> getCategories() {
        // String uri = "http://localhost:8086/api/v1/categories/data-for-product-call";
        String uri = "lb://category-service/api/v1/categories/data-for-product-call";

        String response = restTemplate.getForObject(uri, String.class);
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Lấy danh sách danh mục thành công",
                response,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/call-category-service/openfeign")
    public ResponseEntity<ApiResponse<List<Category>>> getCategoriesByOpenFeign() {
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Lấy danh sách danh mục từ controller sản phẩm thành công",
                callCategoryService.getCategories(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
