package com.aegon.aravermeservisi.controller;

import com.aegon.aravermeservisi.model.AraVermeRequest;
import com.aegon.aravermeservisi.model.AraVermeResponse;
import com.aegon.aravermeservisi.service.AraVermeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@AllArgsConstructor
public class AraVermeController {

    private final AraVermeService araVermeService;

    @GetMapping("PremiumHoliday/Policy/{polid}")
    public AraVermeResponse getAraVermeBilgileri(@PathVariable String polid) throws Exception {
        AraVermeRequest araVermeRequest = new AraVermeRequest();
        araVermeRequest.setPolid(polid);
        AraVermeResponse response = araVermeService.getAraVermeBilgileri(araVermeRequest);
        return response;
    }

}
