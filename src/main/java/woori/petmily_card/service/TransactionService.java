package woori.petmily_card.service;

public interface TransactionService {
    void save(int cardNo, int hospitalNo, int amount);
}
