package com.gideondev.androidcomponenttut.GithubProject.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.gideondev.androidcomponenttut.GithubProject.service.ProjectRepository;
import com.gideondev.androidcomponenttut.Model.Project;

/**
 * Created by ${ENNY} on 12/4/2017.
 */

public class ProjectViewDetailsViewModel extends AndroidViewModel {
    public ObservableField<Project> project = new ObservableField<>();
    private  LiveData<Project> projectObservable;
    public ProjectViewDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Project> getObservableProject() {
        return projectObservable;
    }

    public void setProject(Project project) {
        this.project.set(project);
    }

    public LiveData<Project> getProjectDetails(String userId, String projectName){
        projectObservable = ProjectRepository.getInstance().getProjectDetails(userId,projectName);
        return projectObservable;
    }
}
