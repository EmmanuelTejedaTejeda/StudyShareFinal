package com.example.studyshare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.viewModel> {
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
    protected void onBindViewHolder(@NonNull viewModel holder,int position, @NonNull MainModel model) {
        holder.nombre.setText(model.getNombre());
        holder.nombre.setTextColor(Color.BLACK);
        holder.descripcion.setText(model.getDescripcion());
        holder.descripcion.setTextColor(Color.BLACK);

        Glide.with(holder.imagen.getContext()).load(model.getImagen())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop().error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imagen);

        holder.editarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(holder.imagen.getContext()).setContentHolder(new ViewHolder(R.layout.editararchivos)).setExpanded(true, 1200).create();

                //dialogPlus.show();
                View view = dialogPlus.getHolderView();
                EditText name = view.findViewById(R.id.editarNombre);
                EditText description = view.findViewById(R.id.editarDescripcion);

                Button actualizar = view.findViewById(R.id.Acualizar);

                name.setText(model.getNombre());
                description.setText(model.getDescripcion());

                dialogPlus.show();

                actualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("nombre",  name.getText().toString());
                        map.put("descripcion", description.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("misarchivos")
                                .child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nombre.getContext(),"archivo actualizado", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.nombre.getContext(),"error por favor vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

            }
        });

        holder.eliminarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nombre.getContext());
                builder.setTitle("¿Estas  seguro  de  elimnar este registro?");
                builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("misarchivos").child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.nombre.getContext(),"Cancelado" ,Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });

        holder.descripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.descargarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargarArchivo(model.getArchivoId(), holder.nombre.getContext());
            }
        });

    }



    @NonNull
    @Override
    public viewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items,parent,false);
        return new viewModel(view);
    }

    private void descargarArchivo(String archivoId, Context context) {
        StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("archivosPDF/" + archivoId + ".pdf");
        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Toast.makeText(context, "URL del archivo: " + uri.toString(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Error al obtener la URL del archivo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class viewModel extends RecyclerView.ViewHolder{
        CircleImageView imagen;
        TextView nombre, descripcion;
        Button eliminarRegistro, editarRegistro, agregarRegistro,descargarArchivo;

        public viewModel(@NonNull View itemView) {
            super(itemView);
            descripcion = (TextView)itemView.findViewById(R.id.descripcion);
            imagen = (CircleImageView)itemView.findViewById(R.id.a1);
            nombre = (TextView)itemView.findViewById(R.id.nombre);
            eliminarRegistro = (Button)itemView.findViewById(R.id.eliminarRegistro);
            editarRegistro = (Button)itemView.findViewById(R.id.editarRegistro);
            agregarRegistro = (Button)itemView.findViewById(R.id.subirArchivos);
            descargarArchivo=  (Button)itemView.findViewById(R.id.descargar);
        }
    }
}