package com.dawntechs.gebeyaEcommerceApp.cart.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.R;
import com.dawntechs.gebeyaEcommerceApp.cart.CartItem;
import com.dawntechs.gebeyaEcommerceApp.product.list.ProductListViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartItemsActivity extends AppCompatActivity implements CartListAdapter.CartUpdateActionCallback {

    private CartListAdapter cartListAdapter;
    private CartViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);

        cartListAdapter = new CartListAdapter(this, new ArrayList<CartItem>(), this);

        RecyclerView recyclerView = findViewById(R.id.cart_rv);

        recyclerView.setAdapter(cartListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        viewModel = new CartViewModel((MainApp) this.getApplicationContext());
        viewModel.getCartItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.setData(cartItems);
            }
        });
    }

    @Override
    public void CartItemUpdated(CartItem cartItem) {
        //we would probably like to make bach updates instead of entry updates - for obvious performance issues. skipping that for simplicity
        viewModel.update(cartItem);
    }
}