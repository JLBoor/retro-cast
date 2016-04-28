package ca.iwd.retroapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ca.iwd.retroapp.intermediate.ListRetroAsync;
import ca.iwd.retroapp.simple.SimpleAdapter;

public class RetroListActivity extends AppCompatActivity {

    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createRetroIntent = new Intent(RetroListActivity.this, CreateRetroActivity.class);
                startActivity(createRetroIntent);
            }
        });

        simpleAdapter = new SimpleAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.retroList);

        initRecyclerView(recyclerView);

        new ListRetroAsync(simpleAdapter).execute();
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(simpleAdapter);
    }

    public void joinRetro(View view) {
        String projectName = view.getTag().toString();

        Intent retroIntent = RetroActivity.newIntent(this, projectName);
        startActivity(retroIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ListRetroAsync(simpleAdapter).execute();
    }
}
