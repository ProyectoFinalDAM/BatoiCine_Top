package es.cipfpbatoi.models.dto.prods;

public enum Tipo {
    MOVIE{
        @Override
        public String toString() {
            return "movie";
        }
    }, TVSHOW{
        @Override
        public String toString() {
            return "tv-show";
        }
    }
}
