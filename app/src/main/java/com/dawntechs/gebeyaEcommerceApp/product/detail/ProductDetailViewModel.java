package com.dawntechs.gebeyaEcommerceApp.product.detail;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dawntechs.gebeyaEcommerceApp.cart.CartItem;
import com.dawntechs.gebeyaEcommerceApp.cart.CartRepository;

public class ProductDetailViewModel extends ViewModel {
    private CartRepository repository;
    LiveData<CartItem> cartItem;

    public ProductDetailViewModel (Application application) {
        super();
        repository = new CartRepository(application);
    }

    public LiveData<CartItem> getProduct(float id) {
        if (cartItem == null) {
            cartItem = repository.getCartByProductId(id);
        }
        return cartItem;
    }

    public void addToCart(CartItem cartItem) {
        repository.insert(cartItem);
    }

    public void removeFromCart(CartItem cartItem){
        repository.delete(cartItem);
    }
}
