package com.aegon.aravermeservisi.repository;

import java.util.Map;

public interface QueryPolicyCustomRepository {
    Map<String, Object> getAraVermeBilgileri(String polid);
}
