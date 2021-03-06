package com.sc.bigboss.bigboss;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;

public class ShopCollershirt extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView grid;

    private GridLayoutManager manager;

    private CollerAdapter1 adapeter1;

    // List<String>list;


    private ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_collershirt);



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.arrowleft);
        toolbar.setNavigationOnClickListener(v -> finish());


        //  list = new ArrayList<>();

        adapeter1 = new CollerAdapter1(this );

        grid = findViewById(R.id.grid);

        manager = new GridLayoutManager(this , 1);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapeter1);

        bar = findViewById(R.id.progress);

    }




    public class CollerAdapter1 extends RecyclerView.Adapter<CollerAdapter1.MyViewHolder>{

        final Context context;

        // List<String>list = new ArrayList<>();

        CollerAdapter1(Context context){

            this.context = context;
            //this.list = list;
        }


        @NonNull
        @Override
        public CollerAdapter1 .MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.coller_list_model1 , viewGroup , false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CollerAdapter1.MyViewHolder myViewHolder, int i) {



          /*  String item = list.get(i);
            myViewHolder.name.setText("");
            myViewHolder.brand.setText("");
            myViewHolder.size.setText("");
            myViewHolder.prices.setText("");
            myViewHolder.color.setText("");
            myViewHolder.negtiable.setText("");


            DisplayImageOptions options = new DisplayImageOptions.Builder().
                    cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage("" ,myViewHolder. image , options);*/

        }

     /*  public void setgrid(List<String>list){

            this.list = list;
            notifyDataSetChanged();
        }*/

        @Override
        public int getItemCount() {
            return 15;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            final TextView name;
            final TextView brand;
            final TextView size;
            final TextView prices;
            final TextView color;
            final TextView negtiable;

            final ImageView image;


            MyViewHolder(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
                brand = itemView.findViewById(R.id.brand);
                size = itemView.findViewById(R.id.size);
                prices = itemView.findViewById(R.id.price);
                color = itemView.findViewById(R.id.color);
                negtiable = itemView.findViewById(R.id.nagtiable);
                image = itemView.findViewById(R.id.image);

                itemView.setOnClickListener(v -> {

                    Intent i = new Intent(context ,SingleProduct.class );
                    context.startActivity(i);
                });
            }
        }
    }
}
