package woori.petmily_card.dto;

import lombok.Data;

@Data
public class MemberDto {
    private String name;
    private String id;
    private String password;
    private String phoneNumber;
}
