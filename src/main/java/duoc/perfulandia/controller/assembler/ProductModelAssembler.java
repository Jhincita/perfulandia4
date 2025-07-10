package duoc.perfulandia.controller.assembler;

import duoc.perfulandia.controller.CartController;
import duoc.perfulandia.model.Product;
import duoc.perfulandia.controller.ProductController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(
                product,
                linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products")
                //,linkTo(methodOn(CartController.class).addToCart(product.getId())).withRel("add-to-cart")
        );
    }



}
