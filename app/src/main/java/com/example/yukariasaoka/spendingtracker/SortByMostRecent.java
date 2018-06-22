package com.example.yukariasaoka.spendingtracker;

import java.util.Comparator;

public class SortByMostRecent implements Comparator<Entry> {
    @Override
    public int compare(Entry o1, Entry o2) {
        int i = o1.getDate().substring(6, 8).compareTo(o2.getDate().substring(6, 8));
        if (i != 0) return i;

        i = o1.getDate().substring(3, 5).compareTo(o2.getDate().substring(3, 5));
        if (i != 0) return i;

        return o1.getDate().substring(0, 2).compareTo(o2.getDate().substring(0, 2));
    }
}
