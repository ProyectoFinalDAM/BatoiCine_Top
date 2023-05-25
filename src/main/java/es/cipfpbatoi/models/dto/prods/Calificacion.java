package es.cipfpbatoi.models.dto.prods;

public enum Calificacion {
    G, PG, PG13{
        @Override
        public String toString() {
            return "PG-13";
        }
    }, R, X, UNRATED, APPROVED
}
