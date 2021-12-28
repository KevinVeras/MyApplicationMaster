package com.example.myapplicationapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationapp.R;
import com.example.myapplicationapp.databinding.FragmentHomeBinding;

import com.example.myapplicationapp.ui.dispensador.DashboardFragment;
import com.example.myapplicationapp.ui.mascota.MascotaFragment;
import com.example.myapplicationapp.ui.medico.MedicoFragment;
import com.example.myapplicationapp.ui.vacunas.VacunasFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Fragment newFragment = new MascotaFragment();
        Fragment fragDispe= new DashboardFragment();
        Fragment fragVacunas= new VacunasFragment();
        Fragment fragMedico= new MedicoFragment();

        ImageView Mas=root.findViewById(R.id.imageView2);
        ImageView Vac=root.findViewById(R.id.imageView4);
        ImageView  Medi=root.findViewById(R.id.imageView6);
        ImageView  aseo=root.findViewById(R.id.imageView5);
        ImageView  disp=root.findViewById(R.id.imageView7);
        ImageView  otrsos=root.findViewById(R.id.imageView8);

        Mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homefrm,newFragment).commit();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
            }
        });
        Vac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homefrm,fragVacunas).commit();

                //getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
            }
        });
        Medi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homefrm,fragMedico).commit();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
            }
        });
        aseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homefrm,fragVacunas).commit();

                //getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
            }
        });
        disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homefrm,fragDispe).commit();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
              //  getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
            }
        });
        otrsos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homefrm,fragVacunas).commit();

                //getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}