package com.dawntechs.gebeyaEcommerceApp.cart.ui;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.R;
import com.dawntechs.gebeyaEcommerceApp.cart.CartItem;
import com.dawntechs.gebeyaEcommerceApp.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CartItemsActivity extends BaseActivity implements CartListAdapter.CartAdapterCallback {

    private CartListAdapter cartListAdapter;
    private CartViewModel viewModel;
    private List<CartItem> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);

        setUpToolbar("Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cartListAdapter = new CartListAdapter(this, cartItems);

        RecyclerView recyclerView = findViewById(R.id.cart_rv);

        recyclerView.setAdapter(cartListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        viewModel = new CartViewModel((MainApp) this.getApplicationContext());

        viewModel.getCartItems().observe(this, cartItems -> {
            cartListAdapter.setData(cartItems);
            this.cartItems = cartItems;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //persist updates
        viewModel.updateCart(cartItems);
    }

    @Override
    public void removeItem(CartItem cartItem) {
        viewModel.deleteCartItem(cartItem);
    }
}