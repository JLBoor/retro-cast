package ca.iwd.retroapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import ca.iwd.retroapp.advanced.ChromecastActivity;
import ca.iwd.retroapp.intermediate.AddStickyAsync;

public class RetroActivity extends ChromecastActivity implements View.OnClickListener {

    private static final String EXTRA_PROJECT_NAME = "PROJECT_NAME";
    private String projectName;
    private EditText message;

    public static Intent newIntent(Context context, String projectName) {
        Intent retroActivityIntent = new Intent(context, RetroActivity.class);
        retroActivityIntent.putExtra(EXTRA_PROJECT_NAME, projectName);
        return retroActivityIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        projectName = getIntent().getStringExtra(EXTRA_PROJECT_NAME);
        message = (EditText) findViewById(R.id.message);
        findViewById(R.id.sendMessage).setOnClickListener(this);

        setTitle(projectName);
    }

    @Override
    public void onClick(View v) {
        new AddStickyAsync(projectName, message.getText().toString()).execute();
        sendChromecastMessage(message.getText().toString());
        message.setText("");
    }
}
