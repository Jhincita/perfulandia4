package duoc.perfulandia.controller;
import duoc.perfulandia.model.Cart;
import duoc.perfulandia.model.CartItem;
import duoc.perfulandia.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //create, para los users que ya estaban en la abse de datos (o para crear manualmente en caso de problema x)
    @PostMapping("/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long userId) {
        try {
            Cart cart = cartService.createCart(userId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    //read
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        try {
            Cart cart = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // update cantidad
    @PutMapping("/{userId}/quantity/{itemId}")
    public ResponseEntity<Cart> updateItemQuantity(
            @PathVariable Long userId,
            @PathVariable Long itemId,
            @RequestParam int quantity) {

        try {
            Cart cart = cartService.updateItemQuantity(userId, itemId, quantity);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // HACWER ADDTOCART -> revisar si funciona bien

    @PostMapping("/{userId}/add")
    public Cart addToCart(@PathVariable Long userId, @RequestBody CartItem cartItem) {
        return cartService.addToCart(userId, cartItem);
    }





}
