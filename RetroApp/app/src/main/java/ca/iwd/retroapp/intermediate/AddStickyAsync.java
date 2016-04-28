package ca.iwd.retroapp.intermediate;

import android.os.AsyncTask;

import java.io.IOException;

import retro.iwd.ca.retroApi.model.Retro;

public class AddStickyAsync extends AsyncTask<Void, Void, Retro> {

    private String message;
    private String projectName;

    public AddStickyAsync(String projectName, String message) {
        this.message = message;
        this.projectName = projectName;
    }

    @Override
    protected Retro doInBackground(Void... params) {

        try {
            return EndpointUtils.retroApi().addSticky(projectName, message).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
