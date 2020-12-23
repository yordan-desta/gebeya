package com.dawntechs.gebeyaEcommerceApp.cart;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.dawntechs.gebeyaEcommerceApp.common.BaseEntity;
import com.dawntechs.gebeyaEcommerceApp.product.Product;

@Entity
public class CartItem extends BaseEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    public int local_id;

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
