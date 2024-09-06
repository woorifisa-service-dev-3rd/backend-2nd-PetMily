package woori.petmily_card.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import woori.petmily_card.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Page<Transaction> findAllByCard_CardNo(int cardNo, Pageable pageable);
}
