package com.dawntechs.gebeyaEcommerceApp.product.list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.R;
import com.dawntechs.gebeyaEcommerceApp.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private ProductListAdapter adapter;

    ProductListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        adapter = new ProductListAdapter(this, new ArrayList<Product>());

        RecyclerView bs_recyclerView = findViewById(R.id.best_sell_product_rv);
        RecyclerView ft_recyclerView = findViewById(R.id.featured_product_rv);

        bs_recyclerView.setLayoutManager(new LinearLayoutManager(bs_recyclerView.getContext(), RecyclerView.HORIZONTAL, false));
        ft_recyclerView.setLayoutManager(new LinearLayoutManager(ft_recyclerView.getContext(), RecyclerView.HORIZONTAL, false));

        bs_recyclerView.setAdapter(adapter);
        ft_recyclerView.setAdapter(adapter);

        viewModel = new ProductListViewModel((MainApp) this.getApplicationContext());

        viewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setData(products);
            }
        });

    }
}