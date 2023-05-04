package iv1350.model;

import iv1350.dto.DiscountDTO;

class Discount {
    private int percentageOff;

    public Discount(DiscountDTO dto) {
        this.percentageOff = dto.PERCENTAGE_OFF;
    }

    int totalAfterDiscount(int oldTotal) {
        return oldTotal * (100 - percentageOff) / 100;
    }

    DiscountDTO toDTO() {
        return new DiscountDTO(percentageOff);
    }
}
