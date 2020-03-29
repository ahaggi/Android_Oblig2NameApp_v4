package com.example.danie.nameapp_v4;

import android.app.Application;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by danie on 22.01.2018.
 */

public class GlobalClass extends Application{
    private HashMap<String, Uri> names = new HashMap<>();

    public GlobalClass() {
        super();

        addName("Daniel", "android.resource://" + getClass().getPackage().getName() + "/drawable/daniel");
        addName("Abdella", "android.resource://" + getClass().getPackage().getName() + "/drawable/abdella");
    }

    public ArrayList<String> getNames() {
        ArrayList<String> nameArray = new ArrayList<>();
        Set set = names.entrySet();
        for (Object aSet : set) {
            Map.Entry entry = (Map.Entry) aSet;
            nameArray.add(entry.getKey().toString());
        }
        return nameArray;
    }

    public void addName(String name, String uriString) {
        Uri uri = Uri.parse(uriString);
        names.put(name, uri);
    }

    public void addNameUri(String name, Uri uri) {
        names.put(name, uri);
    }

    public Uri getUri(String name) {
        return names.get(name);
    }
}
