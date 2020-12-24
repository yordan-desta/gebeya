package com.dawntechs.gebeyaEcommerceApp.product.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.R;
import com.dawntechs.gebeyaEcommerceApp.cart.CartItem;
import com.dawntechs.gebeyaEcommerceApp.common.BaseActivity;
import com.dawntechs.gebeyaEcommerceApp.product.Product;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends BaseActivity {

    private boolean productInCart = false;

    private ProductDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        final Product product = getProductFromIntent();

        setUpToolbar("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (product != null) {

            ImageView imageView = findViewById(R.id.product_image);
            TextView prodName = findViewById(R.id.name);
            TextView price = findViewById(R.id.price);

            Picasso.get().load(product.imageUrl).into(imageView);
            prodName.setText(product.nom);
            price.setText(String.format("%s%s", product.currency, product.price));

            Button addToCart = findViewById(R.id.add_to_cart);

            viewModel = new ProductDetailViewModel((MainApp) this.getApplicationContext());

            viewModel.getProduct(product.id).observe(this, cartItem -> {
                if (cartItem == null) {
                    productInCart = false;
                    addToCart.setText(R.string.add_to_cart);
                } else {
                    productInCart = true;
                    addToCart.setText(R.string.remove_from_cart);
                }
            });

            addToCart.setOnClickListener(view -> {
                if (!productInCart) {
                    viewModel.addToCart(new CartItem(product, 1));
                    Toast.makeText(view.getContext(), "Added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.removeFromCartByProduct(product.id);
                    Toast.makeText(view.getContext(), "Removed from cart", Toast.LENGTH_SHORT).show();
                }
            });

        } else finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private Product getProductFromIntent() {
        Intent intent = getIntent();

        if (intent == null)
            return null;

        return intent.getParcelableExtra(Product.class.getSimpleName());
    }
}