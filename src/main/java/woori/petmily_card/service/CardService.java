package woori.petmily_card.service;

import woori.petmily_card.entity.Card;

import java.util.Optional;

public interface CardService {

    Optional<Card> getCardBySerialNo(int serialNo);
    void issueCard(Card card);
    boolean cancelCard(int cardNo);
    int generateSimpleCardNumber();
    int generateRandomCardNumber();
}