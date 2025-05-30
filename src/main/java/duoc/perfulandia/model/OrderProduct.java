package duoc.perfulandia.model;

import jakarta.persistence.*;

// >> FUNCIONA, NO TOCAR.
@Entity
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;
    private Long buyPrice;
}

