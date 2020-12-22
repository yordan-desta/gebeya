package com.dawntechs.gebeyaEcommerceApp.product.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.product.Product;
import com.dawntechs.gebeyaEcommerceApp.product.ProductRepository;

import java.util.List;

public class ProductListViewModel extends ViewModel {

    private LiveData<List<Product>> products;
    private ProductRepository repository;

    public ProductListViewModel(MainApp app) {
        //we would use DI for this purpose - left for simplicity
        repository = new ProductRepository(app);
    }

    public LiveData<List<Product>> getProducts() {
        if (products == null) {
            products = repository.getProducts();
        }
        return products;
    }
}
