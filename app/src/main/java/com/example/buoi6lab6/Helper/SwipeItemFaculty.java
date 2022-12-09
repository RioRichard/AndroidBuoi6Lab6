package com.example.buoi6lab6.Helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buoi6lab6.Adapter.FacultyAdapter;

public class SwipeItemFaculty extends ItemTouchHelper.SimpleCallback {
    FacultyAdapter mAdapter;

    public SwipeItemFaculty(FacultyAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull
            RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int
            direction) {
        int pos = viewHolder.getAdapterPosition();
        this.mAdapter.deleteItemAtPos(pos); //viết hàm xóa tại vị trí
    }
}
