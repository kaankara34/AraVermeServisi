package com.aegon.aravermeservisi.service.impl;

import com.aegon.aravermeservisi.model.AraVermeRequest;
import com.aegon.aravermeservisi.model.AraVermeResponse;
import com.aegon.aravermeservisi.model.FormattedResponse;
import com.aegon.aravermeservisi.repository.QueryPolicyCustomRepository;
import com.aegon.aravermeservisi.service.AraVermeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
@AllArgsConstructor
public class AraVermeServiceImpl implements AraVermeService {

    private final QueryPolicyCustomRepository queryPolicyCustomRepository;

    @Override
    public AraVermeResponse getAraVermeBilgileri(AraVermeRequest araVermeRequest) throws Exception {

        Map<String, Object> araVermeBilgileri = queryPolicyCustomRepository.getAraVermeBilgileri(araVermeRequest.getPolid());
        FormattedResponse formattedResponse = formatTheResponse(araVermeBilgileri);
        ModelMapper modelMapper = new ModelMapper();
        AraVermeResponse araVermeResponse = modelMapper.map(formattedResponse, AraVermeResponse.class);
        return araVermeResponse;
    }

    private FormattedResponse formatTheResponse(Map<String, Object> araVermeBilgileri) throws Exception {
        FormattedResponse formattedResponse = new FormattedResponse();
        if (!StringUtils.isEmpty(araVermeBilgileri.get("penddate"))) {
            if (!araVermeBilgileri.get("penddate").equals("-")) {
                formattedResponse.setPoliceBitisTarihi(araVermeBilgileri.get("penddate").toString());
                String policeBitisTarihi = araVermeBilgileri.get("penddate").toString();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date = dateFormat.parse(policeBitisTarihi);
                if (StringUtils.isEmpty(araVermeBilgileri.get("errstr"))) {
                    Calendar cal3 = Calendar.getInstance();
                    cal3.setTime(date);
                    cal3.add(Calendar.MONTH, 3);
                    String policeBitis3Ay = dateFormat.format(cal3.getTime());
                    Calendar cal6 = Calendar.getInstance();
                    cal6.setTime(date);
                    cal6.add(Calendar.MONTH, 6);
                    String policeBitis6Ay = dateFormat.format(cal6.getTime());
                    formattedResponse.setPoliceBitis3Ay(policeBitis3Ay);
                    formattedResponse.setPoliceBitis6Ay(policeBitis6Ay);
                }


            } else {
                formattedResponse.setPoliceBitisTarihi(araVermeBilgileri.get("penddate").toString());
            }


        }

        if (!StringUtils.isEmpty(araVermeBilgileri.get("phearned"))) {
            formattedResponse.setToplamAraVermeHakki(araVermeBilgileri.get("phearned").toString());
        }
        if (!StringUtils.isEmpty(araVermeBilgileri.get("phused"))) {
            if (!araVermeBilgileri.get("phused").toString().equals("0")) {
                formattedResponse.setAraVermeGecmis(araVermeBilgileri.get("phused").toString() + " kez, toplam " + araVermeBilgileri.get("phduration").toString() + " Ay");
            } else {
                formattedResponse.setAraVermeGecmis("Ara verilmemiş");
            }

        }
        if (!StringUtils.isEmpty(araVermeBilgileri.get("phright"))) {
            if (!araVermeBilgileri.get("phright").toString().equals("0")) {
                formattedResponse.setKullanilabilecekAraVermeSuresi("3 veya 6 Ay");
            } else {
                formattedResponse.setKullanilabilecekAraVermeSuresi(araVermeBilgileri.get("phright").toString());
            }

        }
        if (!StringUtils.isEmpty(araVermeBilgileri.get("errstr"))) {
            formattedResponse.setAciklama(araVermeBilgileri.get("errstr").toString());
        }


        return formattedResponse;


    }
}
