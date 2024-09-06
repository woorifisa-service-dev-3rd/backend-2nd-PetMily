package woori.petmily_card.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String id;
    private String password;
    private String phoneNumber;
}
