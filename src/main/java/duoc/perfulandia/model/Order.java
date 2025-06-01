// NO TOCAR. MATCHEADO CORRECTAMENTE A DB.

package duoc.perfulandia.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// >> FUNCIONA, NO TOCAR.
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;

    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private int total;
}