package com.epam.entity;

public enum Category {
    Phone("Phones"),
    Computer("Computers"),
    Gadget("Gadgets"),
    AudioTechnology("Audio technology"),
    Appliances("Appliances"),
    Laptop("Laptops"),
    TV("TV");

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
