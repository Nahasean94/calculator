package com.example.calculator.services;


import org.springframework.stereotype.Service;

/**
 * Service class
 */
@Service
public class Calculator {
    public int sum(int a, int b){
        return a+b;
    }
}
