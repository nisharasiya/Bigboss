package com.example.bigboss.bigboss;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class OneMenswear extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView grid;

    GridLayoutManager manager;

    Onemens adapter1;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_menswear);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.arrowleft);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        grid = findViewById(R.id.grid);

        //  list = new ArrayList<>();

        adapter1 = new Onemens(this);

        manager = new GridLayoutManager(getApplicationContext(), 3);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter1);

        bar = findViewById(R.id.progress);


    }

    public class Onemens extends RecyclerView.Adapter<Onemens.MyViewHolder> {

        Context context;

        //  List<Datum> list = new ArrayList<>();


        public Onemens(Context context) {

            this.context = context;
            // this.list = list;


        }


        @NonNull
        @Override
        public Onemens.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.onemens_list_model, viewGroup, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Onemens.MyViewHolder myViewHolder, int i) {


          /*  Datum item = list.get(i);

            myViewHolder.name.setText(item.getSubcatName());
*/

            /*DisplayImageOptions options = new DisplayImageOptions.Builder().
                    cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage("" ,myViewHolder. imageView , options);*/

        }

      /*  public void setgrid(List<Datum> list) {

            this.list = list;
            notifyDataSetChanged();

        }*/

        @Override
        public int getItemCount() {
            return 10;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            TextView name;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.tshirt);

                name = itemView.findViewById(R.id.name);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(context, CollerTshirt.class);
                        context.startActivity(i);


                    }
                });
            }
        }
    }
}
