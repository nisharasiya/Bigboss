package com.sc.bigboss.bigboss;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Entertainment extends Fragment {

    private RecyclerView grid;

    private GridLayoutManager manager;

    private EntainmentAdapeter adapter;

    List<String>list;

    private ProgressBar bar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.entertainment , container , false);

       // list = new ArrayList<>();

        adapter = new EntainmentAdapeter(getContext());

        grid = view.findViewById(R.id.grid);

        manager = new GridLayoutManager(getContext() , 1);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);


        bar = view.findViewById(R.id.progress);


        return view;
    }

    public class EntainmentAdapeter extends RecyclerView.Adapter<EntainmentAdapeter.MyViewHolder>{

        final Context context;

        //List<String>list;

        EntainmentAdapeter(Context context){

            this.context = context;
            //this.list = list;
        }


        @NonNull
        @Override
        public EntainmentAdapeter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.enter_list_model , viewGroup , false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull EntainmentAdapeter.MyViewHolder myViewHolder, int i) {


           /* String item = list.get(i);
            myViewHolder.text.setText("");


            DisplayImageOptions options = new DisplayImageOptions.Builder().
                    cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage("" ,myViewHolder. image , options);

*/

        }

       /* public void setgrid(List<String>list){

            this.list = list;
            notifyDataSetChanged();
        }

*/
        @Override
        public int getItemCount() {
            return 18;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            final TextView text;

            final ImageView image;

            final Button play;

            MyViewHolder(@NonNull View itemView) {
                super(itemView);


                play = itemView.findViewById(R.id.play);

                image = itemView.findViewById(R.id.image);

                text = itemView.findViewById(R.id.text);


                itemView.setOnClickListener(v -> {


                    Intent i = new Intent(context , Videoplayer.class);
                    context.startActivity(i);
                });
            }
        }
    }
}
