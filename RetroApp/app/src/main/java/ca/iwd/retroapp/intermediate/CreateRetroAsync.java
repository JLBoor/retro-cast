package ca.iwd.retroapp.intermediate;

import android.content.Intent;
import android.os.AsyncTask;

import java.io.IOException;

import ca.iwd.retroapp.CreateRetroActivity;
import ca.iwd.retroapp.RetroActivity;
import retro.iwd.ca.retroApi.model.Retro;

public class CreateRetroAsync extends AsyncTask<Void, Void, Retro> {

    private final CreateRetroActivity createRetroActivity;
    private final Retro retro = new Retro();;

    public CreateRetroAsync(CreateRetroActivity createRetroActivity, String projectName, String room) {
        this.createRetroActivity = createRetroActivity;
        retro.setProjectName(projectName);
        retro.setRoom(room);
    }

    @Override
    protected void onPostExecute(Retro retro) {
        Intent retroActivityIntent = RetroActivity.newIntent(createRetroActivity, retro.getProjectName());
        createRetroActivity.startActivity(retroActivityIntent);
    }

    @Override
    protected Retro doInBackground(Void... params) {

        try {
            return EndpointUtils.retroApi().createRetro(retro).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
