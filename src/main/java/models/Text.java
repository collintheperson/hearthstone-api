package models;

/**
 * Created by Guest on 8/25/17.
 */
public class Text extends Characteristics {
    private String classType;
    private String text;
    private String name;

    public Text(int mana, String classType, String text, String name) {
        super(mana);
        this.classType = classType;
        this.text= text;
        this.name=name;
    }

    public Text(int attack, int health, int mana, String classType, String text, String name) {
        super(attack, health, mana);
        this.classType= classType;
        this.text= text;
        this.name= name;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
