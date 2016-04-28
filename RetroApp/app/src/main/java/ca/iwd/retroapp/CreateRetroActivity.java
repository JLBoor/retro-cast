package ca.iwd.retroapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import ca.iwd.retroapp.intermediate.CreateRetroAsync;

public class CreateRetroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText projectName;
    private EditText room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_retro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        projectName = (EditText) findViewById(R.id.projectName);
        room = (EditText) findViewById(R.id.room);

        findViewById(R.id.createRetro).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new CreateRetroAsync(this, projectName.getText().toString(), room.getText().toString()).execute();
    }
}
