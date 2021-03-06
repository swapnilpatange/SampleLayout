package com.example.place.samplelayout;

import java.util.List;

public class CustomOption {
    String title;
    List<CustomOptionList> option;
    String type;
    String maxselected;
    String currentOption;

    public String getCurrentOption() {
        return currentOption;
    }

    public void setCurrentOption(String currentOption) {
        this.currentOption = currentOption;
    }

    public int currentselected;

    public int getCurrentselected() {
        return currentselected;
    }

    public void setCurrentselected(int currentselected) {
        this.currentselected = currentselected;
    }

    public String getMaxselected() {
        return maxselected;
    }

    public void setMaxselected(String maxselected) {
        this.maxselected = maxselected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CustomOptionList> getOption() {
        return option;
    }

    public void setOption(List<CustomOptionList> option) {
        this.option = option;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
