package com.dawntechs.gebeyaEcommerceApp.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.dawntechs.gebeyaEcommerceApp.common.BaseEntity;

/**
 * Product model
 **/

public class Product extends BaseEntity implements Parcelable {

    public long id;
    //data encapsulation ignored for simplicity
    public String nom;

    public double price;

    public String currency;

    public String imageUrl;

    public Product(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nom);
        parcel.writeString(currency);
        parcel.writeString(imageUrl);
        parcel.writeDouble(price);
    }

    public static final Parcelable.Creator<Product> CREATOR
            = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    private Product(Parcel in) {
        id = in.readLong();
        nom = in.readString();
        currency = in.readString();
        imageUrl = in.readString();
        price = in.readDouble();
    }
}
