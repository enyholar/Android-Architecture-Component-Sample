package com.gideondev.androidcomponenttut.BorrowProject.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.gideondev.androidcomponenttut.BR;
import com.gideondev.androidcomponenttut.Model.BorrowModel;
import com.gideondev.androidcomponenttut.R;
import java.util.List;

/**
 * Created by ${ENNY} on 11/24/2017.
 */

public class BorrowRecAdapter
    extends RecyclerView.Adapter<BorrowRecAdapter.RecyclerViewHolder> {

    private List<BorrowModel> borrowModelList;
    private BorrowRecAdapterListner mListener;
    private Context mContext;

    public BorrowRecAdapter(Context context,List<BorrowModel> borrowModelList,
                            BorrowRecAdapterListner listner) {
        this.borrowModelList = borrowModelList;
        this.mListener = listner;
        this.mContext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
        //                                  .inflate(R.layout.borrow_item, parent, false));
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.borrow_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new RecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final BorrowModel borrowModel = borrowModelList.get(position);
        holder.bind(borrowModel);
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Click",Toast.LENGTH_LONG).show();
            }
        });
        holder.mBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mListener.OnDelete(borrowModel);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return borrowModelList.size();
    }

    public void addItems(List<BorrowModel> borrowModelList) {
        this.borrowModelList = borrowModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder
        extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;
        public RecyclerViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
        public void bind(BorrowModel borrowModel) {
            mBinding.setVariable(BR.borrowModel, borrowModel);
            mBinding.executePendingBindings();
        }

    }
}
