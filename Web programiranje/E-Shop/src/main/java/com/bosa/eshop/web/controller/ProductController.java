package com.bosa.eshop.web.controller;

import com.bosa.eshop.model.Category;
import com.bosa.eshop.model.Manufacturer;
import com.bosa.eshop.model.Product;
import com.bosa.eshop.service.CategoryService;
import com.bosa.eshop.service.ManufacturerService;
import com.bosa.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    @GetMapping
    public String getProduct(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";

    }

    @PostMapping ("/delete/{id}")
    public String deleteProduct( @PathVariable Long id ){
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/add-form")
    public String addProductPage( Model model ){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long manufacturer,
                              @RequestParam Long category){
        productService.save(name,price,quantity,category,manufacturer);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProduct( @PathVariable Long id, Model model ){
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }


}
