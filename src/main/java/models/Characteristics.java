package models;

/**
 * Created by Guest on 8/25/17.
 */
public class Characteristics {
    private int attack;
    private int health;
    private int mana;

    public Characteristics(int mana) {
        this.mana = mana;
    }

    public Characteristics(int attack, int health, int mana) {
        this.attack = attack;
        this.health = health;
        this.mana = mana;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Characteristics that = (Characteristics) o;

        if (attack != that.attack) return false;
        if (health != that.health) return false;
        return mana == that.mana;
    }

    @Override
    public int hashCode() {
        int result = attack;
        result = 31 * result + health;
        result = 31 * result + mana;
        return result;
    }
}
