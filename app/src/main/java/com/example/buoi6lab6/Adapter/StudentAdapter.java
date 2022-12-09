package com.example.buoi6lab6.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buoi6lab6.Helper.StudentDbHelper;
import com.example.buoi6lab6.Models.Faculty;
import com.example.buoi6lab6.Models.Student;
import com.example.buoi6lab6.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{

    List<Student> list;
    StudentDbHelper dbHelper;

    public StudentAdapter(List<Student> list, StudentDbHelper dbHelper) {
        this.list = list;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentAdapter.ViewHolder(viewHolder);
    }
    public void deleteItemAtPos(int pos){
        dbHelper.Delete(list.get(pos));
        list.remove(pos);
        notifyItemChanged(pos);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = list.get(position);
        String data = student.StudentId + " - " + student.StudentName + " - " +  student.DTB  + " - " +  student.FalcultyId;
        holder.textStudentName.setText(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textStudentName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textStudentName = itemView.findViewById(R.id.textStudentName);
        }
    }
}
