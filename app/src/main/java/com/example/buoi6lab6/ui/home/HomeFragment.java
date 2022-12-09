package com.example.buoi6lab6.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buoi6lab6.Adapter.FacultyAdapter;
import com.example.buoi6lab6.Adapter.StudentAdapter;
import com.example.buoi6lab6.Helper.StudentDbHelper;
import com.example.buoi6lab6.Helper.SwipeItemFaculty;
import com.example.buoi6lab6.Helper.SwipeItemStudent;
import com.example.buoi6lab6.Models.Faculty;
import com.example.buoi6lab6.Models.Student;
import com.example.buoi6lab6.R;
import com.example.buoi6lab6.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView.Adapter adapter;
    List<Student> studentList;
    StudentDbHelper dbHelper;
    List<Faculty> faculties;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new StudentDbHelper(getContext());
        RecyclerView rcvStudent = view.findViewById(R.id.rcvStudent);
        studentList = dbHelper.GetAllStudent();
        faculties = dbHelper.GetAllFalculty();
        List<String> falcutiesNameList = new ArrayList<>();
        for (Faculty item:
             faculties) {
            falcutiesNameList.add(item.FacultyName);
        }
        adapter = new StudentAdapter(studentList,dbHelper);
        rcvStudent.setAdapter(adapter);
        rcvStudent.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rcvStudent.addItemDecoration(itemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new
                SwipeItemStudent((StudentAdapter) adapter));
        itemTouchHelper.attachToRecyclerView(rcvStudent);
        view.findViewById(R.id.floatingAddStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewDiaglogFaculty = LayoutInflater.from(getContext()).inflate(R.layout.dialog_student,null);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setView(viewDiaglogFaculty);

                AlertDialog alert = builder.create();
                alert.show();

                EditText dialogInputMaSV = viewDiaglogFaculty.findViewById(R.id.dialogInputMaSV);
                EditText dialogInputTenSV = viewDiaglogFaculty.findViewById(R.id.dialogInputTenSV);
                EditText dialogInputDTB = viewDiaglogFaculty.findViewById(R.id.dialogInputDtB);
                Spinner dialogInputMaKhoa = viewDiaglogFaculty.findViewById(R.id.spinnerKhoa);

                ArrayAdapter<String> spinnerAdapter =  new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, falcutiesNameList);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dialogInputMaKhoa.setAdapter(spinnerAdapter);

                viewDiaglogFaculty.findViewById(R.id.dialogSaveStudent).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Student obj = new Student();
                        obj.StudentId = Integer.parseInt(dialogInputMaSV.getText().toString());
                        obj.StudentName = dialogInputTenSV.getText().toString();
                        obj.DTB = dialogInputDTB.getText().toString();
                        obj.FalcultyId = faculties.get(dialogInputMaKhoa.getSelectedItemPosition()).FacultyId;

                        dbHelper.Insert(obj);
                        studentList.add(obj);
                        adapter.notifyItemInserted(studentList.size()-1);

                        Toast.makeText(getContext(),"Save completed",Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}