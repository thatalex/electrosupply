package by.alex.web.site.model;

public enum  State {
    NEW("Новый"),
    PROCESSING("В работе"),
    CANCELED("Отменен"),
    FINISHED("Закончен");

    private final String name;

    State(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
