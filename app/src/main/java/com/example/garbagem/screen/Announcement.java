package com.example.garbagem.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.garbagem.Adapter.AnnoucmentAdapter;
import com.example.garbagem.R;
import com.example.garbagem.Retrofit.ApiClient;
import com.example.garbagem.Retrofit.ApiInterface;
import com.example.garbagem.model.AnnnoucmentModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Announcement extends AppCompatActivity {

    RecyclerView rvAnnouncement;
    LinearLayout llList;
    Button retry;
    LinearLayout llError;
    ProgressBar progressBar;

    private List<AnnnoucmentModel> annnoucmentModelList;
    private AnnoucmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        rvAnnouncement = findViewById(R.id.rvAnnouncement);
        retry = findViewById(R.id.retry);
        llError = findViewById(R.id.ll_Error);
        progressBar = findViewById(R.id.progressBar);
        llList = findViewById(R.id.ll_List);

        retry.setOnClickListener(view -> {
            llError.setVisibility(View.GONE);
            llList.setVisibility(View.VISIBLE);
            getAnnouncementList();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        llError.setVisibility(View.GONE);
        llList.setVisibility(View.VISIBLE);
        getAnnouncementList();
    }

    private void getAnnouncementList() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<AnnnoucmentModel>> call = apiService.getRetromodel();
        call.enqueue(new Callback<List<AnnnoucmentModel>>() {
            @Override
            public void onResponse(Call<List<AnnnoucmentModel>> call, Response<List<AnnnoucmentModel>> response) {
                if (response.body() != null && response.body().size() > 0) {
                    annnoucmentModelList = new ArrayList<>();
                    annnoucmentModelList.addAll(response.body());
                    }
                    if (annnoucmentModelList.size() > 0) {
                        setUpRecyclerView();
                    } else {
                        llList.setVisibility(View.GONE);
                        llError.setVisibility(View.VISIBLE);

                    }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<AnnnoucmentModel>> call, Throwable t) {
                Toast.makeText(Announcement.this,
                        getString(R.string.failed_to_fetch_data_from_server) + "\n" +
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                llError.setVisibility(View.VISIBLE);
                llList.setVisibility(View.GONE);

            }
        });
    }
//run app
    private void setUpRecyclerView() {
        Collections.reverse(annnoucmentModelList);
        adapter = new AnnoucmentAdapter(annnoucmentModelList, Announcement.this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvAnnouncement.setLayoutManager(manager);
        rvAnnouncement.setItemAnimator(new DefaultItemAnimator());
        rvAnnouncement.setAdapter(adapter);
    }
}
