package com.dawntechs.gebeyaEcommerceApp.cart;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.dawntechs.gebeyaEcommerceApp.product.Product;

@Entity
public class CartItem {

    @PrimaryKey(autoGenerate = true)
    public int _id;

    @Embedded
    public Product product;

    public int quantity;

    public CartItem(){}

    @Ignore
    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

}
