package com.example.shopspring.exception;

public class ItemNotFound extends IllegalArgumentException{
    public ItemNotFound() {
        super("Item not found");
    }
}
