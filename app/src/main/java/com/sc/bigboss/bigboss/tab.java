package com.sc.bigboss.bigboss;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class tab extends AppCompatActivity {

    private RecyclerView grid;
    private LinearLayoutManager manager;

    private TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        adapter = new TabAdapter(this);

        grid = findViewById(R.id.grid);
        manager = new LinearLayoutManager(this  , LinearLayoutManager.HORIZONTAL, false);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

    }

    public class TabAdapter extends RecyclerView.Adapter<TabAdapter.Myviewholder>{


        final Context context;

        TabAdapter(Context context){
            this.context = context;
        }

        @NonNull
        @Override
        public TabAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


            View view = LayoutInflater.from(context).inflate(R.layout.tab_list_model , viewGroup , false);

            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TabAdapter.Myviewholder myviewholder, int i) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class Myviewholder extends RecyclerView.ViewHolder{

            Myviewholder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
