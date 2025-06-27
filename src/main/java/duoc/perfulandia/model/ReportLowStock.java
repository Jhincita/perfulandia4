package duoc.perfulandia.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ReportLowStock{
    private Long productId;
    private String productName;
    private double inventory;
    
    public ReportLowStock(Long productId, String productName, int inventory) {
        this.productId = productId;
        this.productName = productName;
        this.inventory = inventory;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double  getInventory() { return inventory; }
    public void setInventory(int inventory) { this.inventory = inventory; }

}
