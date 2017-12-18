package com.gideondev.androidcomponenttut.BorrowProject.AddItem;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.Toast;
import com.gideondev.androidcomponenttut.Model.BorrowModel;
import com.gideondev.androidcomponenttut.R;
import com.gideondev.androidcomponenttut.databinding.ActivityAddBinding;
import com.gideondev.androidcomponenttut.viewModel.AddBorrowViewModel;
import java.util.Calendar;
import java.util.Date;

public class AddActivity
    extends AppCompatActivity
    implements DatePickerDialog.OnDateSetListener {

    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private AddBorrowViewModel addBorrowViewModel;
    private FloatingActionButton fab;
    private ActivityAddBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        binding.setAdd(this);
        initModel();
    }

    private void initModel() {
        calendar = Calendar.getInstance();
        //addBorrowViewModel = new AddBorrowViewModel(getApplication());
        addBorrowViewModel = ViewModelProviders.of(this).get(AddBorrowViewModel.class);
        datePickerDialog = new DatePickerDialog(this, AddActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

    }

    public void addNewItem() {
        if (binding.itemName.getText()== null || binding.personName.getText() == null || date == null)
            Toast.makeText(AddActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
        else {
            addBorrowViewModel.addBorrow(new BorrowModel(
                binding.itemName.getText().toString(),
                binding.personName.getText().toString(),
                date
            ));
            finish();
        }
    }

    private void initView() {

    }

    public void DateShow() {
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();

    }

}
