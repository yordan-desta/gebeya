package com.dawntechs.gebeyaEcommerceApp.product.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.R;
import com.dawntechs.gebeyaEcommerceApp.cart.CartItem;
import com.dawntechs.gebeyaEcommerceApp.common.BaseActivity;
import com.dawntechs.gebeyaEcommerceApp.product.Product;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends BaseActivity {

    private View.OnClickListener addToCartListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //todo: implement me
            Toast.makeText(view.getContext(), "Added to card", Toast.LENGTH_SHORT).show();
        }
    };

    private ProductDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        final Product product = getProductFromIntent();

        if (product != null) {

            ImageView imageView = findViewById(R.id.product_image);
            TextView prodName = findViewById(R.id.name);
            TextView price = findViewById(R.id.price);

            Picasso.get().load(product.imageUrl).into(imageView);
            prodName.setText(product.nom);
            price.setText(String.format("%s%s", product.currency, product.price));

            Button addToCart = findViewById(R.id.add_to_cart);

            viewModel = new ProductDetailViewModel((MainApp) this.getApplicationContext());

            viewModel.getProduct(product.id).observe(this, new Observer<CartItem>() {

                @Override
                public void onChanged(CartItem cartItem) {
                    if (cartItem == null) {
                        Toast.makeText(getApplicationContext(), "Not in cart Item", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "In cart Item", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //create a cart with default quantity 1
                    viewModel.addToCart(new CartItem(product, 1));
                }
            });

        } else finish();

    }

    private Product getProductFromIntent() {
        Intent intent = getIntent();

        if (intent == null)
            return null;

        return intent.getParcelableExtra(Product.class.getSimpleName());
    }
}