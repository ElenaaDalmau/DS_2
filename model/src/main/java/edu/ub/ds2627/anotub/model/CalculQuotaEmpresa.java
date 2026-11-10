package edu.ub.ds2627.anotub.model;

public class CalculQuotaEmpresa {
    public int quotaAnual(TipusEmpresa tipus) {
        return switch (tipus) {
            case PETITA -> 300;
            case MITJANA -> 700;
            case GRAN -> 1500;
        };
    }

    public int quotaAnual(String tipus) {
        try {
            return quotaAnual(
                    TipusEmpresa.valueOf(tipus.trim().toUpperCase())
            );
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException(
                    "Tipus d'empresa desconegut: " + tipus
            );
        }
    }
}
