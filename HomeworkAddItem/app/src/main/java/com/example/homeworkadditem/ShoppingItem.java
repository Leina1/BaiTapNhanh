package com.example.homeworkadditem;

import android.os.Parcel;
import android.os.Parcelable;

public class ShoppingItem implements Parcelable {
    private String name;
    private int quantity;

    public ShoppingItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    protected ShoppingItem(Parcel in) {
        name = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<ShoppingItem> CREATOR = new Creator<ShoppingItem>() {
        @Override
        public ShoppingItem createFromParcel(Parcel in) {
            return new ShoppingItem(in);
        }

        @Override
        public ShoppingItem[] newArray(int size) {
            return new ShoppingItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}