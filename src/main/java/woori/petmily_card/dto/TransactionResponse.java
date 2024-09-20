package woori.petmily_card.dto;

import lombok.Getter;
import woori.petmily_card.entity.Hospital;
import woori.petmily_card.entity.SaleType;

@Getter
public class TransactionResponse {
    private int amount;
    private HospitalResponse hospital;

    private TransactionResponse(int amount, HospitalResponse hospital) {
        this.amount = amount;
        this.hospital = hospital;
    }

    public static TransactionResponse of(int cardNo, Hospital hospital) {
        return new TransactionResponse(cardNo, HospitalResponse.from(hospital));
    }
}
