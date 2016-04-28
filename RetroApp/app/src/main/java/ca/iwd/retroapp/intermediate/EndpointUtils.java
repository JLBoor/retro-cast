package ca.iwd.retroapp.intermediate;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;

import java.io.IOException;

import ca.iwd.retroapp.BuildConfig;
import retro.iwd.ca.retroApi.RetroApi;

public final class EndpointUtils {

    public static RetroApi retroApi() {

        RetroApi.Builder builder = new RetroApi.Builder(
                AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), new SimpleHttpRequestInitializer())
                .setRootUrl(BuildConfig.RETRO_API_URL)
                .setGoogleClientRequestInitializer(new GZipDisabledRequestInitializer());

        return builder.build();
    }

    private static class SimpleHttpRequestInitializer implements HttpRequestInitializer {

        @Override
        public void initialize(HttpRequest request) throws IOException {
            // Nothing
        }
    }


    private static class GZipDisabledRequestInitializer implements GoogleClientRequestInitializer {
        @Override
        public void initialize(final AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
            abstractGoogleClientRequest.setDisableGZipContent(true);
        }
    }


}
