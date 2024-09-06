package woori.petmily_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woori.petmily_card.entity.Card;
import woori.petmily_card.repository.CardRepository;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @Override
    public Optional<Card> getCardBySerialNo(int serialNo) {
        return cardRepository.findBySerialNo(serialNo);
    }

    @Override
    public Card issueCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public boolean cancelCard(int cardNo) {
        Optional<Card> cardOptional = cardRepository.findById(cardNo);
        if (cardOptional.isPresent()) {
            cardRepository.deleteById(cardNo);
            return true;
        }
        return false;
    }
}
