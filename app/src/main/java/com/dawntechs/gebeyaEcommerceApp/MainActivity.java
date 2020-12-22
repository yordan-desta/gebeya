package com.dawntechs.gebeyaEcommerceApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dawntechs.gebeyaEcommerceApp.common.BaseActivity;
import com.dawntechs.gebeyaEcommerceApp.product.list.ProductListActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is usually a splash screen or a started activity

        startActivity(new Intent(this, ProductListActivity.class));
        finish();
    }
}