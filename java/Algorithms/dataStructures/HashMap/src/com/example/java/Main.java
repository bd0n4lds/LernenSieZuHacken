package com.example.java;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("Ontario", "Toronto");
        map.put("Oregon", "New York");
        map.put("Amsterdam", "Amsterdam");
        System.out.println(map);
        map.put("Alaska", "Juneau");
        System.out.println(map);
        String cap = map.get("Oregon");
        System.out.println("The Cap of Oregon is" + cap);
        map.remove("Ontario");
        System.out.println(map);

    }
}
