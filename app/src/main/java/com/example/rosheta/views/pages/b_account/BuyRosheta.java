package com.example.rosheta.views.pages.b_account;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.Medicines;
import com.example.rosheta.data.models.remote.Pharmacy;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.adapters.PharmacyAdapter;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.c_home.ExaminationDetails;
import com.google.zxing.WriterException;

import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;

public class BuyRosheta extends AppCompatActivity {
    private ImageView qrCodeIV;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_rosheta);

        qrCodeIV = findViewById(R.id.idIVQrcode);

        // below line is for getting
        // the windowmanager service.
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        String mediciens = "Found " + ExaminationDetails.medicines.size() + " itemes\n";
        int idx = 1;
        for (Medicines cur : ExaminationDetails.medicines) {
            mediciens += (idx + " : " + cur.getName() + "\n");
            idx++;
        }
        qrgEncoder = new QRGEncoder(mediciens, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            qrCodeIV.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }



        //Pharmacies
        RecyclerView recyclerView2 = findViewById(R.id.rv_buy);
        StaggeredGridLayoutManager layoutManager2
                = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager2);
        ArrayList<Pharmacy> pharmacies = new ArrayList<>();
        EndPoints Api = RetrofitCreation.getInstance();
        Call<ArrayList<Pharmacy>> call2 = Api.getPharmacy("pharmacy", "");
        call2.enqueue(new Callback<ArrayList<Pharmacy>>() {
            @Override
            public void onResponse(Call<ArrayList<Pharmacy>> call, retrofit2.Response<ArrayList<Pharmacy>> response) {
                for (int i = 0; i < response.body().size(); i++)
                    pharmacies.add(response.body().get(i));
                PharmacyAdapter pharmacyAdapter = new PharmacyAdapter(BuyRosheta.this, pharmacies);
                recyclerView2.setAdapter(pharmacyAdapter);
                recyclerView2.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<ArrayList<Pharmacy>> call, Throwable t) {

            }
        });


        EditText et_buy_search=findViewById(R.id.et_buy_search);
        et_buy_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<Pharmacy> pharmacies = new ArrayList<>();
                EndPoints Api = RetrofitCreation.getInstance();
                Call<ArrayList<Pharmacy>> call2 = Api.getPharmacy("pharmacy", charSequence.toString());
                call2.enqueue(new Callback<ArrayList<Pharmacy>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Pharmacy>> call, retrofit2.Response<ArrayList<Pharmacy>> response) {
                        for (int i = 0; i < response.body().size(); i++)
                            pharmacies.add(response.body().get(i));
                        PharmacyAdapter pharmacyAdapter = new PharmacyAdapter(BuyRosheta.this, pharmacies);
                        pharmacyAdapter.notifyDataSetChanged();
                        recyclerView2.setAdapter(pharmacyAdapter);
                        recyclerView2.setItemAnimator(new DefaultItemAnimator());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Pharmacy>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}