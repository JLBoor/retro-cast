package ca.iwd.retroapp.intermediate;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;

import ca.iwd.retroapp.simple.SimpleAdapter;
import retro.iwd.ca.retroApi.model.Retro;
import retro.iwd.ca.retroApi.model.RetroCollection;

public class ListRetroAsync extends AsyncTask<Void, Void, RetroCollection> {

    private SimpleAdapter retroListAdapter;

    public ListRetroAsync(SimpleAdapter retroListAdapter) {
        this.retroListAdapter = retroListAdapter;
    }

    @Override
    protected void onPostExecute(RetroCollection retroCollection) {
        this.retroListAdapter.setRetroList(retroCollection.getItems());
    }

    @Override
    protected RetroCollection doInBackground(Void... params) {

        try {
            return EndpointUtils.retroApi().listRetros().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
