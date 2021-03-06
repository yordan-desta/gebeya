package com.dawntechs.gebeyaEcommerceApp.product.list;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.R;
import com.dawntechs.gebeyaEcommerceApp.cart.ui.CartItemsActivity;
import com.dawntechs.gebeyaEcommerceApp.common.BaseActivity;
import com.dawntechs.gebeyaEcommerceApp.product.Product;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends BaseActivity {

    private ProductListAdapter adapter;

    ProductListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_container);

        setUpToolbar(getString(R.string.app_name));

        //todo: page 1 - search

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

        viewModel.getCartItemsCount().observe(this, count -> {
            cart_count = count;
            invalidateOptionsMenu();
        });

        ViewPager mPager = findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
    }

    protected int cart_count = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(getCartIcon());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart_action) {
            startActivity(new Intent(this, CartItemsActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }

    private Drawable getCartIcon() {

        View view = LayoutInflater.from(this).inflate(R.layout.badge_icon_layout, null);
        if (cart_count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = view.findViewById(R.id.count);
            textView.setText(MessageFormat.format(" {0}", cart_count));
        }

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        return new BitmapDrawable(getResources(), view.getDrawingCache());
    }

    private static final int PAGES = 3;

    private static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        public Fragment getItem(int position) {
            return new SliderPageFragment();
        }

        @Override
        public int getCount() {
            return PAGES;
        }
    }
}