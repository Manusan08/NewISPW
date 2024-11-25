package it.uniroma2.ispw.enums;

public enum StatoProdotto {
    DISPONIBILE(1),
    ESAURITO(2),
    OCCUPATO(3);

    private final int id;

    private StatoProdotto(int id) {
        this.id = id;
    }

    public static StatoProdotto fromInt(int id) {
        for (StatoProdotto stato : values()) {
            if (stato.getId() == id) {
                return stato;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
