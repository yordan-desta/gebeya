package com.dawntechs.gebeyaEcommerceApp.cart;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartItem cartItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CartItem> cartItems);

    @Delete
    void delete(CartItem cartItem);

    @Query("SELECT * FROM CartItem ORDER BY local_id DESC")
    LiveData<List<CartItem>> getCartItems();

    @Query("DELETE FROM CartItem where id=:productId")
    void deleteByProduct(long productId);

    @Update()
    void updateCartItem(CartItem cartItem);

    @Query("SELECT COUNT(*) FROM CartItem")
    LiveData<Integer> getItemCount();

    @Query("select * from CartItem where id=:id limit 1")
    LiveData<CartItem> getByProductId(long id);
}

