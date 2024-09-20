package woori.petmily_card.entity;

public enum SaleType {
    DISCOUNT("할인"),
    REWARD("적립");

    private final String name;

    SaleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
