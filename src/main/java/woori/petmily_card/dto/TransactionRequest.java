package woori.petmily_card.dto;

import woori.petmily_card.entity.Card;
import woori.petmily_card.entity.Hospital;
import woori.petmily_card.entity.Transaction;

public class TransactionRequest {
    private Card card;
    private Hospital hospital;
    private int amount;

    private TransactionRequest() {}

    private TransactionRequest(Card card, Hospital hospital, int amount) {
        this.card = card;
        this.hospital = hospital;
        this.amount = amount;
    }

    public static TransactionRequest of(Card card, Hospital hospital, int amount) {
        return new TransactionRequest(card, hospital, amount);
    }

    public Transaction toTransaction() {
        return Transaction.builder().card(card).hospital(hospital).amount(amount).build();
    }
}
