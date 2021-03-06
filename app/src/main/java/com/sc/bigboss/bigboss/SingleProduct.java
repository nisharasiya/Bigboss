package com.sc.bigboss.bigboss;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;

import com.bumptech.glide.Glide;
import com.sc.bigboss.bigboss.PlaySliderPOJO.PlayBean;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SingleProduct extends AppCompatActivity {

    private Toolbar toolbar;

    private Button order;

    private TextView name;
    private TextView brand;
    private TextView color;
    private TextView size;
    private TextView negitable;
    private TextView price;
    private static TextView title;
    private TextView details;
    private TextView cod;

    private ViewPager imageView;

    private CircleIndicator indicator;

    private ProgressBar bar;

    private static String id;

    private ImageView search;
    private ImageView home;

    private String ph;
    private String co;

    private String catName;
    private String base;

    private LinearLayout negotitle;


    private ImageView notification;
    private ImageView perks2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        id = getIntent().getStringExtra("id");
        catName = getIntent().getStringExtra("catname");


        toolbar = findViewById(R.id.toolbar);
        negotitle = findViewById(R.id.negotitle);

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrowleft);

        notification = findViewById(R.id.notification);
        perks2 = findViewById(R.id.perks2);


        notification.setOnClickListener(v -> {


            Intent i = new Intent(SingleProduct.this, Notification.class);
            startActivity(i);
        });

        perks2.setOnClickListener(view -> {

            Intent intent = new Intent(SingleProduct.this , Perks.class);
            startActivity(intent);
        });

        toolbar.setNavigationOnClickListener(v -> finish());
        title = findViewById(R.id.title);

        indicator = findViewById(R.id.indicator);

        details = findViewById(R.id.text);

