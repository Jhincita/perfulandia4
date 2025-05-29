package duoc.perfulandia.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// MATCHEADO A DB. NO TOCAR
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand; // class brand?
    private String notes; // notas olfativas. class note?
    private int price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private int ml;
    private int inventory; // class stock?


}
