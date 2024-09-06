package woori.petmily_card.service;

import org.springframework.data.domain.Page;
import woori.petmily_card.dto.HospitalResponse;

import java.util.List;

public interface HospitalService {
    Page<HospitalResponse> showAll(int page);

    HospitalResponse show(int hospitalNo);
}
