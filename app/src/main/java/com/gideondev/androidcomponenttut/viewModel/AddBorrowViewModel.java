package com.gideondev.androidcomponenttut.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import com.gideondev.androidcomponenttut.Database.AppDatabase;
import com.gideondev.androidcomponenttut.Database.BorrowModelDao;
import com.gideondev.androidcomponenttut.Model.BorrowModel;
import java.util.Calendar;
import java.util.Date;

public class AddBorrowViewModel extends AndroidViewModel {

    //private AppDatabase appDatabase;
    private BorrowModelDao dao;

    public AddBorrowViewModel(Application application, BorrowModelDao dao) {
        super(application);

        //appDatabase = AppDatabase.getDatabase(this.getApplication());
        this.dao = dao;

    }

    public void addBorrow(final BorrowModel borrowModel) {
        dao.addBorrow(borrowModel);
    }

    public void addBorrow(String itemName) {
        Date date = new Date("Sat Dec 01 00:00:00 GMT 2012");
        BorrowModel borrowModel = new BorrowModel(itemName, "Enny", date);
        dao.addBorrow(borrowModel);
    }

    public void addBorrowItem(String personName) {
        Date date = new Date("Sat Dec 01 00:00:00 GMT 2012");
        BorrowModel borrowModel = new BorrowModel("Pen", personName, date);
        dao.addBorrow(borrowModel);
    }

    private static class addAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BorrowModel... params) {

            return null;
        }

    }
}
