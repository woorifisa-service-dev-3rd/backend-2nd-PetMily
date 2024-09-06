package woori.petmily_card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woori.petmily_card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
