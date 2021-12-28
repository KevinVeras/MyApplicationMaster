package com.example.myapplicationapp.ui.dispensador;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationapp.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MainAdapterHorario extends FirebaseRecyclerAdapter<ModelDispensador, MainAdapterHorario.myViewHolder> {

    int t1H,t1M,t2H,t2M;
    int t3H,t3M,t4H,t4M;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterHorario(@NonNull FirebaseRecyclerOptions<ModelDispensador> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull ModelDispensador model) {
        holder.Cagua.setText(model.getCantidadAgua());
        holder.Ccomida.setText(model.getCantidadComida());
        if (model.getAutomatico().equals("1")){
            holder.AutomtaicoT.setText("Automatico(ON))");
        }else {
            holder.AutomtaicoT.setText("Automatico(OFF))");
        }
        if (model.getProgramable().equals("1")){
            holder.ProgramableT.setText("Programable(ON))");
        }else {
            holder.ProgramableT.setText("Programable(OFF))");
        }
       // holder.AutomtaicoT.setText(model.getAutomatico());
        //holder.ProgramableT.setText(model.getProgramable());

        holder.btnProgra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.AutomtaicoT.getContext())
                        .setContentHolder(new ViewHolder(R.layout.detalle_programable))
                        .setExpanded(true,1700)
                        .create();
                View view=dialogPlus.getHolderView();
                TextView hora1=view.findViewById(R.id.tv_timer1);
                TextView hora2=view.findViewById(R.id.tv_timer2);
                TextView hora3=view.findViewById(R.id.tv_timer3);
                TextView hora4=view.findViewById(R.id.tv_timer4);
                Button btnProg=view.findViewById(R.id.btnModConfigura);
                hora1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int selectHour, int sletedMinute) {
                                t1H=selectHour;
                                t1M=sletedMinute;
                                hora1.setText(String.format(Locale.getDefault(),"%02d:%02d",t1H,t1M));
                            }
                        };
                        int style= AlertDialog.THEME_HOLO_LIGHT;
                        TimePickerDialog timePickerDialog=new TimePickerDialog(hora1.getContext(), style,onTimeSetListener,t1H,t1M,false);
                        timePickerDialog.setTitle("selecione hora");
                        timePickerDialog.show();
                    }
                });

                hora2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int selectHour, int sletedMinute) {
                                t2H=selectHour;
                                t2M=sletedMinute;
                                hora2.setText(String.format(Locale.getDefault(),"%02d:%02d",t2H,t2M));
                            }
                        };
                        int style= AlertDialog.THEME_HOLO_LIGHT;
                        TimePickerDialog timePickerDialog=new TimePickerDialog(hora2.getContext(), style,onTimeSetListener,t1H,t1M,false);
                        timePickerDialog.setTitle("selecione hora");
                        timePickerDialog.show();
                    }
                });

                hora3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int selectHour, int sletedMinute) {
                                t3H=selectHour;
                                t3M=sletedMinute;
                                hora3.setText(String.format(Locale.getDefault(),"%02d:%02d",t3H,t3M));
                            }
                        };
                        int style= AlertDialog.THEME_HOLO_LIGHT;
                        TimePickerDialog timePickerDialog=new TimePickerDialog(hora3.getContext(), style,onTimeSetListener,t1H,t1M,false);
                        timePickerDialog.setTitle("selecione hora");
                        timePickerDialog.show();
                    }
                });
                hora4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int selectHour, int sletedMinute) {
                                t4H=selectHour;
                                t4M=sletedMinute;
                                hora4.setText(String.format(Locale.getDefault(),"%02d:%02d",t4H,t4M));
                            }
                        };
                        int style= AlertDialog.THEME_HOLO_LIGHT;
                        TimePickerDialog timePickerDialog=new TimePickerDialog(hora4.getContext(), style,onTimeSetListener,t1H,t1M,false);
                        timePickerDialog.setTitle("selecione hora");
                        timePickerDialog.show();
                    }
                });

                hora1.setText(model.getHorario1());
                hora2.setText(model.getHorario2());
                hora3.setText(model.getHorario3());
                hora4.setText(model.getHorario4());
                 dialogPlus.show();
                btnProg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            Map<String,Object> map=new HashMap<>();
                            map.put("Horario1",hora1.getText().toString());
                            map.put("Horario2",hora2.getText().toString());
                            map.put("Horario3",hora3.getText().toString());
                            map.put("Horario4",hora4.getText().toString());
                            map.put("Automatico","0".toString());
                            map.put("Programable","1".toString());

                            FirebaseDatabase.getInstance().getReference().child("dispensador").
                                    child(getRef(position).getKey()).updateChildren(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(holder.AutomtaicoT.getContext(),"Modop rogramable actiado",Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(holder.AutomtaicoT.getContext(),"error al mostrar",Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
                                        }
                                    });


                    }
                });
            }
        });
        holder.btnAut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.AutomtaicoT.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dettalle_automatico))
                        .setExpanded(true,1300)
                        .create();

                View view=dialogPlus.getHolderView();
                TextView automa=view.findViewById(R.id.Modautomatico);
                Button btnac=view.findViewById(R.id.btnModAuto);
                automa.setText("1");


                dialogPlus.show();
                btnac.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("Automatico",automa.getText().toString());
                        map.put("Programable","0".toString());

                        FirebaseDatabase.getInstance().getReference().child("dispensador").
                                child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.AutomtaicoT.getContext(),"Modo automatico actiado",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.AutomtaicoT.getContext(),"error al mostrar",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.horario_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView Cagua,Ccomida,AutomtaicoT,ProgramableT;

        Button btnAut,btnProgra;




        public myViewHolder(@NonNull View itemView) {
            super(itemView);

           Cagua=itemView.findViewById(R.id.CAgua);

            Ccomida=itemView.findViewById(R.id.Ccomida);
            AutomtaicoT=itemView.findViewById(R.id.Automatico);
            ProgramableT=itemView.findViewById(R.id.Programable);

            btnAut=itemView.findViewById(R.id.btnAutomatico);
            btnProgra=itemView.findViewById(R.id.btnProgramable);

        }


    }
}
