package woori.petmily_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import woori.petmily_card.entity.Card;
import woori.petmily_card.repository.CardRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final Random random = new Random();

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @Override
    public Optional<Card> getCardBySerialNo(int serialNo) {
        return cardRepository.findBySerialNo(serialNo);
    }

    @Override
    public void issueCard(Card card) {

        int generatedSerialNo = generateSimpleCardNumber();
        card.setSerialNo(generatedSerialNo);
        card.setCardNumber(generateRandomCardNumber());
        card.setExpirationDate(LocalDate.now().plusYears(1));

        cardRepository.save(card);
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

    @Override
    public int generateSimpleCardNumber() {
        return 100000 + random.nextInt(900000);
    }

    @Override
    public int generateRandomCardNumber() {
        return random.nextInt(9000) + 1000;
    }

    @Override
    public Page<Card> findAllCards(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

}
