package com.example.calculator.services;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service class
 */
@Service
public class Calculator {
    @Cacheable
    public int sum(int a, int b){
        return a+b;
    }
}
