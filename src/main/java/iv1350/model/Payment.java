package iv1350.model;

import iv1350.dto.PaymentDTO;

import java.time.LocalDateTime;

class Payment {
    private int amountPaid;
    private int amountOfChange;
    private LocalDateTime timeOfSale;

    Payment(int totalPrice, int amountPaid) {
        this.amountPaid = amountPaid;
        this.amountOfChange = amountPaid - totalPrice;
        timeOfSale = LocalDateTime.now();
    }

    PaymentDTO toDTO() {
        return new PaymentDTO(this.amountPaid, this.amountOfChange, timeOfSale);
    }
}
