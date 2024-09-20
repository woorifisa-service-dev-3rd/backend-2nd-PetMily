package woori.petmily_card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woori.petmily_card.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Integer> {

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findById(String id);

}
