package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.MemberDto;
import woori.petmily_card.entity.Member;
import woori.petmily_card.exception.ErrorCode;
import woori.petmily_card.exception.PetMilyException;
import woori.petmily_card.repository.MemberRepository;

import static woori.petmily_card.exception.ErrorCode.ALREADY_EXIST_ID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void register(MemberDto memberDto) {
        if (checkDuplicateId(memberDto.getId())) {
            throw new PetMilyException(ALREADY_EXIST_ID);
        }

        Member member = Member.builder()
                .name(memberDto.getName())
                .id(memberDto.getId())
                .password(memberDto.getPassword())
                .phoneNumber(memberDto.getPhoneNumber())
                .point(0)
                .build();
        memberRepository.save(member);
    }

    @Override
    public Member login(MemberDto memberDto) {
        // 아이디로 회원이 존재하는지 체크
        Member member = memberRepository.findById(memberDto.getId());
        if (member == null) {
            throw new PetMilyException(ErrorCode.MEMBER_NOT_FOUND); // 회원이 존재하지 않을 때 예외 발생
        }
        // 비밀번호가 일치하는지 체크
        if (!member.getPassword().equals(memberDto.getPassword())) {
            throw new PetMilyException(ErrorCode.INVALID_INPUT); // 비밀번호 불일치 시 예외 발생
        }

        return member; // 회원이 존재하고 비밀번호가 일치하면 로그인 성공
    }

    private boolean checkDuplicateId(String id) {
        return memberRepository.findById(id) != null;
    }
}
