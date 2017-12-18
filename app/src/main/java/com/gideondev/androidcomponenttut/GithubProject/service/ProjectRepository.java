package com.gideondev.androidcomponenttut.GithubProject.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.gideondev.androidcomponenttut.Model.Project;
import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ${ENNY} on 11/29/2017.
 */

public class ProjectRepository {
    private GithubService gitHubService;
    private static ProjectRepository projectRepository;
    private ProjectRepository() {
        //TODO this gitHubService instance will be injected using Dagger in part #2 ...
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GithubService.HTTPS_API_GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        gitHubService = retrofit.create(GithubService.class);
    }
//projectReopoitory is inistialised here aand can used in anyclase with this
    public synchronized static ProjectRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (projectRepository == null) {
           // if (projectRepository == null) {
                projectRepository = new ProjectRepository();
           // }
        }
        return projectRepository;
    }

    public LiveData<List<Project>> getProjectList(String userId) {
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Response<List<Project>> response, Retrofit retrofit) {
                data.setValue(response.body()); // your current thread
               // data.postValue(response.body()); // UI thread
            }

            @Override
            public void onFailure(Throwable t) {
                data.setValue(null);
            }


            // Error handling will be explained in the next article …
        });

        return data;
    }


    public LiveData<Project> getProjectDetails(String userID, String projectName) {
        final MutableLiveData<Project> data = new MutableLiveData<>();

        gitHubService.getProjectDetails(userID, projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Response<Project> response, Retrofit retrofit) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });

        return data;
    }
}
