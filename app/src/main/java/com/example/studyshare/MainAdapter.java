package com.example.studyshare;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

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
    protected void onBindViewHolder(@NonNull viewModel holder, int position, @NonNull MainModel model) {
        holder.nombre.setText(model.getNombre());
        holder.nombre.setTextColor(Color.BLACK);
        holder.descripcion.setText(model.getDescripcion());
        holder.descripcion.setTextColor(Color.BLACK);
        holder.tipo.setText(model.getTipo());
        holder.tipo.setTextColor(Color.BLACK);
        Glide.with(holder.imagen.getContext()).load(model.getImagen())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop().error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imagen);
    }

    @NonNull
    @Override
    public viewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items,parent,false);

        return new viewModel(view);
    }

    class viewModel extends RecyclerView.ViewHolder{
        CircleImageView imagen;
        TextView nombre, descripcion,tipo;

        public viewModel(@NonNull View itemView) {
            super(itemView);
            descripcion = (TextView)itemView.findViewById(R.id.descripcion);
            imagen = (CircleImageView)itemView.findViewById(R.id.a1);
            nombre = (TextView)itemView.findViewById(R.id.nombre);
            tipo = (TextView)itemView.findViewById(R.id.tipo);
        }
    }
}
