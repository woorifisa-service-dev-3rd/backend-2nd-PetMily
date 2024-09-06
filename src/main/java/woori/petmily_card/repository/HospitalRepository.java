package woori.petmily_card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woori.petmily_card.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
