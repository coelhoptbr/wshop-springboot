package br.com.southsystem.workshop.zuul.helper;

import static br.com.southsystem.workshop.zuul.helper.Constants.SPRING_PROFILE_DEFAULT;
import static br.com.southsystem.workshop.zuul.helper.Constants.SPRING_PROFILE_LOCAL;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

public final class DefaultProfileHelper {

    private DefaultProfileHelper() { }

    public static void addDefaultProfile(SpringApplication app, String[] args) {
        Map<String, Object> defProperties =  new HashMap<String, Object>();
        defProperties.put(SPRING_PROFILE_DEFAULT, SPRING_PROFILE_LOCAL);
        app.setDefaultProperties(defProperties);
    }

    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
