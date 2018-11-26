package com.example.naver.naver_iso.not_used;

/**
 * Created by Naver on 2018. 8. 6..
 */

import java.util.ArrayList;
public class Prototype_myGroup {
    public ArrayList<String> childTitle;
    public ArrayList<String> childUrl;

    public String groupName;
    Prototype_myGroup(String name){
        groupName = name;
        childTitle = new ArrayList<String>();
        childUrl = new ArrayList<String>();
    }
}
