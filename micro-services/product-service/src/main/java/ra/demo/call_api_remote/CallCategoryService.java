package ra.demo.call_api_remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ra.demo.dto.response.Category;

import java.util.List;

@FeignClient(name = "category-service")
public interface CallCategoryService {
    @GetMapping("/api/v1/categories/data-for-product-call")
    List<Category> getCategories();

    //...
}
