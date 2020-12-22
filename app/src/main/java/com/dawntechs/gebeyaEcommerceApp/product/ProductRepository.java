package com.dawntechs.gebeyaEcommerceApp.product;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.restApi.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private Api restApi;

    private static final String TAG = ProductRepository.class.getSimpleName();

    public ProductRepository(MainApp context){
        restApi = context.getRestApi();
    }

    public LiveData<List<Product>> getProducts(){
        final MutableLiveData<List<Product>> products = new MutableLiveData<>();

        restApi.fetchProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if(response.isSuccessful())
                    products.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call,@NonNull Throwable t) {
                //leaving the error-handling for simplicity
                Log.e(TAG, t.getLocalizedMessage(), t);
            }
        });

        return products;
    }
}
