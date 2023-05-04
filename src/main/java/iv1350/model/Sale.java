package iv1350.model;

import iv1350.dto.DiscountDTO;
import iv1350.dto.ItemDTO;
import iv1350.dto.PaymentDTO;
import iv1350.dto.SaleDTO;
import iv1350.integration.DAOCollection;
import iv1350.integration.DiscountDAO;
import iv1350.integration.ItemDAO;
import iv1350.integration.SaleDAO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a sale process from start to finish
 */
public class Sale {
    private ItemDAO itemDAO;
    private DiscountDAO discountDAO;
    private SaleDAO saleDAO;
    private int totalPrice;
    private int totalVat;
    private Map<Item, Integer> itemsAndQuantities;
    private List<Discount> discounts;
    private Payment payment;
    private boolean itemsFinalized = false;

    /**
     * Initializes a new and empty sale
     */
    public Sale() {
        this.itemDAO = DAOCollection.getSingleton().getItemDAO();
        this.discountDAO = DAOCollection.getSingleton().getDiscountDAO();
        this.saleDAO = DAOCollection.getSingleton().getSaleDAO();
        this.itemsAndQuantities = new LinkedHashMap<>();
        this.discounts = new ArrayList<>();
        this.totalPrice = 0;
        this.totalVat = 0;
    }

    /**
     * Gets the state of the sale in the form of a SaleDTO
     * @return A SaleDTO object containing information on the total price & VAT, items, discounts, and payment (if any)
     */
    public SaleDTO getSaleState() {
        Map<ItemDTO, Integer> itemDTOsAndQuantities = new HashMap<>();
        for (Map.Entry<Item, Integer> itemOfQuantity : this.itemsAndQuantities.entrySet()) {
            Item item = itemOfQuantity.getKey();
            int quantity = itemOfQuantity.getValue();
            itemDTOsAndQuantities.put(item.toDTO(), quantity);
        }
        itemDTOsAndQuantities = Collections.unmodifiableMap(itemDTOsAndQuantities);
        List<DiscountDTO> discountDTOs = this.discounts.stream().map(Discount::toDTO).collect(Collectors.toList());
        PaymentDTO paymentDTO = null;
        if (payment != null) {
            paymentDTO = payment.toDTO();
        }
        return new SaleDTO(this.totalPrice, this.totalVat, itemDTOsAndQuantities, discountDTOs, paymentDTO);
    }

    /**
     * Adds a singular copy of the item of the corresponding itemId to the sale
     * @param itemId The id of an item to add
     */
    public void addItem(int itemId) {
        addItemWithQuantity(itemId, 1);
    }

    /**
     * Adds multiple copies of the item of the corresponding itemId to the sale
     * @param itemId The id of an item to add
     */
    public void addItemWithQuantity(int itemId, int quantity) {
        ItemDTO itemDTO = this.itemDAO.fetchItemById(itemId);
        if (itemDTO != null) {
            Item item = new Item(itemDTO);
            addItemInternal(item, quantity);
        }
    }

    /**
     * Finalizes the items and quantities thereof added to the sale, disallowing further adjustments
     */
    public void finalizeItemSelection() {
        this.itemsAndQuantities = Collections.unmodifiableMap(this.itemsAndQuantities);
        this.itemsFinalized = true;
    }

    /**
     * Applies all discounts for which the customer is elgible
     * @param customerId The id of the customer in question
     */
    public void applyEligibleDiscounts(int customerId) {
        List<DiscountDTO> discountsDTOs = this.discountDAO.fetchDiscountsByCustomerId(customerId);
        List<Discount> discountsToApply = discountsDTOs.stream().map(Discount::new).collect(Collectors.toList());
        this.discounts.addAll(discountsToApply);
        for (Discount discount : discountsToApply) {
            applyDiscount(discount);
        }
    }

    /**
     * Registers a payment and ends the sale
     * @param amountPaid The amount paid
     */
    public void registerPayment(int amountPaid) {
        payment = new Payment(this.totalPrice, amountPaid);
        saleDAO.recordSale(getSaleState());
        decreaseItemQuantitiesViaDAO();
    }

    private void addItemInternal(Item item, int quantity) {
        if (itemsFinalized) {
            return;
        }
        this.itemsAndQuantities.putIfAbsent(item, 0);
        int oldQuantity = this.itemsAndQuantities.get(item);
        int newQuantity = oldQuantity + quantity;
        this.itemsAndQuantities.put(item, newQuantity);
        this.totalPrice += quantity * item.getPriceAfterVAT();
        this.totalVat += quantity * item.getVAT();
    }

    private void applyDiscount(Discount discount) {
        this.totalPrice = discount.totalAfterDiscount(this.totalPrice);
        this.totalVat = discount.totalAfterDiscount(this.totalVat);
    }

    private void decreaseItemQuantitiesViaDAO() {
        for (Map.Entry<Item, Integer> itemAndQuantity : itemsAndQuantities.entrySet()) {
            Item item = itemAndQuantity.getKey();
            int quantity = itemAndQuantity.getValue();
            itemDAO.decreaseQuantityOfItem(item.getId(), quantity);
        }
    }
}
