package com.example.buoi6lab6.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buoi6lab6.Helper.StudentDbHelper;
import com.example.buoi6lab6.Models.Faculty;
import com.example.buoi6lab6.R;

import java.util.List;

public class FacultyAdapter extends
        RecyclerView.Adapter<FacultyAdapter.ViewHolder> {
    List<Faculty> listFaculty;
    StudentDbHelper db;

    public FacultyAdapter(List<Faculty> listFaculty,StudentDbHelper db) {
        this.listFaculty = listFaculty;
        this.db = db;
    }

    @NonNull
    @Override
    public FacultyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faculty, parent, false);
        return new ViewHolder(viewHolder);
    }
    public void deleteItemAtPos(int pos){
        db.Delete(listFaculty.get(pos));
        listFaculty.remove(pos);
        notifyItemChanged(pos);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyAdapter.ViewHolder holder,
                                 int position) {
        Faculty f = listFaculty.get(position);
        holder.textFacultyName.setText(f.FacultyId + " - " + f.FacultyName);


    }

    @Override
    public int getItemCount() {
        return listFaculty.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textFacultyName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textFacultyName = itemView.findViewById(R.id.textStudentName);
        }
    }
}
