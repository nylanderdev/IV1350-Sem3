package iv1350.model;

import iv1350.dto.ItemDTO;

class Item {
    private ItemDTO itemInfo;

    Item(ItemDTO itemDTO) {
        this.itemInfo = itemDTO;
    }

    int getId() {
        return itemInfo.ID;
    }

    int getPriceAfterVAT() {
        return itemInfo.PRICE;
    }

    int getVATRate() {
        return itemInfo.VAT_RATE;
    }

    ItemDTO toDTO() {
        return this.itemInfo;
    }

    int getVAT() {
        return getPriceAfterVAT() * getVATRate() / 100;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            return ((Item)obj).getId() == this.getId();
        }
        return false;
    }
}
