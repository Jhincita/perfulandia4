package duoc.perfulandia.service;
import duoc.perfulandia.model.*;
import duoc.perfulandia.model.Repo.CartRepo;
import duoc.perfulandia.model.Repo.OrderRepo;
import duoc.perfulandia.model.Repo.ProductRepo;
import duoc.perfulandia.model.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// AGREGAR Y QUITAR ITEMS DE CARRITO, MANEJO DE CARRITO.
@Service
public class CartService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductRepo productRepo;

    // create by userid
    public Cart createCart(Long userId) {
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("No existe USERID: " + userId);
        }
        // ver si existe -> evitar duplicados
        if (cartRepo.findByUserId(userId) != null) {
            throw new RuntimeException("Ya hay carrito para USERID:" + userId);
        }
        Cart cart = new Cart();
        cart.setUser(userOpt.get());
        cart.setItems(new ArrayList<>());
        return cartRepo.save(cart);
    }

    // read cart
    public Cart getCartByUserId(Long userId) {
        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null) {
            throw new RuntimeException("Carrito no encontrado para USERID: " + userId);
        }
        return cart;
    }

    // delete
    public void deleteCart(Long cartId) {
        if (!cartRepo.existsById(cartId)) {
            throw new RuntimeException("Cart not found");
        }
        cartRepo.deleteById(cartId);
    }

    // add item -> arreglar

    public Cart addToCart(Long userId, CartItem newItem) {
        Cart cart = getCartByUserId(userId);

        // validar producto
        if (newItem.getProduct() == null || newItem.getProduct().getId() == null) {
            throw new RuntimeException("Product must have a valid ID");
        }

        //  actualizar cantidad si el producto ya está en carrito:
        for (CartItem existingItem : cart.getItems()) {
            if (existingItem.getProduct().getId().equals(newItem.getProduct().getId())) {
                existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
                return cartRepo.save(cart);
            }
        }
        newItem.setCart(cart);
        cart.getItems().add(newItem);
        return cartRepo.save(cart);
    }


    // remove item
    public Cart removeItemFromCart(Long userId, Long itemId) {
        Cart cart = getCartByUserId(userId);
        // "Objectss" compara 2 objetos, equivale a (a == b) || (a != null && a.equals(b))
        cart.getItems().removeIf(item -> Objects.equals(item.getId(), itemId));
        return cartRepo.save(cart);
    }

    //update cantidad deitems
    public Cart updateItemQuantity(Long userId, Long itemId, int quantity) {
        Cart cart = getCartByUserId(userId);
        for (CartItem item : cart.getItems()) {
            if (item.getId().equals(itemId)) {
                item.setQuantity(quantity);
                break;
            }
        }
        return cartRepo.save(cart);
    }

    //checkout method :: HACER --> CHECKOUT METHOD LISTO, PROBAR.  >> metodo migrado a OrderService.
    /* public Order checkout(Long userId){
        Cart cart = getCartByUserId(userId);

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            if (product.getInventory()< cartItem.getQuantity()) {
                throw new RuntimeException("No hay suficiente stock de " + product.getName());
            }
        }
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDateTime.now());

    } */

}
