package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class DriverProvider {

    public static UiAutomator2Options loadCapabilities(String filePath) throws
            IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = new FileInputStream(filePath);
        return objectMapper.readValue(is, UiAutomator2Options.class);
    }

    public static AndroidDriver provideDriver() throws IOException {
        return new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                loadCapabilities("src/test/resources/android_capabilities.json")
        );
    }
}
