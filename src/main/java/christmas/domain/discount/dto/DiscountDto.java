package christmas.domain.discount.dto;

public class DiscountDto {
    private String description;
    private Integer price;

    public DiscountDto(String description, Integer price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }
}
