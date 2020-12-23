package com.dawntechs.gebeyaEcommerceApp.cart.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dawntechs.gebeyaEcommerceApp.R;
import com.dawntechs.gebeyaEcommerceApp.cart.CartItem;
import com.dawntechs.gebeyaEcommerceApp.common.EntityListAdapter;
import com.dawntechs.gebeyaEcommerceApp.product.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> implements EntityListAdapter<CartItem> {

    private List<CartItem> items;
    private final Context context;

    CartListAdapter(Context context, List<CartItem> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final CartListAdapter.ViewHolder holder, int position) {

        final CartItem item = items.get(position);
        final Product product = item.product;

        holder.price.setText(String.format("%s%s", product.currency, product.price));
        holder.name.setText(product.nom);
        holder.vendor.setText("Vendor X");

        holder.qty.setText(String.valueOf(item.quantity));

        Picasso.get().load(product.imageUrl).into(holder.imageView);

        holder.plustQty.setOnClickListener(view -> {
            item.quantity += 1;
            holder.qty.setText(String.valueOf(item.quantity));
        });
        holder.minustQty.setOnClickListener(view -> {
            item.quantity = Math.max((item.quantity - 1), 0);
            holder.qty.setText(String.valueOf(item.quantity));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void setData(List<CartItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView price;
        private final TextView vendor;
        private final TextView name;
        private final TextView qty;

        private final Button plustQty;
        private final Button minustQty;

        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            price = view.findViewById(R.id.price);
            vendor = view.findViewById(R.id.vendor);
            name = view.findViewById(R.id.name);
            imageView = view.findViewById(R.id.product_image);

            plustQty = view.findViewById(R.id.plus);
            minustQty = view.findViewById(R.id.minus);
            qty = view.findViewById(R.id.quantity);
        }
    }
}

