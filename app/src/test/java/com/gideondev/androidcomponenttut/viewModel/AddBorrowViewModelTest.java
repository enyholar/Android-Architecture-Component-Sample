package com.gideondev.androidcomponenttut.viewModel;

import android.app.Application;
import com.gideondev.androidcomponenttut.Database.BorrowModelDao;
import com.gideondev.androidcomponenttut.Model.BorrowModel;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;

/**
 * Created by ${ENNY} on 12/5/2017.
 */
public class AddBorrowViewModelTest {

    BorrowModelDao mockDao;
    AddBorrowViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        mockDao = Mockito.mock(BorrowModelDao.class);

        viewModel = new AddBorrowViewModel(Mockito.mock(Application.class), mockDao);
    }

    @Test
    public void addBorrow() throws Exception {
        Date date = new Date("Sat Dec 01 00:00:00 GMT 2012");
        viewModel.addBorrow("fun");
        viewModel.addBorrowItem("Enny");
        verify(mockDao).addBorrow(eq(new BorrowModel("fun", "Enny", date)));
        verify(mockDao).addBorrow(eq(new BorrowModel("Pen", "Enny", date)));
    }


}