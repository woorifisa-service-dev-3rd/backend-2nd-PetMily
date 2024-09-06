package woori.petmily_card.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hospitalNo;

    @Column(nullable = false)
    private String name;

    @Column(name = "sale_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SaleType saleType;

    @Column(nullable = false)
    private int sale;

    @Column(nullable = false, columnDefinition = "true")
    private boolean status;
}
