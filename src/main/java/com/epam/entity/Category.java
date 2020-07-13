package com.epam.entity;

import java.lang.annotation.Target;

public enum Category {
    Phone("Phone"),
    Computer("Computer"),
    SmartGadget("SmartGadget"),
    AudioTechnology("AudioTechnology"),
    KitchenAppliances("KitchenAppliances"),
    Laptop("Laptop"),
    TV("TV"),
    Camera("Camera"),
    Tablet("Tablet"),
    ForGamers("ForGamers");


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
