package woori.petmily_card.service;

import woori.petmily_card.dto.MemberDto;
import woori.petmily_card.entity.Member;

public interface MemberService {
    void register(MemberDto memberDto);
    Member login(MemberDto memberDto);
    boolean checkDuplicateId(String id);   // 중복 아이디 확인
}
