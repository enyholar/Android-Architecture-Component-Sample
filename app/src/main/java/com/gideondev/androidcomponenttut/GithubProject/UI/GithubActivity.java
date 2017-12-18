package com.gideondev.androidcomponenttut.GithubProject.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gideondev.androidcomponenttut.Model.Project;
import com.gideondev.androidcomponenttut.R;

public class GithubActivity
    extends AppCompatActivity {
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);
        // Add project list fragment if this is first creation
        Intent intent = getIntent();
        username = intent.getStringExtra("data");

        if (savedInstanceState == null) {
            ProjectListFragment fragment = new ProjectListFragment();
            Bundle data = new Bundle();
            data.putString("data",username);
            fragment.setArguments(data);
            getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, ProjectListFragment.TAG).commit();
        }
    }


    public void show(Project project) {
        ProjectDetailsFragment projectFragment = ProjectDetailsFragment.newInstance(project.name,username);

        getSupportFragmentManager()
            .beginTransaction()
            .addToBackStack("project")
            .replace(R.id.fragment_container,
                     projectFragment, null).commit();
    }

}
