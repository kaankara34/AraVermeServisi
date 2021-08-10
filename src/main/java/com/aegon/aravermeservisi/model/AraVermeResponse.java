package com.aegon.aravermeservisi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AraVermeResponse {
    private String policeBitisTarihi;
    private String toplamAraVermeHakki;
    private String araVermeGecmis;
    private String kullanilabilecekAraVermeSuresi;
    private String policeBitis3Ay;
    private String policeBitis6Ay;
    private String aciklama;


}
