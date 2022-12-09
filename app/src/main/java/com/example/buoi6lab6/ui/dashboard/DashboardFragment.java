package com.example.buoi6lab6.ui.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.buoi6lab6.Helper.StudentDbHelper;
import com.example.buoi6lab6.Helper.SwipeItemFaculty;
import com.example.buoi6lab6.Models.Faculty;
import com.example.buoi6lab6.R;
import com.example.buoi6lab6.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    RecyclerView.Adapter adapter;
    List<Faculty> faculties ;
    StudentDbHelper dbHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        DashboardViewModel dashboardViewModel =
//                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        dbHelper = new StudentDbHelper(getContext());
        RecyclerView rcvFaculty = view.findViewById(R.id.rcvFaculty);
        faculties = dbHelper.GetAllFalculty();
        adapter = new FacultyAdapter(faculties,dbHelper);
        rcvFaculty.setAdapter(adapter);
        rcvFaculty.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rcvFaculty.addItemDecoration(itemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new
                SwipeItemFaculty((FacultyAdapter) adapter));
        itemTouchHelper.attachToRecyclerView(rcvFaculty);
        view.findViewById(R.id.floatingAddFaculty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewDiaglogFaculty = LayoutInflater.from(getContext()).inflate(R.layout.dialog_faculty,null);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setView(viewDiaglogFaculty);

                AlertDialog alert = builder.create();
                alert.show();

                EditText dialogInputMaKhoa = viewDiaglogFaculty.findViewById(R.id.dialogInputMaKhoa);
                EditText dialogInputTenKhoa = viewDiaglogFaculty.findViewById(R.id.dialogInputTenKhoa);

                viewDiaglogFaculty.findViewById(R.id.dialogSaveFaculty).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Faculty obj = new Faculty();
                        obj.FacultyId = Integer.parseInt(dialogInputMaKhoa.getText().toString());
                        obj.FacultyName = dialogInputTenKhoa.getText().toString();

                        dbHelper.Insert(obj);
                        faculties.add(obj);
                        adapter.notifyItemInserted(faculties.size()-1);

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