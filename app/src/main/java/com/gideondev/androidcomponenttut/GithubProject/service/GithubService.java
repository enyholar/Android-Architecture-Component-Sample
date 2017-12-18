package com.gideondev.androidcomponenttut.GithubProject.service;

import com.gideondev.androidcomponenttut.Model.Project;
import java.util.List;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ${ENNY} on 11/29/2017.
 */

public interface GithubService {
    String HTTPS_API_GITHUB_URL = "https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<Project>> getProjectList(@Path("user") String user);

    @GET("/repos/{user}/{reponame}")
    Call<Project> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);
}
