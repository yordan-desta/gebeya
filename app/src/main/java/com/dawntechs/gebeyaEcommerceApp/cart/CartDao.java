package com.dawntechs.gebeyaEcommerceApp.cart;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dawntechs.gebeyaEcommerceApp.product.Product;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CartItem cartItem);

    @Delete
    void delete(CartItem cartItem);

    @Query("SELECT * FROM cartitem ORDER BY _id DESC")
    LiveData<List<CartItem>> getCartItems();

    @Query("DELETE FROM cartitem where id=:productId")
    void deleteByProduct(float productId);

    @Update()
    void updateCartItem(CartItem cartItem);

    @Query("SELECT COUNT(*) FROM CARTITEM")
    LiveData<Integer> getItemCount();
}

