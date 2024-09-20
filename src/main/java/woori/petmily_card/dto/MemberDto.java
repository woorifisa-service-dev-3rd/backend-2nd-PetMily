package woori.petmily_card.dto;

import lombok.Getter;
import woori.petmily_card.entity.Member;

@Getter
public class MemberDto {
    private String name;
    private String id;
    private String password;
    private String phoneNumber;

    private MemberDto(String name, String id, String password, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Member toMember() {
        return Member.builder()
                .name(name)
                .id(id)
                .password(password)
                .phoneNumber(phoneNumber)
                .point(0)
                .build();
    }
}
