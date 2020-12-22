package com.dawntechs.gebeyaEcommerceApp.restApi;

import com.dawntechs.gebeyaEcommerceApp.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    //in a real application there will be a query to limit the page number and start index

    @GET("products")
    Call<List<Product>> fetchProducts();
}
