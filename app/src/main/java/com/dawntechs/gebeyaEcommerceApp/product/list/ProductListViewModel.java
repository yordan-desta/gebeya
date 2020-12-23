package com.dawntechs.gebeyaEcommerceApp.product.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dawntechs.gebeyaEcommerceApp.MainApp;
import com.dawntechs.gebeyaEcommerceApp.cart.CartRepository;
import com.dawntechs.gebeyaEcommerceApp.product.Product;
import com.dawntechs.gebeyaEcommerceApp.product.ProductRepository;

import java.util.List;

public class ProductListViewModel extends ViewModel {

    private LiveData<List<Product>> products;
    private ProductRepository repository;
    private CartRepository cartRepository;

    public ProductListViewModel(MainApp app) {
        super();
        //we would use DI in a real app to handle it - left for simplicity
        repository = new ProductRepository(app);
        cartRepository = new CartRepository(app);
    }

    public LiveData<List<Product>> getProducts() {
        if (products == null) {
            products = repository.getProducts();
        }
        return products;
    }

    public LiveData<Integer> getCartItemsCount(){
        return cartRepository.getItemCount();
    }
}
