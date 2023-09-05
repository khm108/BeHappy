package com.hello.ourApplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class FragmentTutorial6 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutorial_6, container, false);

        ImageButton goToMain = rootView.findViewById(R.id.tutorial_to_main);

        ImageButton buttonLogin = getActivity().findViewById(R.id.tut_button_login);
        ImageButton buttonNext = getActivity().findViewById(R.id.tut_button_next);

        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}