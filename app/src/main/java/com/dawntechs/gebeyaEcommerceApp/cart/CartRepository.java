package com.dawntechs.gebeyaEcommerceApp.cart;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.dawntechs.gebeyaEcommerceApp.AppDatabase;

import java.util.List;

public class CartRepository {

    private static final String TAG = CartRepository.class.getSimpleName();

    private CartDao cartDao;

    public CartRepository(Context context) {
        this.cartDao = AppDatabase.getDatabase(context).cartDao();
    }

    public LiveData<List<CartItem>> getCartItems() {
        return cartDao.getCartItems();
    }

    public void insert(CartItem cartItem) {
        cartDao.insert(cartItem);
    }

    public void updateCartItem(CartItem cartItem) {
        cartDao.updateCartItem(cartItem);
    }

    public LiveData<Integer> getItemCount() {
        return cartDao.getItemCount();
    }

    public LiveData<CartItem> getCartByProductId(float id) {
        return cartDao.getByProductId(id);
    }

    public void delete(CartItem cartItem) {
        cartDao.delete(cartItem);
    }
}
