package com.example.homeworkadditem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItemAdapter extends RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder> {

    private List<ShoppingItem> itemList;
    private final OnItemClickListener listener;

    public ShoppingItemAdapter(List<ShoppingItem> itemList, OnItemClickListener listener) {
        this.itemList = itemList != null ? itemList : new ArrayList<>();
        this.listener = listener;
    }

    public void updateList(List<ShoppingItem> newList) {
        this.itemList = newList != null ? newList : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopping, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (itemList == null || position >= itemList.size()) return;
        ShoppingItem item = itemList.get(position);
        holder.tvName.setText(item.getName());
        holder.tvQuantity.setText("Qty: " + item.getQuantity());
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvQuantity;
        ImageView imgDelete, imgEdit;

        ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);

            imgDelete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onDeleteClick(position);
                }
            });

            imgEdit.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onEditClick(position);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onDeleteClick(int position);
        void onEditClick(int position);
    }
}