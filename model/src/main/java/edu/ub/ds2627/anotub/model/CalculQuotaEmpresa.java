package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.TipusEmpresaDesconegutException;

public class CalculQuotaEmpresa {
    public int quotaAnual(TipusEmpresa tipus) {
        return tipus.getQuotaAnual();
    }

    public int quotaAnual(String tipus) throws TipusEmpresaDesconegutException {
        return quotaAnual(TipusEmpresa.desDe(tipus));
    }
}
