package woori.petmily_card.service;

import org.springframework.data.domain.Page;
import woori.petmily_card.dto.TransactionResponse;

import java.util.List;

public interface TransactionService {
    int save(int cardNo, int hospitalNo, int amount);

    Page<TransactionResponse> show(int memberNo, int page);
}
