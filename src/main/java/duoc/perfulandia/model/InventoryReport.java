package duoc.perfulandia.model;

public class InventoryReport {
    private Long productId;
    private String productName;
    private int inventory;

    public InventoryReport(Long productId, String productName, int inventory) {
        this.productId = productId;
        this.productName = productName;
        this.inventory = inventory;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getInventory() { return inventory; }
    public void setInventory(int inventory) { this.inventory = inventory; }
}
