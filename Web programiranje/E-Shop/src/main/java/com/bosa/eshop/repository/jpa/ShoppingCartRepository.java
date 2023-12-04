package com.bosa.eshop.repository.jpa;

import com.bosa.eshop.model.ShoppingCart;
import com.bosa.eshop.model.User;
import com.bosa.eshop.model.enumeration.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
