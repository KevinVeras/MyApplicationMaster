package com.example.myapplicationapp.ui.mascota;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationapp.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {


    Button btn;
    View vista;
    EditText nombre,tipo,edad;
    Button btnRegistrar,btnCancel;
    CheckBox rabia,distemper,parvovirus;

    TextView tv_valor,valopm,valopme,valorgrande,valorgigante;
    Spinner spinner;
    DatabaseReference dbref;
    ValueEventListener listener;
    ArrayList<String> list,list2;
    ArrayAdapter<String> adapter,adapter2;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        vista=inflater.inflate(R.layout.fragment_add, container, false);
        btn=vista.findViewById(R.id.btnBack);

       // vista.setContentView(R.layout.fragment_add);
        nombre=vista.findViewById(R.id.txtNombreR);
        tipo=vista.findViewById(R.id.txtTipoR);
        edad=vista.findViewById(R.id.txtEdadR);
        btnRegistrar=vista.findViewById(R.id.btnRegistrar);
        btnCancel=vista.findViewById(R.id.btnBack);

        rabia=vista.findViewById(R.id.vRabia);
        distemper=vista.findViewById(R.id.vDistemper);
        parvovirus=vista.findViewById(R.id.vParvovirus);

        tv_valor=vista.findViewById(R.id.valosipin);
        valopm=vista.findViewById(R.id.valorpesom);
        valopme=vista.findViewById(R.id.valorpesome);
        valorgrande=vista.findViewById(R.id.valorgrande);
        valorgigante=vista.findViewById(R.id.valorgigante);
        spinner=vista.findViewById(R.id.idspiner);

        DatabaseReference ref1=dbref=FirebaseDatabase.getInstance().getReference("edadM");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<String>();
                for (DataSnapshot areaSnapshot: snapshot.getChildren()){


                    String areaName=areaSnapshot.child("cachorro").getValue(String.class);
                    String areaapeso=areaSnapshot.child("mini").getValue(String.class);
                    String areaapeso2=areaSnapshot.child("mediano").getValue(String.class);
                    String areaapeso3=areaSnapshot.child("grande").getValue(String.class);
                    String areaapeso4=areaSnapshot.child("gigante").getValue(String.class);

                    list.add(areaName+" % "+areaapeso+" : "+areaapeso2+"$"+areaapeso3+"#"+areaapeso4);
                }
                adapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,list);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String item=parent.getSelectedItem().toString();

                        String pm=item;
                        int cont=item.length();
                        String tot=String.valueOf(cont);

                        String pm2=item;
                        String pm3=item;

                        String parte1=pm3.substring(0,pm3.lastIndexOf('%'));
                        String parte2=pm2.substring(pm2.lastIndexOf('%')+1,pm2.lastIndexOf(':'));
                        String parte3=pm2.substring(pm2.lastIndexOf(':')+1,pm2.lastIndexOf('$'));
                        String parte4=pm2.substring(pm2.lastIndexOf('$')+1,pm2.lastIndexOf('#'));
                        String parte5=pm2.substring(pm2.lastIndexOf('#')+1);

                        tv_valor.setText(parte1);
                        valopm.setText(parte2);
                        valopme.setText(parte3);
                        valorgrande.setText(parte4);
                        valorgigante.setText(parte5);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertaDatos();
               //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frmas,newFragment).commit();
            }
        });
        /*
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
*/
        /////////


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Regreso a inicio",Toast.LENGTH_SHORT).show();

            }
        });


        // Inflate the layout for this fragment
        return vista;
    }

    public void datosSelect(){
        listener=dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata: snapshot.getChildren())
                    list.add(mydata.getValue().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void vacunas(){
        if (rabia.isChecked()==true)
            rabia.setText("Rabia");

        else
            rabia.setText("");

        if (distemper.isChecked()==true)
            distemper.setText("Distemper");
        else
            distemper.setText("");

        if (parvovirus.isChecked()==true)
            parvovirus.setText("Parvovirus");
        else
            parvovirus.setText("");
    }
    private void insertaDatos(){

        vacunas();
        Fragment newFragment = new MascotaFragment();
        Map<String,Object> map=new HashMap<>();
        map.put("nombre",nombre.getText().toString());
        map.put("tipo",tipo.getText().toString());
        map.put("edad",edad.getText().toString());

        map.put("vDistemper",distemper.getText().toString());
        map.put("vParvovirus",parvovirus.getText().toString());
        map.put("vRabia",rabia.getText().toString());

        map.put("tamanio",tv_valor.getText().toString());
        map.put("tmini",valopm.getText().toString());
        map.put("tmediano",valopme.getText().toString());
        map.put("tgrande",valorgrande.getText().toString());
        map.put("tgigante",valorgigante.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("mascotag").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(),"Se registro correctamenta",Toast.LENGTH_SHORT).show();
                        limpiar();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frmas,newFragment).commit();
                        getActivity().getSupportFragmentManager().beginTransaction().remove(newFragment).commit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Error No se registro ",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void limpiar(){
        nombre.setText("");
        tipo.setText("");
        edad.setText("");
        distemper.setText("");
        parvovirus.setText("");
        rabia.setText("");
        tv_valor.setText("");
        valopm.setText("");
        valopme.setText("");
        valorgrande.setText("");
        valorgigante.setText("");

    }
}