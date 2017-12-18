package com.gideondev.androidcomponenttut.Database;

import com.gideondev.androidcomponenttut.Model.BorrowModel;
import java.util.Date;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by ${ENNY} on 12/5/2017.
 */
public class BorrowModelDaoTest extends DbTest{
    @Test
    public void getAllBorrowedItems() throws Exception {
    }

    @Test
    public void getItembyId() throws Exception {
        Date date = new Date("Sat Dec 01 00:00:00 GMT 2012");
        BorrowModel borrowModel1  = new BorrowModel("Pencils", "Tinh", date);
        db.itemAndPersonModel().addBorrow(borrowModel1);
        BorrowModel borrowModel2  = new BorrowModel("Pen", "Enny", date);
        db.itemAndPersonModel().addBorrow(borrowModel2);
        BorrowModel model = db.itemAndPersonModel().getItembyId(String.valueOf(1));
        assertThat(model.itemName, is("Pencils"));

    }

    @Test
    public void addBorrow() throws Exception {
        int size  = 2;
        Date date = new Date("Sat Dec 01 00:00:00 GMT 2012");
        BorrowModel borrowModel1  = new BorrowModel("Pencils", "Tinh", date);
        db.itemAndPersonModel().addBorrow(borrowModel1);
        BorrowModel borrowModel2  = new BorrowModel("Pen", "Enny", date);
        db.itemAndPersonModel().addBorrow(borrowModel2);

        assertEquals(LiveDataTestUtil.getValue(db.itemAndPersonModel().getAllBorrowedItems()).size(), size);
      //  assertThat(borrowModel.itemName, is("with token 1"));

    }

    @Test
    public void deleteBorrow() throws Exception {
    }
    @Test
    public void findEmptydata_ifNoAddBorrow() throws Exception {
        //Date date = new Date("Sat Dec 01 00:00:00 GMT 2012");
        //BorrowModel borrowModel1  = new BorrowModel(null, null, null);
        //db.itemAndPersonModel().addBorrow(borrowModel1);
        //BorrowModel borrowModel2  = new BorrowModel(null, null, null);
        //db.itemAndPersonModel().addBorrow(borrowModel2);
        assertNull(LiveDataTestUtil.getValue(db.itemAndPersonModel().getAllBorrowedItems()));
    }


    private BorrowModel createBorrow() {
        Date date = new Date("Sat Dec 01 00:00:00 GMT 2012");
        return new BorrowModel("Pencils", "Tinh", date);
    }

}