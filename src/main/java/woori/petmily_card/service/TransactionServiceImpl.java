package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woori.petmily_card.dto.TransactionRequest;
import woori.petmily_card.entity.Card;
import woori.petmily_card.entity.Hospital;
import woori.petmily_card.exception.ErrorCode;
import woori.petmily_card.exception.PetMilyException;
import woori.petmily_card.repository.CardRepository;
import woori.petmily_card.repository.HospitalRepository;
import woori.petmily_card.repository.TransactionRepository;

import static woori.petmily_card.exception.ErrorCode.CARD_NOT_FOUND;
import static woori.petmily_card.exception.ErrorCode.HOSPITAL_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public void save(int cardNo, int hospitalNo, int amount) {
        transactionRepository.save(
                new TransactionRequest(getCard(cardNo), getHospital(hospitalNo), amount)
                .toTransaction());
    }

    private Card getCard(int cardNo) {
        return cardRepository.findById(cardNo).orElseThrow(() -> new PetMilyException(CARD_NOT_FOUND));
    }

    private Hospital getHospital(int hospitalNo) {
        return hospitalRepository.findById(hospitalNo).orElseThrow(() -> new PetMilyException(HOSPITAL_NOT_FOUND));
    }
}
