package com.gideondev.androidcomponenttut.GithubProject.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.gideondev.androidcomponenttut.GithubProject.adapter.UserClickCallback;
import com.gideondev.androidcomponenttut.GithubProject.adapter.UserListAdapter;
import com.gideondev.androidcomponenttut.GithubProject.viewModel.CreateUserViewModel;
import com.gideondev.androidcomponenttut.Model.User;
import com.gideondev.androidcomponenttut.R;
import com.gideondev.androidcomponenttut.databinding.ActivityCreateUserBinding;
import java.util.ArrayList;
import java.util.List;

public class CreateUserActivity
    extends AppCompatActivity {
    private ActivityCreateUserBinding binding;
    private CreateUserViewModel viewModel;
    private UserListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create_user);
        mAdapter = new UserListAdapter(getApplicationContext(),new ArrayList<User>(),projectClickCallback);
        binding.recUser.setAdapter(mAdapter);
        binding.setActivity(this);
        initModel();
    }

    public void AddNewUser(){
        if (binding.edtUsername.getText()== null)
            Toast.makeText(this, "Missing fields", Toast.LENGTH_SHORT).show();
        else {
            viewModel.AddNewUser(new User(
                binding.edtUsername.getText().toString()
            ));
            binding.edtUsername.setText("");
        }
    }

    private void initModel(){
        viewModel = ViewModelProviders.of(this).get(CreateUserViewModel.class);
        observeViewModel(viewModel);
    }

    public UserClickCallback projectClickCallback = new UserClickCallback() {
        @Override
        public void OnClick(User model) {
            Intent intent = new Intent(getApplicationContext(), GithubActivity.class);
            intent.putExtra("data", model.userName);
            startActivity(intent);
        }

    };

    private void observeViewModel(CreateUserViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (users != null) {
                    mAdapter.addItems(users);
                }
            }
        });
    }
}
