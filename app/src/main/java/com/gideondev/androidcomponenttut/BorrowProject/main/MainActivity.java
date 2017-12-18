package com.gideondev.androidcomponenttut.BorrowProject.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.gideondev.androidcomponenttut.BorrowProject.AddItem.AddActivity;
import com.gideondev.androidcomponenttut.CustomSetter.CustomSetterActivity;
import com.gideondev.androidcomponenttut.GithubProject.UI.CreateUserActivity;
import com.gideondev.androidcomponenttut.Model.BorrowModel;
import com.gideondev.androidcomponenttut.R;
import com.gideondev.androidcomponenttut.databinding.ActivityMainBinding;
import com.gideondev.androidcomponenttut.viewModel.BorrowedListViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity
    extends AppCompatActivity
    implements View.OnLongClickListener {

    private RecyclerView recyclerView;
    private BorrowedListViewModel viewModel;
    private BorrowRecAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainActivity(this);
        initView();
        initModel();
    }

    @Override
    public boolean onLongClick(View view) {
        BorrowModel borrowModel = (BorrowModel) view.getTag();
        viewModel.deleteItem(borrowModel);
        return true;
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    public void OpenAddItemScreen() {
        startActivity(new Intent(MainActivity.this, AddActivity.class));
    }

    public void OpenGithub() {
        startActivity(new Intent(MainActivity.this, CreateUserActivity.class));
    }

    public void OpenCustom() {
        startActivity(new Intent(MainActivity.this, CustomSetterActivity.class));
    }

    private void initModel() {

        recyclerViewAdapter = new BorrowRecAdapter(getApplicationContext(),
                                                   new ArrayList<BorrowModel>(),
                                                   new BorrowRecAdapterListner() {
                                                       @Override
                                                       public void OnDelete(BorrowModel model) {
                                                           viewModel.deleteItem(model);
                                                       }
                                                   });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(BorrowedListViewModel.class);
        viewModel.getItemAndPersonList().observe(MainActivity.this, new Observer<List<BorrowModel>>() {
            @Override
            public void onChanged(@Nullable List<BorrowModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });

    }




}
