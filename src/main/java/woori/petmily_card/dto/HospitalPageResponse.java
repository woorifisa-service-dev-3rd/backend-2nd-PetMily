package woori.petmily_card.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;
import woori.petmily_card.entity.Hospital;

import java.util.List;

@Getter
public class HospitalPageResponse {
    private List<HospitalResponse> hospitals;
    private int currentPage;
    private int totalPage;

    private HospitalPageResponse(List<HospitalResponse> hospitals, int currentPage, int totalPage) {
        this.hospitals = hospitals;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public static HospitalPageResponse from(Page<Hospital> hospitals) {
        return new HospitalPageResponse(
                hospitals.map(HospitalResponse::from).getContent(),
                hospitals.getNumber() + 1,
                hospitals.getTotalPages());
    }
}
