package woori.petmily_card.service;

import woori.petmily_card.dto.HospitalPageResponse;
import woori.petmily_card.dto.HospitalResponse;

import java.util.List;

public interface HospitalService {
    HospitalPageResponse showAll(int page);

    HospitalResponse show(int hospitalNo);
}
