package ca.lazanomentsoa.cartservice.repository;

import ca.lazanomentsoa.cartservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByUserIdAndProductId(Long userId, Long productId);
}
