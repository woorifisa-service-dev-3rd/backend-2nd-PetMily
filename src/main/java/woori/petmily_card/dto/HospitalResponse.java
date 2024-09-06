package woori.petmily_card.dto;

import lombok.Getter;
import woori.petmily_card.entity.Hospital;
import woori.petmily_card.entity.SaleType;

@Getter
public class HospitalResponse {
    private int hospitalNo;
    private String name;
    private String saleType;
    private int sale;

    private HospitalResponse(int hospitalNo, String name, SaleType saleType, int sale) {
        this.hospitalNo = hospitalNo;
        this.name = name;
        this.saleType = saleType.getName();
        this.sale = sale;
    }

    public static HospitalResponse from(Hospital hospital) {
        return new HospitalResponse(hospital.getHospitalNo(), hospital.getName(),
                hospital.getSaleType(), hospital.getSale());
    }
}
