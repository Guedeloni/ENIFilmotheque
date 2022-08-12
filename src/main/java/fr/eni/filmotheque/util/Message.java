package fr.eni.filmotheque.util;

public enum Message {
    CREATION_OK("Création bien enregistrée"),
    GENRE_EXIST("Genre déjà existant"),
    GENRE_NOT_EXIST("Genre inexistant"),
    FILM_EXIST("Film déjà existant (même titre avec même date de sortie)"),
    FILM_NOT_EXIST("Film inexistant"),
    MEMBRE_EXIST("Membre déjà existant (même login)"),
    MEMBRE_NOT_EXIST("Membre inexistant"),
    PARTICIPANT_EXIST("Participant déjà existant (même nom et prénom)"),
    PARTICIPANT_NOT_EXIST("Participant inexistant");

    private String msg;
    Message(String msg) {
        this.msg = msg;
    }

    public String showMsg() { return msg; }

}
