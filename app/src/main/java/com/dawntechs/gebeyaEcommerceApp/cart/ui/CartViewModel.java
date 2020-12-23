package com.dawntechs.gebeyaEcommerceApp.cart.ui;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dawntechs.gebeyaEcommerceApp.cart.CartItem;
import com.dawntechs.gebeyaEcommerceApp.cart.CartRepository;

import java.util.List;

public class CartViewModel extends ViewModel {

    private CartRepository repository;

    private final LiveData<List<CartItem>> cartItems;

    public CartViewModel (Application application) {
        super();
        repository = new CartRepository(application);
        cartItems = repository.getCartItems();
    }

    LiveData<List<CartItem>> getCartItems() { return cartItems; }

    public void insert(CartItem cartItem) { repository.insert(cartItem); }

    public void update(CartItem cartItem) { repository.updateCartItem(cartItem); }

    public void updateCart(List<CartItem> cartItems) { repository.updateCart(cartItems); }

    public LiveData<Integer> getItemCount(){ return repository.getItemCount();}

    public void deleteCartItem(CartItem cartItem) {
        repository.delete(cartItem);
    }
}
