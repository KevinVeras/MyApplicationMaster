package com.example.myapplicationapp.ui.dispensador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplicationapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class DashboardFragment extends Fragment {

    View vista;

    RecyclerView recyclerView;
    MainAdapterHorario mainAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView =vista.findViewById(R.id.listDispen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<ModelDispensador> options =
                new FirebaseRecyclerOptions.Builder<ModelDispensador>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("dispensador"), ModelDispensador.class)
                        .build();
        mainAdapter=new MainAdapterHorario(options);
        recyclerView.setAdapter(mainAdapter);

        return vista;
    }

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}