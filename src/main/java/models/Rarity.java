package models;

/**
 * Created by Guest on 8/25/17.
 */
public class Rarity extends Characteristics {
    private String typeRarity;
    private int cardTextId;
    public Rarity(int mana, String classType, String cardDetail, String name, String typeRarity) {
        super(mana, classType, cardDetail, name);
        this.typeRarity=  typeRarity;
    }

    public String getTypeRarity() {
        return typeRarity;
    }

    public void setTypeRarity(String typeRarity) {
        this.typeRarity = typeRarity;
    }

    public int getCardTextId() {
        return cardTextId;
    }

    public void setCardTextId(int cardTextId) {
        this.cardTextId = cardTextId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Rarity rarity = (Rarity) o;

        if (cardTextId != rarity.cardTextId) return false;
        return typeRarity.equals(rarity.typeRarity);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + typeRarity.hashCode();
        result = 31 * result + cardTextId;
        return result;
    }
}
