package woori.petmily_card.dto;

import lombok.Getter;

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

    public static MemberDto of(String name, String id, String password, String phoneNumber) {
        return new MemberDto(name, id, password, phoneNumber);
    }
}
