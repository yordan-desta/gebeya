package com.dawntechs.gebeyaEcommerceApp.product.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dawntechs.gebeyaEcommerceApp.R;
import com.dawntechs.gebeyaEcommerceApp.common.EntityListAdapter;
import com.dawntechs.gebeyaEcommerceApp.product.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements EntityListAdapter<Product> {

    private List<Product> items;
    private final Context context;

    ProductListAdapter(Context context, List<Product> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ProductListAdapter.ViewHolder holder, int position) {

        holder.item = items.get(position);

        holder.price.setText(String.format("%s%s", holder.item.currency, holder.item.price));
        holder.desc.setText(holder.item.nom);
        Picasso.get().load(holder.item.imageUrl).into(holder.imageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: page 2 - go to detail
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void setData(List<Product> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View mView;
        private final TextView price;
        private final TextView desc;
        private final ImageView imageView;

        public Product item;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            price = view.findViewById(R.id.price);
            desc = view.findViewById(R.id.description);
            imageView = view.findViewById(R.id.product_image);
        }
    }
}

