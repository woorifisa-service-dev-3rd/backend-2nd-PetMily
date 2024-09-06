package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.MemberDto;
import woori.petmily_card.entity.Member;
import woori.petmily_card.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void register(MemberDto memberDto) {
        Member member = Member.builder()
                .name(memberDto.getName())
                .id(memberDto.getId())
                .password(memberDto.getPassword())
                .phoneNumber(memberDto.getPhoneNumber())
                .point(0)  // 초기 포인트 설정
                .build();
        memberRepository.save(member);
    }

    @Override
    public Member login(MemberDto memberDto) {
        return memberRepository.findByIdAndPassword(memberDto.getId(), memberDto.getPassword());
    }
}
