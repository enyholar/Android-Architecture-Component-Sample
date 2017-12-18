package com.gideondev.androidcomponenttut.GithubProject.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gideondev.androidcomponenttut.GithubProject.viewModel.ProjectViewDetailsViewModel;
import com.gideondev.androidcomponenttut.Model.Project;
import com.gideondev.androidcomponenttut.R;
import com.gideondev.androidcomponenttut.databinding.FragmentProjectDetailsBinding;

public class ProjectDetailsFragment
    extends Fragment {
    private static final String KEY_PROJECT_ID = "project_id";
    private static final String KEY_USER_ID = "user_id";
    private FragmentProjectDetailsBinding binding;
    private String ProjectId;
    private String userId;

    public ProjectDetailsFragment() {
        // Required empty public constructor
    }

    public static ProjectDetailsFragment newInstance(String projectID, String userId) {
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_PROJECT_ID, projectID);
        args.putString(KEY_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    private void observeViewModel(final ProjectViewDetailsViewModel viewModel) {
        // Observe project data
        viewModel.getProjectDetails(userId,ProjectId).observe(this, new Observer<Project>() {
            @Override
            public void onChanged(@Nullable Project project) {
                if (project != null) {
                    binding.setIsLoading(false);
                    viewModel.setProject(project);
                }
            }
        });
    }



    //@Override
    //public void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //
    //}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProjectId = getArguments().getString(KEY_PROJECT_ID);
        userId = getArguments().getString(KEY_USER_ID);
        ProjectViewDetailsViewModel viewModel = ViewModelProviders.of(this).get(ProjectViewDetailsViewModel.class);
        binding.setProjectViewModel(viewModel);
        binding.setIsLoading(true);
        observeViewModel(viewModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_project_details, container, false);
        return binding.getRoot();
    }

}
