package com.aegon.aravermeservisi.repository.impl;

import com.aegon.aravermeservisi.repository.QueryPolicyCustomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.HashMap;
import java.util.Map;

@Repository
public class QueryPolicyCustomRepositoryImpl implements QueryPolicyCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Value("${ara.verme.servisi.procedure}")
    private String QUERYPOLICY_PROCEDURE;

    @Value("${ara.verme.servisi.ppolid}")
    private String PPOLID;

    @Value("${ara.verme.servisi.penddate}")
    private String PENDDATE;

    @Value("${ara.verme.servisi.phearned}")
    private String PHEARNED;

    @Value("${ara.verme.servisi.phused}")
    private String PHUSED;

    @Value("${ara.verme.servisi.phduration}")
    private String PHDURATION;

    @Value("${ara.verme.servisi.phright}")
    private String PHRIGHT;

    @Value("${ara.verme.servisi.errstr}")
    private String ERRSTR;


    @Override
    public Map<String, Object> getAraVermeBilgileri(String polid) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery(QUERYPOLICY_PROCEDURE);
        query.registerStoredProcedureParameter(PPOLID, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(PENDDATE, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(PHEARNED, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(PHUSED, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(PHDURATION, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(PHRIGHT, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(ERRSTR, String.class, ParameterMode.OUT);

        query.setParameter(PPOLID, polid);
        query.execute();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("penddate", query.getOutputParameterValue(PENDDATE));
        resultMap.put("phearned", query.getOutputParameterValue(PHEARNED));
        resultMap.put("phused", query.getOutputParameterValue(PHUSED));
        resultMap.put("phduration", query.getOutputParameterValue(PHDURATION));
        resultMap.put("phright", query.getOutputParameterValue(PHRIGHT));
        resultMap.put("errstr", query.getOutputParameterValue(ERRSTR));
        return resultMap;

    }
}
