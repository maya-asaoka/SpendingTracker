package com.example.yukariasaoka.spendingtracker;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

// An expense Entry object consisting of a description, amount, date, and category

public class Entry implements Parcelable, Comparable<Entry> {

    private String description;
    private String amount;
    private String date;
    private String category;

    // date in dd/mm/yy
    public Entry(String description, String amount, String date, String category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    // makes entry parceable so it can be sent as intent extra
    protected Entry(Parcel in) {
        description = in.readString();
        amount = in.readString();
        date = in.readString();
        category = in.readString();
    }

    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    // getters
    public String getDescription() { return description; }
    public String getAmount() { return amount; }
    public String getDate() { return date; }
    public String getCategory() { return category; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(amount);
        dest.writeString(date);
        dest.writeString(category);
    }

    // so collections.sort will sort by the description field
    @Override
    public int compareTo(Entry o) {
        return description.compareTo(o.getDescription());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(getDescription(), entry.getDescription()) &&
                Objects.equals(getAmount(), entry.getAmount()) &&
                Objects.equals(getDate(), entry.getDate()) &&
                Objects.equals(getCategory(), entry.getCategory());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDescription(), getAmount(), getDate(), getCategory());
    }
}
