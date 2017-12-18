package com.gideondev.androidcomponenttut.GithubProject.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.gideondev.androidcomponenttut.GithubProject.service.ProjectRepository;
import com.gideondev.androidcomponenttut.Model.Project;
import java.util.List;

/**
 * Created by ${ENNY} on 11/29/2017.
 */

public class GithubProjectListViewModel extends AndroidViewModel {

    private LiveData<List<Project>> projectListObservable;

    public GithubProjectListViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<List<Project>> getProjectList(String userId){
        projectListObservable = ProjectRepository.getInstance().getProjectList(userId);
        return projectListObservable;
    }



}
