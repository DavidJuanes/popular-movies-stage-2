package com.davidjuanes.popular_movies.one.platform;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Simple loader for the properties located at src/main/assests/application.properties
 */

public class ApplicationPropertiesLoader {
    private static final String LOG_TAG = "PROPS_LOADER";
    private static final String PROPERTIES_FILENAME = "application.properties";
    private static final String APIKEY_KEY = "themoviedb.apikey";

    public static String getProperty(String key, Context context) {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(PROPERTIES_FILENAME);
            properties.load(inputStream);
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            throw new PropertyNotFoundException();
        }
        String value = properties.getProperty(key);

        if (value == null)
            throw new PropertyNotFoundException();
        return value;
    }

    public static String getApiKey(Context context)
    {
        return getProperty(APIKEY_KEY, context);
    }
}
