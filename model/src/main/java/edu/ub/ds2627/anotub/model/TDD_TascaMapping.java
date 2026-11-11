package edu.ub.ds2627.anotub.model;


import java.util.Map;
import java.util.LinkedHashMap;

public class TDD_TascaMapping implements TascaMap<Map<String, String>> {
    @Override
    public Map<String, String> mapejar(String id, String descripcio, String expertesa) {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("idTasca", id);
        m.put("descripcioTasca", descripcio);
        m.put("expertesaNecessaria", expertesa);
        return m;
    }
}
