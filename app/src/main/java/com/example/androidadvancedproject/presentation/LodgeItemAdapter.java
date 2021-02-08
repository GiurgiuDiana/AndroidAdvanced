package com.example.androidadvancedproject.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidadvancedproject.R;
import com.example.androidadvancedproject.databinding.ListItemBinding;
import com.example.androidadvancedproject.domain.LodgeItem;

import java.util.ArrayList;
import java.util.List;

public class LodgeItemAdapter  extends RecyclerView.Adapter<LodgeItemAdapter.LodgeItemViewHolder> {
    private final List<LodgeItem> items;

    public LodgeItemAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public LodgeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding  binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item, parent, false);

        return new LodgeItemViewHolder (binding);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LodgeItemViewHolder holder, int position) {
        LodgeItem item = items.get(position);
        holder.bind(item);
    }

    public void updateItems(List<LodgeItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    static class LodgeItemViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public LodgeItemViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(LodgeItem model) {
            binding.setModel(model);
        }
    }
}
