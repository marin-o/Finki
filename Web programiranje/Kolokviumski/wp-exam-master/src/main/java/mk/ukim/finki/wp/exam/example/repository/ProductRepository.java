package mk.ukim.finki.wp.exam.example.repository;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoriesContaining(Category category);

    List<Product> findAllByNameLike(String nameLike);

    List<Product> findAllByNameLikeAndCategoriesContaining(String nameLike, Category category);
}
