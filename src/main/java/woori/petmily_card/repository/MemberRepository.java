package woori.petmily_card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woori.petmily_card.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByIdAndPassword(String id, String password);
    Member findById(String id);
}
