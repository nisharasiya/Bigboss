package com.sc.bigboss.bigboss;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sc.bigboss.bigboss.getPerksPOJO.getPerksBean;
import com.sc.bigboss.bigboss.prodList2POJO.Datum;
import com.sc.bigboss.bigboss.prodList2POJO.prodList2Bean;
import com.sc.bigboss.bigboss.scratchCardPOJO.scratchCardBean;
import com.tarek360.instacapture.Instacapture;
import com.tarek360.instacapture.listener.SimpleScreenCapturingListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProductList3 extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView grid;

    private GridLayoutManager manager;

    private MAdapter adapter;

    private List<Datum> list;

    private ProgressBar bar;

    private String id;

    private TextView title;

    private ImageView search;
    private ImageView home;

    private ConnectionDetector cd;

    private String catName;
    private String base;
    private String client;

    private LinearLayout linear;

    private Uri uri;
    private File file;

    private String ph;
    private String co;

    private TextView perks;

    private String p;

    private String pho = "";
    private String tex = "";

    private String phone;

    private ImageView notification;
    private ImageView perks2;

    private Button upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list3);

        cd = new ConnectionDetector(ProductList3.this);

        toolbar = findViewById(R.id.toolbar);
        linear = findViewById(R.id.linear);
        perks = findViewById(R.id.perks);
        upload = findViewById(R.id.upload);

        notification = findViewById(R.id.notification);
        perks2 = findViewById(R.id.perks2);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.arrowleft);
        toolbar.setNavigationOnClickListener(v -> finish());

        title = findViewById(R.id.title);

        title.setText(getIntent().getStringExtra("text"));
        catName = getIntent().getStringExtra("catname");
        phone = getIntent().getStringExtra("phone");
        client = getIntent().getStringExtra("client");



        notification.setOnClickListener(v -> {


            Intent i = new Intent(ProductList3.this, Notification.class);
            startActivity(i);
        });

        perks2.setOnClickListener(view -> {

            Intent intent = new Intent(ProductList3.this , Perks.class);
            startActivity(intent);
        });



        Log.d("catname" , catName);

        grid = findViewById(R.id.grid);

        list = new ArrayList<>();



        adapter = new MAdapter(this, list);

        manager = new GridLayoutManager(getApplicationContext(), 1);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);

        bar = findViewById(R.id.progress);

        search = findViewById(R.id.search);
        home = findViewById(R.id.home);

        search.setOnClickListener(v -> {


            Intent i = new Intent(ProductList3.this, Search.class);
            startActivity(i);
        });

        home.setOnClickListener(v -> {
            Intent i = new Intent(ProductList3.this, MainActivity.class);
            startActivity(i);
            finishAffinity();
        });

        id = getIntent().getStringExtra("id");

        if (cd.isConnectingToInternet()) {

            bar.setVisibility(View.VISIBLE);

            Bean b = (Bean) getApplicationContext();

            base = b.baseurl;

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseurl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

            Call<prodList2Bean> call = cr.getProd2(id , SharePreferenceUtils.getInstance().getString("location"));

            call.enqueue(new Callback<prodList2Bean>() {
                @Override
                public void onResponse(Call<prodList2Bean> call, Response<prodList2Bean> response) {

                    if (Objects.equals(Objects.requireNonNull(response.body()).getStatus(), "1")) {

                        adapter.setgrid(response.body().getData());
                        linear.setVisibility(View.GONE);
                    }
                    else
                    {
                        linear.setVisibility(View.VISIBLE);
                    }

                    bar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<prodList2Bean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });


        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        upload.setOnClickListener(v -> {

            pho = phone;
            tex = "";

            final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Folder/";
            File newdir = new File(dir);
            try {
                newdir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }


            String fil = dir + DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString() + ".jpg";


            file = new File(fil);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            uri = FileProvider.getUriForFile(ProductList3.this, BuildConfig.APPLICATION_ID + ".provider", file);

            Intent getpic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            getpic.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            getpic.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(getpic, 1);

        });

    }
    public class MAdapter extends RecyclerView.Adapter<MAdapter.MyViewHolder> {

        final Context context;

        List<Datum> list;


        MAdapter(Context context, List<Datum> list) {

            this.context = context;
            this.list = list;


        }


        @NonNull
        @Override
        public MAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.prod_list_model3, viewGroup, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MAdapter.MyViewHolder myViewHolder, int i) {


            final Datum item = list.get(i);

            //  myViewHolder.name.setText(item.getSubcatName());

            myViewHolder.textView.setText(Html.fromHtml(item.getSubTitle()));

            /*DisplayImageOptions options = new DisplayImageOptions.Builder().
                    cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage(base + "southman/admin2/upload/products/" + item.getProductImage(), myViewHolder.imageView, options);
*/
            Glide.with(context).load(base + "southman/admin2/upload/products/" + item.getProductImage()).into(myViewHolder.imageView);


            myViewHolder.sku.setText(item.getSku());


            myViewHolder.play.setOnClickListener(v -> {


                float pp = Float.parseFloat(p);
                float total = Float.parseFloat(item.getPrice());


                if (pp > 0)
                {
                    float ppp = 0;

                    String m = "";
                    if (total <= pp)
                    {
                        ppp = total;
                        m = "Congratulations! Dear User, It's free for you. It can be claimed after uploading the bill. Thank you!";
                    }
                    else
                    {
                        ppp = pp;

                        float mmmm = total - pp;

                        m = "Dear User, Please pay Rs. " + mmmm + " and bill is redeemed on uploading the bill. Thank you!";
                    }


                    Dialog dialog1 = new Dialog(context);
                    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog1.setCancelable(false);
                    dialog1.setContentView(R.layout.share_dialog3);
                    dialog1.show();


                    TextView mess = dialog1.findViewById(R.id.mess);

                    mess.setText(m);

                    TextView device = dialog1.findViewById(R.id.device);
                    TextView code = dialog1.findViewById(R.id.code);

                    TextView nnn = dialog1.findViewById(R.id.name);

                    nnn.setText("Attention " + SharePreferenceUtils.getInstance().getString("name"));


                    TextView cancel = dialog1.findViewById(R.id.cancel);
                    TextView proceed = dialog1.findViewById(R.id.proceed);
                    ProgressBar bar = dialog1.findViewById(R.id.progress);

                    String android_id = Settings.Secure.getString(getContentResolver(),
                            Settings.Secure.ANDROID_ID);


                    device.setText("Device - " + android_id);
                    code.setText("Code - " + item.getSku() + " | Max Cash Rewards - " + ppp);

                    cancel.setOnClickListener(v12 -> dialog1.dismiss());


                    float finalPpp = ppp;
                    proceed.setOnClickListener(v1 -> Instacapture.INSTANCE.capture(ProductList3.this, new SimpleScreenCapturingListener() {
                        @Override
                        public void onCaptureComplete(Bitmap bitmap) {
                            //Your code here..

                                    /*bar.setVisibility(View.VISIBLE);

                                    Bean b = (Bean) getApplicationContext();

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(b.baseurl)
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


                                    Call<scratchCardBean> call2 = cr.buyCash(SharePreferenceUtils.getInstance().getString("userid") , client , String.valueOf(finalPpp) , "cash" , item.getSku() , item.getPrice());

                                    call2.enqueue(new Callback<scratchCardBean>() {
                                        @Override
                                        public void onResponse(Call<scratchCardBean> call, Response<scratchCardBean> response) {

                                            if (response.body().getStatus().equals("1"))
                                            {

                                                Toast.makeText(ProductList3.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                loadPerks();



                                                *//*Intent sendIntent = new Intent("android.intent.action.MAIN");
                                                //File f=new File("path to the file");
                                                //Uri uri = Uri.fromFile(file);
                                                //sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.ContactPicker"));
                                                sendIntent.setType("image");
                                                sendIntent.setAction(Intent.ACTION_SEND);
                                                sendIntent.setPackage("com.whatsapp");
                                                sendIntent.putExtra(Intent.EXTRA_STREAM,getImageUri(context , bitmap));
                                                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(item.getPhoneNumber())+"@s.whatsapp.net");
                                                //sendIntent.putExtra(Intent.EXTRA_TEXT,"Product Code - " + tex);
                                                startActivity(sendIntent);
*//*
                             *//*

                                                Intent sendIntent = new Intent("android.intent.action.SEND");
                                                //File f=new File("path to the file");
                                                //Uri uri = Uri.fromFile(file);
                                                sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.ContactPicker"));
                                                sendIntent.setType("image");
                                                sendIntent.putExtra(Intent.EXTRA_STREAM , getImageUri(context , bitmap));
                                                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(item.getPhoneNumber())+"@s.whatsapp.net");
                                                sendIntent.putExtra(Intent.EXTRA_TEXT,"");
                                                startActivity(sendIntent);

*//*


                                                dialog1.dismiss();

                                            }

                                            bar.setVisibility(View.GONE);

                                        }

                                        @Override
                                        public void onFailure(Call<scratchCardBean> call, Throwable t) {
                                            bar.setVisibility(View.GONE);
                                        }
                                    });*/


                        }
                    }));

                }
                else
                {
                    Toast.makeText(context, "Sorry, you don't have enough cash rewards to redeem this", Toast.LENGTH_SHORT).show();
                }





            });


            ph = item.getPhoneNumber();
            co = item.getSku();

            myViewHolder.upload.setOnClickListener(v -> {


                pho = item.getPhoneNumber();
                tex = item.getSku();

                final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Folder/";
                File newdir = new File(dir);
                try {
                    newdir.mkdirs();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                String fil = dir + DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString() + ".jpg";


                file = new File(fil);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                uri = FileProvider.getUriForFile(ProductList3.this, BuildConfig.APPLICATION_ID + ".provider", file);

                Intent getpic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getpic.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                getpic.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(getpic, 1);



            });




        }

        void setgrid(List<Datum> list) {

            this.list = list;
            notifyDataSetChanged();

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            final ImageView imageView;

            final TextView textView;
            final TextView sku;

            final Button play;
            final Button upload;

            // TextView name;

            MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text);
                sku = itemView.findViewById(R.id.sku);
                upload = itemView.findViewById(R.id.upload);
                imageView = itemView.findViewById(R.id.image);
                play = itemView.findViewById(R.id.play);

                //name = itemView.findViewById(R.id.name);


            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                PackageInfo info = getPackageManager().getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            File file2 = null;

            try {
                file2 = new Compressor(ProductList3.this).compressToFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }


            MultipartBody.Part body = null;

            try {

                RequestBody reqFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(file2));
                body = MultipartBody.Part.createFormData("bill", file2.getName(), reqFile1);


            }catch (Exception e1)
            {
                e1.printStackTrace();
            }


            Bean b = (Bean) getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseurl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


            bar.setVisibility(View.VISIBLE);

            Call<scratchCardBean> call = cr.uploadBill(client , SharePreferenceUtils.getInstance().getString("userid") , body);

            call.enqueue(new Callback<scratchCardBean>() {
                @Override
                public void onResponse(Call<scratchCardBean> call, Response<scratchCardBean> response) {

                    if (Objects.requireNonNull(response.body()).getStatus().equals("1"))
                    {
                        Toast.makeText(ProductList3.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    bar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<scratchCardBean> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });

            /*Intent sendIntent = new Intent("android.intent.action.MAIN");
            //File f=new File("path to the file");
            //Uri uri = Uri.fromFile(file);
            //sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.ContactPicker"));
            sendIntent.setType("image");
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setPackage("com.whatsapp");
            sendIntent.putExtra(Intent.EXTRA_STREAM,uri);
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(phone)+"@s.whatsapp.net");
            //sendIntent.putExtra(Intent.EXTRA_TEXT,"Product Code - " + tex);
            startActivity(sendIntent);
*/

/*
            Intent sendIntent = new Intent("android.intent.action.SEND");
            //File f=new File("path to the file");
            //Uri uri = Uri.fromFile(file);
            sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.ContactPicker"));
            sendIntent.setType("image");
            sendIntent.putExtra(Intent.EXTRA_STREAM,uri);
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(pho)+"@s.whatsapp.net");
            //sendIntent.putExtra(Intent.EXTRA_TEXT,"Product Code - " + tex);
            startActivity(sendIntent);
*/


            /*Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + "" + ph + "?body=" + "Product Code : " + co));
            intent.setPackage("com.whatsapp");
            //intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=91" + ph + "&text=Product Code : " + co));
            intent.setType("image/jpeg");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(intent);
*/
            /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=91" + ph + "&text=Product Code : " + co));
            startActivity(browserIntent);
*/
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, String.valueOf(System.currentTimeMillis()), null);
        return Uri.parse(path);
    }

    private void loadPerks()
    {

        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


        String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Log.d("asdsad", android_id);

        Call<getPerksBean> call = cr.getPerks(android_id);


        call.enqueue(new Callback<getPerksBean>() {
            @Override
            public void onResponse(Call<getPerksBean> call, Response<getPerksBean> response) {

                if (Objects.requireNonNull(response.body()).getStatus().equals("1")) {
                    perks.setText("Cash reward remaining : " + response.body().getData().get(0).getCashRewards());
                    p = response.body().getData().get(0).getCashRewards();

                }
                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<getPerksBean> call, Throwable t) {
                bar.setVisibility(View.GONE);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        loadPerks();

    }

}
