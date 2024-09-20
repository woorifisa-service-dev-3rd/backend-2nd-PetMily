package woori.petmily_card.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import woori.petmily_card.entity.Card;

import java.util.Optional;

public interface CardService {

    Optional<Card> getCardBySerialNo(int serialNo);
    void issueCard(Card card);
    boolean cancelCard(int cardNo);
    int generateSimpleCardNumber();
    int generateRandomCardNumber();
    Page<Card> findAllCards(Pageable pageable);
}