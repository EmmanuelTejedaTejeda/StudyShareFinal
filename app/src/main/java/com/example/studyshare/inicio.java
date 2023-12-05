package com.example.studyshare;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.lang.reflect.Array;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link inicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class inicio extends Fragment {
    FragmentManager fragmentManager;
    FragmentTransaction animacion;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CardView informatica;
    CardView sistemas;
    CardView mecatronica;
    CardView gestion;
    CardView industrial;
    CardView alimentarias;
    CardView diseno;
    CardView turismo;


    public inicio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment inicio.
     */
    // TODO: Rename and change types and number of parameters
    public static inicio newInstance(String param1, String param2) {
        inicio fragment = new inicio();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //mostrar la vista
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        // Configurar animación de entrada
        Transition abrirfragment = TransitionInflater.from(requireContext())
                .inflateTransition(android.R.transition.slide_right);
        setEnterTransition(abrirfragment);

        // Configurar animación de salida
        Transition salirfragment = TransitionInflater.from(requireContext())
                .inflateTransition(android.R.transition.slide_left);
        setExitTransition(salirfragment);

        //definir botones
        informatica = view.findViewById(R.id.informatica);
        sistemas = view.findViewById(R.id.sistemas);
        mecatronica = view.findViewById(R.id.mecatronica);
        gestion = view.findViewById(R.id.gestion);
        industrial = view.findViewById(R.id.industrial);
        alimentarias = view.findViewById(R.id.alimentarias);
        diseno = view.findViewById(R.id.diseno);
        turismo = view.findViewById(R.id.turismo);

        //Creacion de todas las  animaciones
        informatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaledow = ObjectAnimator.ofPropertyValuesHolder(
                        informatica,
                        PropertyValuesHolder.ofFloat("scaleX", 0.87f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.87f)
                );
                scaledow.setDuration(150);
                scaledow.setInterpolator(new AccelerateInterpolator());
                scaledow.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Navigation.findNavController(view).navigate(R.id.action_inicio_to_i_Informatica);
                    }
                });
                scaledow.start();
            }
        });
        sistemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaledow = ObjectAnimator.ofPropertyValuesHolder(
                        sistemas,
                        PropertyValuesHolder.ofFloat("scaleX", 0.87f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.87f)
                );
                scaledow.setDuration(150);
                scaledow.setInterpolator(new AccelerateInterpolator());
                scaledow.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Navigation.findNavController(view).navigate(R.id.action_inicio_to_i_Sistemas);
                    }
                });
                scaledow.start();
            }
        });

        mecatronica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaledow = ObjectAnimator.ofPropertyValuesHolder(
                        mecatronica,
                        PropertyValuesHolder.ofFloat("scaleX", 0.87f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.87f)
                );
                scaledow.setDuration(150);
                scaledow.setInterpolator(new AccelerateInterpolator());
                scaledow.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Navigation.findNavController(view).navigate(R.id.action_inicio_to_i_Mecatronica);
                    }
                });
                scaledow.start();
            }
        });
        gestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaledow = ObjectAnimator.ofPropertyValuesHolder(
                        gestion,
                        PropertyValuesHolder.ofFloat("scaleX", 0.87f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.87f)
                );
                scaledow.setDuration(150);
                scaledow.setInterpolator(new AccelerateInterpolator());
                scaledow.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Navigation.findNavController(view).navigate(R.id.action_inicio_to_i_Gestion);
                    }
                });
                scaledow.start();
            }
        });

        industrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaledow = ObjectAnimator.ofPropertyValuesHolder(
                        industrial,
                        PropertyValuesHolder.ofFloat("scaleX", 0.87f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.87f)
                );
                scaledow.setDuration(150);
                scaledow.setInterpolator(new AccelerateInterpolator());
                scaledow.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Navigation.findNavController(view).navigate(R.id.action_inicio_to_i_Industrial);
                    }
                });
                scaledow.start();
            }
        });

        alimentarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaledow = ObjectAnimator.ofPropertyValuesHolder(
                        alimentarias,
                        PropertyValuesHolder.ofFloat("scaleX", 0.87f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.87f)
                );
                scaledow.setDuration(150);
                scaledow.setInterpolator(new AccelerateInterpolator());
                scaledow.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Navigation.findNavController(view).navigate(R.id.action_inicio_to_i_Alimentarias);
                    }
                });
                scaledow.start();
            }
        });

        diseno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaledow = ObjectAnimator.ofPropertyValuesHolder(
                        diseno,
                        PropertyValuesHolder.ofFloat("scaleX", 0.87f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.87f)
                );
                scaledow.setDuration(150);
                scaledow.setInterpolator(new AccelerateInterpolator());
                scaledow.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Navigation.findNavController(view).navigate(R.id.action_inicio_to_i_Diseno);
                    }
                });
                scaledow.start();
            }
        });

        turismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaledow = ObjectAnimator.ofPropertyValuesHolder(
                        turismo,
                        PropertyValuesHolder.ofFloat("scaleX", 0.87f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.87f)
                );
                scaledow.setDuration(150);
                scaledow.setInterpolator(new AccelerateInterpolator());
                scaledow.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Navigation.findNavController(view).navigate(R.id.action_inicio_to_i_Turismo);
                    }
                });
                scaledow.start();
            }
        });
        return view;
    }
}