package woori.petmily_card.service;

import woori.petmily_card.entity.Card;

import java.util.Optional;

public interface CardService {

    Optional<Card> getCardBySerialNo(int serialNo);
    Card issueCard(Card card);
    boolean cancelCard(int cardNo);
}
