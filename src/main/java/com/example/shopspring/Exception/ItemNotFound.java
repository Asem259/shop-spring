package com.example.shopspring.Exception;

public class ItemNotFound extends IllegalArgumentException{
    public ItemNotFound(String message) {
        super(message);
    }
}
