package models;

/**
 * Created by Guest on 8/25/17.
 */
public class CardText extends Characteristics {
    private int attack;
    private int health;
    private int rarityId;

    public CardText(int mana, String classType, String cardDetail, String name, int attack, int health) {
        super(mana, classType, cardDetail, name);
        this.attack = attack;
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getRarityId() {
        return rarityId;
    }

    public void setRarityId(int rarityId) {
        this.rarityId = rarityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CardText cardText = (CardText) o;

        if (attack != cardText.attack) return false;
        if (health != cardText.health) return false;
        return rarityId == cardText.rarityId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + attack;
        result = 31 * result + health;
        result = 31 * result + rarityId;
        return result;
    }
}
