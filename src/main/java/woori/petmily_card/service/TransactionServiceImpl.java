package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woori.petmily_card.dto.TransactionRequest;
import woori.petmily_card.dto.TransactionResponse;
import woori.petmily_card.entity.Card;
import woori.petmily_card.entity.Hospital;
import woori.petmily_card.entity.Transaction;
import woori.petmily_card.exception.ErrorCode;
import woori.petmily_card.exception.PetMilyException;
import woori.petmily_card.repository.CardRepository;
import woori.petmily_card.repository.HospitalRepository;
import woori.petmily_card.repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

import static woori.petmily_card.exception.ErrorCode.CARD_NOT_FOUND;
import static woori.petmily_card.exception.ErrorCode.HOSPITAL_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final HospitalRepository hospitalRepository;

    private final int SIZE = 10;

    @Override
    public void save(int cardNo, int hospitalNo, int amount) {
        transactionRepository.save(
                new TransactionRequest(getCard(cardNo), getHospital(hospitalNo), amount)
                .toTransaction());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponse> show(int cardNo, int page) {
        return getAllTransactionByCardNo(cardNo, page).stream().map(
                transaction -> TransactionResponse.of(transaction.getAmount(), transaction.getHospital()))
                .collect(Collectors.toList());
    }

    private Card getCard(int cardNo) {
        return cardRepository.findById(cardNo).orElseThrow(() -> new PetMilyException(CARD_NOT_FOUND));
    }

    private Hospital getHospital(int hospitalNo) {
        return hospitalRepository.findById(hospitalNo).orElseThrow(() -> new PetMilyException(HOSPITAL_NOT_FOUND));
    }

    private Page<Transaction> getAllTransactionByCardNo(int cardNo, int page) {
        Pageable pageable = PageRequest.of(page, SIZE);
        return transactionRepository.findAllByCard_CardNo(cardNo, pageable);
    }
}
