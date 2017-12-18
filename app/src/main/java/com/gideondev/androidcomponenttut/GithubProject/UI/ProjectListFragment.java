package com.gideondev.androidcomponenttut.GithubProject.UI;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gideondev.androidcomponenttut.GithubProject.adapter.ProjectClickCallback;
import com.gideondev.androidcomponenttut.GithubProject.adapter.ProjectListAdapter;
import com.gideondev.androidcomponenttut.GithubProject.viewModel.GithubProjectListViewModel;
import com.gideondev.androidcomponenttut.Model.Project;
import com.gideondev.androidcomponenttut.R;
import com.gideondev.androidcomponenttut.databinding.FragmentProjectListBinding;
import java.util.ArrayList;
import java.util.List;

public class ProjectListFragment
    extends Fragment {
    public static final String TAG = "ProjectListFragment";
    private FragmentProjectListBinding binding;
    private ProjectListAdapter projectListAdapter;
    private LiveData<List<Project>> mProjectList;
    private String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getArguments().getString("data");
        GithubProjectListViewModel viewModel = ViewModelProviders.of(this).get(GithubProjectListViewModel.class);
        observeViewModel(viewModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false);

        projectListAdapter = new ProjectListAdapter(getContext(), new ArrayList<Project>(), projectClickCallback);
        //bind recyclerview
        binding.projectList.setAdapter(projectListAdapter);
        //binding.projectList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setIsLoading(true);
        return  binding.getRoot();
    }

    private void observeViewModel(GithubProjectListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getProjectList(userId).observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects != null) {
                    binding.setIsLoading(false);
                    projectListAdapter.addItems(projects);
                }
            }
        });
    }


    public ProjectClickCallback projectClickCallback = new ProjectClickCallback() {
        @Override
        public void OnClick(Project model) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                        ((GithubActivity) getActivity()).show(model);
                    }
        }

    };
}
