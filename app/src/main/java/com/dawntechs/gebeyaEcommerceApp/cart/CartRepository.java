package com.dawntechs.gebeyaEcommerceApp.cart;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dawntechs.gebeyaEcommerceApp.AppDatabase;
import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.product.Product;
import com.dawntechs.gebeyaEcommerceApp.restApi.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {

    private static final String TAG = CartRepository.class.getSimpleName();

    private CartDao cartDao;

    public CartRepository(Context context){
        this.cartDao = AppDatabase.getDatabase(context).cartDao();
    }

    public LiveData<List<CartItem>> getCartItems(){
        return cartDao.getCartItems();
    }

    public void insert(CartItem cartItem) {
        cartDao.insert(cartItem);
    }

    public void updateCartItem(CartItem cartItem){
        cartDao.updateCartItem(cartItem);
    }

    public LiveData<Integer> getItemCount() {
        return cartDao.getItemCount();
    }
}
