package mk.ukim.finki.wp.exam.example.web;

import mk.ukim.finki.wp.exam.example.model.User;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.security.core.Authentication;

import java.util.List;

public class ProductsController {

    private final ProductService service;

    public ProductsController(ProductService service) {
        this.service = service;
    }


    public String showProducts(String nameSearch, Long categoryId) {
        if (nameSearch == null && categoryId == null) {
            this.service.listAllProducts();
        } else {
            this.service.listProductsByNameAndCategory(nameSearch, categoryId);
        }
        return "";
    }

    public String showAdd() {

        return "";
    }

    public String showEdit(Long id) {
        this.service.findById(id);
        return "";
    }


    public String create(String name, Double price, Integer quantity, List<Long> categories) {
        this.service.create(name, price, quantity, categories);
        return "";
    }

    public String update(Long id, String name, Double price, Integer quantity, List<Long> categories) {
        this.service.update(id, name, price, quantity, categories);
        return "";
    }

    public String delete(Long id) {
        this.service.delete(id);
        return "";
    }
}
