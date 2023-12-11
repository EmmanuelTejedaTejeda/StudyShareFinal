package com.example.studyshare;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link I_Turismo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class I_Turismo extends Fragment {
    RecyclerView turismo;
    MainAdapter mainAdapter;
    ImageView regresar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public I_Turismo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment I_Turismo.
     */
    // TODO: Rename and change types and number of parameters
    public static I_Turismo newInstance(String param1, String param2) {
        I_Turismo fragment = new I_Turismo();
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
        // Inflate the layout for this fragment
        View  view =  inflater.inflate(R.layout.fragment_i__turismo, container, false);

        // Configurar animación de entrada
        Transition enterTransition = TransitionInflater.from(requireContext())
                .inflateTransition(android.R.transition.slide_bottom);
        setEnterTransition(enterTransition);

        // Configurar animación de salida
        Transition exitTransition = TransitionInflater.from(requireContext())
                .inflateTransition(android.R.transition.slide_top);
        setExitTransition(exitTransition);

        //Boton para regresar
        regresar = view.findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaledow = ObjectAnimator.ofPropertyValuesHolder(
                        regresar,
                        PropertyValuesHolder.ofFloat("scaleX", 0.87f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.87f)
                );
                scaledow.setDuration(150);
                scaledow.setInterpolator(new AccelerateInterpolator());
                scaledow.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Navigation.findNavController(view).navigate(R.id.action_i_Turismo_to_inicio);
                    }
                });
                scaledow.start();
            }
        });

        turismo = view.findViewById(R.id.Rturismo);
        turismo.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("archivos"), MainModel.class)
                        .build();
        mainAdapter = new MainAdapter(options);
        turismo.setAdapter(mainAdapter);
        return view;
    }

    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.startListening();
    }
}