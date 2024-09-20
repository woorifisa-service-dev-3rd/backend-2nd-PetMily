package woori.petmily_card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woori.petmily_card.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Integer> {

}
