package com.gideondev.androidcomponenttut.GithubProject.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.gideondev.androidcomponenttut.BR;
import com.gideondev.androidcomponenttut.Model.User;
import com.gideondev.androidcomponenttut.R;
import com.gideondev.androidcomponenttut.databinding.UserItemBinding;
import com.gideondev.androidcomponenttut.BorrowProject.main.BorrowRecAdapterListner;
import java.util.List;

/**
 * Created by ${ENNY} on 11/29/2017.
 */

public class UserListAdapter
    extends RecyclerView.Adapter<UserListAdapter.RecyclerViewHolder> {

    private List<User> UserModelList;
    private BorrowRecAdapterListner mListener;
    private Context mContext;
    private UserClickCallback projectClickCallback;

    public UserListAdapter(Context context, List<User> ModelList,
                           UserClickCallback projectClickCallback ) {
        this.UserModelList = ModelList;
        this.mContext = context;
        this.projectClickCallback = projectClickCallback;
    }

    @Override
    public UserListAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UserItemBinding binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.user_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        binding.setCallback(projectClickCallback);
        return new UserListAdapter.RecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final UserListAdapter.RecyclerViewHolder holder, int position) {
        final User userModel = UserModelList.get(position);
        holder.bind(userModel);

    }

    @Override
    public int getItemCount() {
        return  UserModelList == null ? 0 : UserModelList.size();
    }

    public void addItems(List<User> projectModellList) {
        this.UserModelList = projectModellList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder
        extends RecyclerView.ViewHolder {
        private UserItemBinding mBinding;

        public RecyclerViewHolder(UserItemBinding  binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(User user) {
            mBinding.setVariable(BR.user, user);
            mBinding.executePendingBindings();
        }


    }
}