/*
        details.setVerticalScrollBarEnabled(false);
        details.setHorizontalScrollBarEnabled(false);

*/
/*
details.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
*/

        title.setText(getIntent().getStringExtra("text"));

        name = findViewById(R.id.name);

        brand = findViewById(R.id.brand);

        color = findViewById(R.id.color);

        size = findViewById(R.id.size);

        price = findViewById(R.id.price);

        negitable = findViewById(R.id.nagtiable);

        imageView = findViewById(R.id.image);

        order = findViewById(R.id.order);

        search = findViewById(R.id.search);
        home = findViewById(R.id.home);
        cod = findViewById(R.id.code);

        search.setOnClickListener(v -> {


            Intent i = new Intent(SingleProduct.this, Search.class);
            startActivity(i);
        });


        home.setOnClickListener(v -> {

            Intent i = new Intent(SingleProduct.this, MainActivity.class);
            startActivity(i);
            finishAffinity();
        });


        bar = findViewById(R.id.progress);

        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        base = b.baseurl;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

        Call<PlayBean> call = cr.play(id, SharePreferenceUtils.getInstance().getString("location"));

        call.enqueue(new Callback<PlayBean>() {
            @Override
            public void onResponse(Call<PlayBean> call, Response<PlayBean> response) {

                name.setText(Objects.requireNonNull(response.body()).getProductInfo().get(0).getProductTitle());

                brand.setText(response.body().getProductInfo().get(0).getBrand());

                color.setText(response.body().getProductInfo().get(0).getColor());

                size.setText(response.body().getProductInfo().get(0).getSize());
                cod.setText(response.body().getProductInfo().get(0).getProductCode());

/*                if (response.body().getProductInfo().get(0).getDiscountPrice() != null && !catName.equals("shop by shop"))
                {*/
                    price.setText(Html.fromHtml("\u20B9" + response.body().getProductInfo().get(0).getDiscountPrice() + "  <strike>\u20B9" + response.body().getProductInfo().get(0).getPrice() + "</strike>"));
               /* }
                else
                {
                    price.setText("\u20B9" + response.body().getProductInfo().get(0).getPrice());
                }*/

                //price.setText("\u20B9" + response.body().getProductInfo().get(0).getPrice());




                    negotitle.setVisibility(View.GONE);


                if (response.body().getProductInfo().get(0).getWhatsappOrderNow().equals("yes")) {
                    order.setVisibility(View.VISIBLE);
                } else {
                    order.setVisibility(View.GONE);
                }

                ph = String.valueOf(response.body().getProductInfo().get(0).getPhoneNumber());

                co = String.valueOf(response.body().getProductInfo().get(0).getProductCode());

                Log.d("ddddd" , response.body().getProductInfo().get(0).getProductDetail());

                String de = response.body().getProductInfo().get(0).getProductDetail();

                de = de.replace("\t" , "");
                de = de.replace("\n" , "");
                de = de.replace("<p>" , "");
                de = de.replace("</p>" , "<br>");

                StringBuilder sb = new StringBuilder(de);

                sb.delete(de.length() - 4 , de.length());

                de = sb.toString();

                Log.d("ddddd" , de);


                details.setText(Html.fromHtml(de));
                //details.setText(Html.fromHtml(response.body().getProductInfo().get(0).getProductDetail()));



                ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager(), response.body().getThumb().get(0));

                imageView.setAdapter(adapter);

                indicator.setViewPager(imageView);



                bar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<PlayBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });


        imageView.setOnClickListener(v -> {

            Intent i = new Intent(SingleProduct.this, WristWatch.class);
            i.putExtra("id", id);
            i.putExtra("text", title.getText().toString());
            startActivity(i);

        });


        order.setOnClickListener(v -> {

            final Dialog dialog = new Dialog(SingleProduct.this);

            dialog.setContentView(R.layout.dialog);

            dialog.setCancelable(true);

            dialog.show();

            TextView code = dialog.findViewById(R.id.code);

            TextView mobile = dialog.findViewById(R.id.mobile);

            Button watshp = dialog.findViewById(R.id.whatsapp);

            Button call1 = dialog.findViewById(R.id.call);

            mobile.setText(ph);

            code.setText(co);

            watshp.setOnClickListener(v12 -> {



/*

                    try {

                        Uri uri = Uri.parse("smsto:" + ph);
                        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
                        sendIntent.setPackage("com.whatsapp");
                        startActivity(sendIntent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
*/


                openWhatsApp();

                dialog.dismiss();

                // String formattedNumber = Util.formatPhone(ph);
                   /* try{
                        Intent sendIntent =new Intent("android.intent.action.MAIN");
                        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.setType("text/plain");
                        sendIntent.putExtra(Intent.EXTRA_TEXT,"dstfdsg");
                        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("917503381028") +"@s.whatsapp.net");
                        sendIntent.setPackage("com.whatsapp");
                        SingleProduct.this.startActivity(sendIntent);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(SingleProduct.this,"Error/n"+ e.toString(),Toast.LENGTH_SHORT).show();
                    }
*/



/*

                    String url = "https://api.whatsapp.com/send?phone="+ph;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
*/


            });

            call1.setOnClickListener(v1 -> {

                   /* try {


                        Intent i = new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse(ph));
                        startActivity(i);


                    } catch (Exception e) {

                        e.printStackTrace();
                    }*/


                try {

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ph));
                    startActivity(intent);
                    dialog.dismiss();


                } catch (Exception e) {

                    e.printStackTrace();
                }


            });
        });

        count = findViewById(R.id.count);

        singleReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (Objects.requireNonNull(intent.getAction()).equals("count")) {
                    count.setText(String.valueOf(SharePreferenceUtils.getInstance().getInteger("count")));
                }

            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(singleReceiver,
                new IntentFilter("count"));

    }


    private BroadcastReceiver singleReceiver;
    private TextView count;



    private void openWhatsApp() {



        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=91" + ph + "&text=Product Code : " + co));
        startActivity(browserIntent);

/*

        String smsNumber = "ph";
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(ph) + "@s.whatsapp.net");//phone number without "+" prefix
           // sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(ph) + "Product code : " + co);//phone number without "+" prefix

            startActivity(sendIntent);
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }*/
    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return app_installed;
    }

    class ViewAdapter extends FragmentStatePagerAdapter {

        List<String> tlist;

        ViewAdapter(FragmentManager fm, List<String> tlist) {
            super(fm);
            this.tlist = tlist;
        }

        @Override
        public Fragment getItem(int i) {


            Page2 frag = new Page2();
            Bundle b = new Bundle();
            b.putString("url" , base + "southman/admin2/upload/products/" + tlist.get(i));

            frag.setArguments(b);
            return frag;

        }

        @Override
        public int getCount() {
            return tlist.size();
        }
    }

    public static class Page2 extends Fragment {

        ImageView imageView;

        String url;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.page1 , container , false);

            url = Objects.requireNonNull(getArguments()).getString("url");

            imageView = view.findViewById(R.id.watch);


            Glide.with(Objects.requireNonNull(getActivity())).load(url).into(imageView);




            imageView.setOnClickListener(v -> {

                Intent i = new Intent(getContext(), WristWatch.class);
                i.putExtra("id", id);
                i.putExtra("text", title.getText().toString());
                startActivity(i);

            });

            return view;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(singleReceiver);

    }

    @Override
    protected void onResume() {
        super.onResume();
        count.setText(String.valueOf(SharePreferenceUtils.getInstance().getInteger("count")));

    }
}
