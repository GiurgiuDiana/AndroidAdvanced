package com.example.androidadvancedproject.presentation.View;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidadvancedproject.domain.LodgeItem;

import java.util.List;

public class LodgeBindingAdapter {

    @BindingAdapter("textTitleRes")
    public static void setTitle(TextView textView, @StringRes int textTitleRes) {
        Context context = textView.getContext();
        textView.setText(context.getString(textTitleRes));
    }

    @BindingAdapter("lodgeItems")
    public static void setItems(RecyclerView recyclerView, List<LodgeItem> items) {
        RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new LodgeItemAdapter();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        }
        if (items != null) {
            ((LodgeItemAdapter) adapter).updateItems(items);
        }
    }

}

