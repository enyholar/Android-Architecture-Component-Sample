package com.gideondev.androidcomponenttut.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.gideondev.androidcomponenttut.Database.AppDatabase;
import com.gideondev.androidcomponenttut.Model.BorrowModel;
import java.util.List;

/**
 * Created by ${ENNY} on 11/24/2017.
 */
//Every ViewModel class must extend the ViewModel class
    //If  the ViewModel needs the application context, then it must extend the AndroidViewModel class
    //ViewModel will contain all the data needed for our Activity
    //LiveData is a wrapper that lets interested classes observe changes in the data inside the wrapper
public class BorrowedListViewModel extends AndroidViewModel {
    private final LiveData<List<BorrowModel>> itemAndPersonList;

    private AppDatabase appDatabase;
    public BorrowedListViewModel(@NonNull Application application) {
        super(application);
        //initialise database
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        //use the dao interface method in AppDatbase to query database and get all item
        // needed and use live data to notify UI anytime it chnages
        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }

    public LiveData<List<BorrowModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(BorrowModel borrowModel) {
        appDatabase.itemAndPersonModel().deleteBorrow(borrowModel);
    }

    //Static class to delete itm from database
    private static class deleteAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BorrowModel... params) {
            db.itemAndPersonModel().deleteBorrow(params[0]);
            return null;
        }

    }

}
