package woori.petmily_card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woori.petmily_card.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}