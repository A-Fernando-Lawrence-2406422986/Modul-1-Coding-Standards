package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if(product.getProductId() == null){
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(String id){
        for(Product product : productData){
            if(product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }

    public Product edit(Product product){
        for(Product allProduct : productData){
            if(allProduct.getProductId().equals(product.getProductId())){
                allProduct.setProductName(product.getProductName());
                allProduct.setProductQuantity(product.getProductQuantity());
                return allProduct;
            }
        }
        return null;
    }
}