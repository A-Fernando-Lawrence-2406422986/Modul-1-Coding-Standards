package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    public void setProductQuantity(int productQuantity){
        if(productQuantity<0){
            return;
        }
        this.productQuantity=productQuantity;
    }

    public void setProductName(String productName){
        if(productName == null || productName.isEmpty()){
            return;
        }
        this.productName=productName;
    }
}
