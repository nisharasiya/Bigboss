package com.sc.bigboss.bigboss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jsibbold.zoomage.ZoomageView;

public class page4 extends Fragment {


    private ZoomageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page4 , container , false);

        imageView = view.findViewById(R.id.watch);

        return view;
    }
}
