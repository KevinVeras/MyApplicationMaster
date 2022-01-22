package com.example.myapplicationapp.ui.mascota;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {

    CheckBox VrabiaEdit, VdistemperEdit, VPoarvoEdit;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull MainModel model) {

        holder.nombre.setText(model.getNombre());
        // holder.tipo.setText(model.getTipo());
        //holder.edad.setText(model.getEdad());


        holder.btndetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.Img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.detalle_mascota))
                        .setExpanded(true, 1700)
                        .create();
                // dialogPlus.show();


                View view = dialogPlus.getHolderView();
                TextView nombreM = view.findViewById(R.id.nombremascota);
                TextView edadM = view.findViewById(R.id.edadmascota);


                TextView miniM = view.findViewById(R.id.miniM);
                TextView grandeM = view.findViewById(R.id.grandeM);
                TextView medianoM = view.findViewById(R.id.medianoM);
                TextView giganteM = view.findViewById(R.id.giganteM);

                TextView vRabiaM = view.findViewById(R.id.vRabiaM);
                TextView vDistemperM = view.findViewById(R.id.vDistemperM);
                TextView vParvovirusM = view.findViewById(R.id.vParvovirusM);


                //Button btnActualizar=view.findViewById(R.id.btnActualizar);
////////////////////////////////
                nombreM.setText(model.getNombre());
                edadM.setText(model.getTamanio());


                miniM.setText(model.getTmini());
                medianoM.setText(model.getTmediano());
                grandeM.setText(model.getTgrande());
                giganteM.setText(model.getTgigante());

                //vRabiaM.setText(model.getvRabia());
                //vDistemperM.setText(model.getvDistemper());
                //vParvovirusM.setText(model.getvParvovirus());


                if (model.getvRabia().equals("Rabia")) {

                    vRabiaM.setText(model.getvRabia());
                } else {

                    vRabiaM.setVisibility(View.GONE);
                }

                if (model.getvDistemper().equals("Distemper")) {

                    vDistemperM.setText(model.getvDistemper());
                } else {
                    vDistemperM.setVisibility(View.GONE);

                }

                if (model.getvParvovirus().equals("Parvovirus")) {
                    vParvovirusM.setText(model.getvParvovirus());

                } else {

                    vParvovirusM.setVisibility(View.GONE);
                }


                dialogPlus.show();


            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.Img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.editar_mascota))
                        .setExpanded(true, 1800)
                        .create();
                dialogPlus.show();


                View view = dialogPlus.getHolderView();
                EditText EditNombre = view.findViewById(R.id.nombremascotaEdit);

                //EditText tipo=view.findViewById(R.id.txtTipo);
                //EditText edad=view.findViewById(R.id.txtEdad);
/*
                EditText mini=view.findViewById(R.id.tminio);
                EditText mediano=view.findViewById(R.id.tmediano);
                TextView grande=view.findViewById(R.id.tgrande);
                TextView gigante=view.findViewById(R.id.tgigante);

*/
                VrabiaEdit = view.findViewById(R.id.vRabiaEdit);
                VdistemperEdit = view.findViewById(R.id.vDistemperedit);
                VPoarvoEdit = view.findViewById(R.id.vParvovirusEdit);

                TextView vrabia = view.findViewById(R.id.vRabiaME);
                TextView vdistemper = view.findViewById(R.id.vDistemperME);
                TextView vparavirus = view.findViewById(R.id.vParvovirusME);


                Button BtnActualizar = view.findViewById(R.id.btnactualizar);
////////////////////////////////
                EditNombre.setText(model.getNombre());
                /*
                tipo.setText(model.getTipo());
                edad.setText(model.getEdad());

                mini.setText(model.getTmini());
                mediano.setText(model.getTmediano());
                grande.setText(model.getTgrande());
                gigante.setText(model.getTgigante());

                VrabiaEdit.setText(model.getvRabia());
                VdistemperEdit.setText(model.getvDistemper());
                VPoarvoEdit.setText(model.getvParvovirus());*/

                if (model.getvRabia().equals("Rabia")) {
                    VrabiaEdit.setChecked(true);
                } else {
                    VrabiaEdit.setChecked(false);
                }

                if (model.getvDistemper().equals("Distemper")) {
                    VdistemperEdit.setChecked(true);
                } else {
                    VdistemperEdit.setChecked(false);
                }

                String parvo = "Parvovirus";
                if (model.getvParvovirus().equals(parvo)) {
                    VPoarvoEdit.setChecked(true);
                } else {
                    VPoarvoEdit.setChecked(false);
                }
                ;
                //VdistemperEdit.setChecked(true);

                vrabia.setText(model.getvRabia());
                vdistemper.setText(model.getvDistemper());
                vparavirus.setText(model.getvParvovirus());


                dialogPlus.show();

                BtnActualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vacunas();
                        Map<String, Object> map = new HashMap<>();
                        map.put("nombre", EditNombre.getText().toString());
                        //map.put("tipo",tipo.getText().toString());
                        //map.put("edad",edad.getText().toString());
                        //VrabiaEdit,VdistemperEdit,VPoarvoEdit;


                        map.put("vRabia", VrabiaEdit.getText().toString());
                        map.put("vDistemper", VdistemperEdit.getText().toString());
                        map.put("vParvovirus", VPoarvoEdit.getText().toString());

                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = mAuth.getCurrentUser();

                        assert user != null;
                        FirebaseDatabase.getInstance().getReference().child("mascotag").child(user.getUid()).
                                child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nombre.getContext(), "Datos actualizados", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.nombre.getContext(), "error al mostrar", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nombre.getContext());
                builder.setTitle("Eliminar");
                builder.setMessage("Desa elimiar a su amscota");
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        assert user != null;
                        FirebaseDatabase.getInstance().getReference().child("mascotag").child(user.getUid())
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.nombre.getContext(), "se Cancelo", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    private void vacunas() {
        //VrabiaEdit,VdistemperEdit,VPoarvoEdit;
        if (VrabiaEdit.isChecked() == true)
            VrabiaEdit.setText("Rabia");

        else
            VrabiaEdit.setText("");

        if (VdistemperEdit.isChecked() == true)
            VdistemperEdit.setText("Distemper");
        else
            VdistemperEdit.setText("");

        if (VPoarvoEdit.isChecked() == true)
            VPoarvoEdit.setText("Parvovirus");
        else
            VPoarvoEdit.setText("");
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new myViewHolder(view1);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        ImageView Img;
        TextView nombre, tipo, edad;

        Button btnEdit, btnDelete, btnActualizar, btndetalle;

        FloatingActionButton floatingActionButton;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Img = itemView.findViewById(R.id.imgM);
            nombre = itemView.findViewById(R.id.nametext);

            //tipo=itemView.findViewById(R.id.tipotext);
            //edad=itemView.findViewById(R.id.edadtext);

            btndetalle = itemView.findViewById(R.id.btnver);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }


    }
}
