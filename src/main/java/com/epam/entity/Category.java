package com.epam.entity;

import java.lang.annotation.Target;

public enum Category {
    Phone("Phones"),
    Computer("Computers"),
    SmartGadget("Smart gadgets"),
    AudioTechnology("Audio technology"),
    KitchenAppliances("Kitchen appliances"),
    Laptop("Laptops"),
    TV("TV"),
    Camera("Cameras"),
    Tablet("Tablets"),
    ForGamers("For gamers");


    private String nameCategory;

    Category(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    @Override
    public String toString() {
        return nameCategory;
    }
}
