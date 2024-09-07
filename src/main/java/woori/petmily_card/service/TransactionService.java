package woori.petmily_card.service;

import woori.petmily_card.dto.TransactionResponse;

import java.util.List;

public interface TransactionService {
    int save(int cardNo, int hospitalNo, int amount);

    List<TransactionResponse> show(int cardNo, int page);
}
