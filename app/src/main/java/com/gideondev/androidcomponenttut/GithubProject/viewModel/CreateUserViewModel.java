package com.gideondev.androidcomponenttut.GithubProject.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.gideondev.androidcomponenttut.Database.AppDatabase;
import com.gideondev.androidcomponenttut.Model.Project;
import com.gideondev.androidcomponenttut.Model.User;
import java.util.List;

/**
 * Created by ${ENNY} on 11/30/2017.
 */

public class CreateUserViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    public CreateUserViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(application);
    }

    public LiveData<List<User>> getUserList(){
       // final MutableLiveData<List<User>> data = new MutableLiveData<>();
       return appDatabase.getUserDao().getUserlist() ;
    }

    public void AddNewUser(User user){
        appDatabase.getUserDao().AddUser(user);
    }
}
