package com.gideondev.androidcomponenttut.CustomSetter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gideondev.androidcomponenttut.R;
import com.gideondev.androidcomponenttut.databinding.ActivityCustomSetterBinding;

public class CustomSetterActivity
    extends AppCompatActivity {

   private ActivityCustomSetterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_setter);

    }
}
