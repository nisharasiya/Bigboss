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

public class ShopMens extends AppCompatActivity {


    private Toolbar toolbar;

    private RecyclerView grid;

    private GridLayoutManager manager;

    private MAdapter1 adapter1;

    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_mens);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.arrowleft);
        toolbar.setNavigationOnClickListener(v -> finish());


        grid = findViewById(R.id.grid);

      //  list = new ArrayList<>();

        adapter1 = new MAdapter1(this);

        manager = new GridLayoutManager(getApplicationContext(), 3);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter1);

        bar = findViewById(R.id.progress);




    }






    public class MAdapter1 extends RecyclerView.Adapter<MAdapter1.MyViewHolder> {

        final Context context;

      //  List<Datum> list = new ArrayList<>();


        MAdapter1(Context context) {

            this.context = context;
           // this.list = list;


        }


        @NonNull
        @Override
        public MAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.shopmens_list_model, viewGroup, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MAdapter1.MyViewHolder myViewHolder, int i) {


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

            final ImageView imageView;

            final TextView name;

            MyViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.tshirt);

                name = itemView.findViewById(R.id.name);

                itemView.setOnClickListener(v -> {

                    Intent i = new Intent(context, CollerTshirt.class);
                    context.startActivity(i);


                });
            }
        }
    }
}
