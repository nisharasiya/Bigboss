package com.sc.bigboss.bigboss;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Womenwear extends Fragment {

    private LinearLayout top;
    private LinearLayout bottom;

    private ImageView shirt;
    private ImageView pant;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.womenwear, container, false);


        top = view.findViewById(R.id.top);
        bottom = view.findViewById(R.id.bottom);


        top.setOnClickListener(v -> {


            Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.topdialog);
            dialog.setCancelable(true);
            dialog.show();


            RecyclerView grid = dialog.findViewById(R.id.grid);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
            WomanAdapter adapter = new WomanAdapter(getContext());
            grid.setLayoutManager(manager);
            grid.setAdapter(adapter);

        });


        bottom.setOnClickListener(v -> {


            Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.bottomdialog);
            dialog.setCancelable(true);
            dialog.show();


            RecyclerView grid = dialog.findViewById(R.id.grid);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
            WomanAdapter1 adapter = new WomanAdapter1(getContext());
            grid.setLayoutManager(manager);
            grid.setAdapter(adapter);

        });

        shirt = view.findViewById(R.id.shirt);
        pant = view.findViewById(R.id.pant);
        shirt.setOnClickListener(v -> {


            Intent i = new Intent(getContext(), SingleProduct.class);
            startActivity(i);

        });


        pant.setOnClickListener(v -> {


            Intent i = new Intent(getContext(), SingleProduct.class);
            startActivity(i);

        });

        return view;
    }


    public class WomanAdapter extends RecyclerView.Adapter<WomanAdapter.Myviewholder> {


        final Context context;


        WomanAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public WomanAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.means_list_model, viewGroup, false);
            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WomanAdapter.Myviewholder myviewholder, int i) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class Myviewholder extends RecyclerView.ViewHolder {

            Myviewholder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }


    public class WomanAdapter1 extends RecyclerView.Adapter<WomanAdapter1.Myviewholder> {


        final Context context;


        WomanAdapter1(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public WomanAdapter1.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.bottom_list_model, viewGroup, false);
            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WomanAdapter1.Myviewholder myviewholder, int i) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class Myviewholder extends RecyclerView.ViewHolder {

            Myviewholder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }


}
