package com.aegon.aravermeservisi.service;

import com.aegon.aravermeservisi.model.AraVermeRequest;
import com.aegon.aravermeservisi.model.AraVermeResponse;

import java.text.ParseException;

public interface AraVermeService {
    AraVermeResponse getAraVermeBilgileri(AraVermeRequest araVermeRequest) throws ParseException, Exception;
}
