package com.mamaalert.services;

public interface SmsService {
    boolean sendSms(String to, String message);
}

