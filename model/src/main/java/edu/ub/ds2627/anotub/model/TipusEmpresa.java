package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.TipusEmpresaDesconegutException;

// OCP: cada tipus coneix la seva quota; afegir-ne un no obliga a modificar CalculQuotaEmpresa
public enum TipusEmpresa {
    PETITA(300),
    MITJANA(700),
    GRAN(1500);

    private final int quotaAnual;

    TipusEmpresa(int quotaAnual) {
        this.quotaAnual = quotaAnual;
    }

    public int getQuotaAnual() {
        return quotaAnual;
    }

    public static TipusEmpresa desDe(String nom) throws TipusEmpresaDesconegutException {
        if (nom == null) {
            throw new TipusEmpresaDesconegutException(null);
        }
        try {
            return valueOf(nom.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TipusEmpresaDesconegutException(nom);
        }
    }
}
