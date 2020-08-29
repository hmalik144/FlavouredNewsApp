package com.example.h_mal.flavourednewsapp.utils;


public class Event<T> {
    private T content;
    private Boolean hasBeenHandled = false;

    public Event(T content) {
        this.content = content;
    }

    public T getContentIfNotHandled(){
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }
}
