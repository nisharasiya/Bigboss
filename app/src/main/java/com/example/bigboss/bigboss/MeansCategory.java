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
import android.widget.Toast;

import com.example.bigboss.bigboss.ShopTillPOJO.TillBean;
import com.example.bigboss.bigboss.TillSubCategory2.Datum;
import com.example.bigboss.bigboss.TillSubCategory2.TillSubCatBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MeansCategory extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView grid;

    GridLayoutManager manager;

    MAdapter adapter;

    List<Datum> list;

    ProgressBar bar;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_means_category);

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

        list = new ArrayList<>();

        adapter = new MAdapter(this, list);

        manager = new GridLayoutManager(getApplicationContext(), 3);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);

        bar = findViewById(R.id.progress);

        id = getIntent().getStringExtra("id");

        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

        Call<TillSubCatBean> call = cr.tillcat2(id);

        call.enqueue(new Callback<TillSubCatBean>() {
            @Override
            public void onResponse(Call<TillSubCatBean> call, Response<TillSubCatBean> response) {

                if (Objects.equals(response.body().getStatus(), "1")) {

                    adapter.setgrid(response.body().getData());

                } else {

                    Toast.makeText(MeansCategory.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                bar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<TillSubCatBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });


    }


    public class MAdapter extends RecyclerView.Adapter<MAdapter.MyViewHolder> {

        Context context;

        List<Datum> list = new ArrayList<>();


        public MAdapter(Context context, List<Datum> list) {

            this.context = context;
            this.list = list;


        }


        @NonNull
        @Override
        public MAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.category_list_model, viewGroup, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MAdapter.MyViewHolder myViewHolder, int i) {


            Datum item = list.get(i);

            myViewHolder.name.setText(item.getSubcatName());


            /*DisplayImageOptions options = new DisplayImageOptions.Builder().
                    cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage("" ,myViewHolder. imageView , options);*/

        }

        public void setgrid(List<Datum> list) {

            this.list = list;
            notifyDataSetChanged();

        }

        @Override
        public int getItemCount() {
            return list.size();
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
