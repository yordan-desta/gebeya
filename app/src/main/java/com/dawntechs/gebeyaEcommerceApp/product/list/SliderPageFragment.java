package com.dawntechs.gebeyaEcommerceApp.product.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.dawntechs.gebeyaEcommerceApp.R;
import com.squareup.picasso.Picasso;

public class SliderPageFragment extends Fragment {

    final String dummyProductImage = "https://www.adorama.com/images/Large/btmx422lla.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.slider, container, false);

        ImageView productI = view.findViewById(R.id.product_image);

        Picasso.get().load(dummyProductImage).into(productI);

        return view;
    }
}