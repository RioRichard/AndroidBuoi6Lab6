package com.example.buoi6lab6.Helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buoi6lab6.Adapter.StudentAdapter;

public class SwipeItemStudent extends ItemTouchHelper.SimpleCallback {
    StudentAdapter adapter;

    public SwipeItemStudent( StudentAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        this.adapter.deleteItemAtPos(pos); //viết hàm xóa tại vị trí
    }
}
