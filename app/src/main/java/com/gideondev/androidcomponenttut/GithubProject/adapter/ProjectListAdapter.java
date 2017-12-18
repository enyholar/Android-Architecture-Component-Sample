package com.gideondev.androidcomponenttut.GithubProject.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.gideondev.androidcomponenttut.BR;
import com.gideondev.androidcomponenttut.Model.Project;
import com.gideondev.androidcomponenttut.R;
import com.gideondev.androidcomponenttut.databinding.ProjectListItemBinding;
import com.gideondev.androidcomponenttut.BorrowProject.main.BorrowRecAdapterListner;
import java.util.List;

/**
 * Created by ${ENNY} on 11/29/2017.
 */

public class ProjectListAdapter  extends RecyclerView.Adapter<ProjectListAdapter.RecyclerViewHolder> {

    private List<Project> projectModelList;
    private BorrowRecAdapterListner mListener;
    private Context mContext;
    private ProjectClickCallback projectClickCallback;

    public ProjectListAdapter(Context context, List<Project> ModelList,
                              ProjectClickCallback projectClickCallback ) {
        this.projectModelList = ModelList;
        this.mContext = context;
        this.projectClickCallback = projectClickCallback;
    }

    @Override
    public ProjectListAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProjectListItemBinding binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.project_list_item, parent, false);
        binding.setCallback(projectClickCallback);
        // set the view's size, margins, paddings and layout parameters
        return new ProjectListAdapter.RecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ProjectListAdapter.RecyclerViewHolder holder, int position) {
        final Project projectModel = projectModelList.get(position);
        holder.bind(projectModel);

    }

    @Override
    public int getItemCount() {
        return  projectModelList == null ? 0 : projectModelList.size();
    }

    public void addItems(List<Project> projectModellList) {
        this.projectModelList = projectModellList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder
        extends RecyclerView.ViewHolder {
        private ProjectListItemBinding mBinding;

        public RecyclerViewHolder(ProjectListItemBinding  binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(Project project) {
            mBinding.setVariable(BR.project, project);
            mBinding.executePendingBindings();
        }


    }
}
