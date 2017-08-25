package models;

/**
 * Created by Guest on 8/25/17.
 */
public class Characteristics {
    private int mana;
    private int id;
    private String classType;
    private String cardDetail;
    private String name;

    public Characteristics(int mana, String classType, String cardDetail, String name) {
        this.mana = mana;
        this.classType = classType;
        this.cardDetail = cardDetail;
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public int getId() {
        return id;
    }

    public String getClassType() {
        return classType;
    }

    public String getCardDetail() {
        return cardDetail;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setCardDetail(String cardDetail) {
        this.cardDetail = cardDetail;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Characteristics that = (Characteristics) o;

        if (mana != that.mana) return false;
        if (id != that.id) return false;
        if (!classType.equals(that.classType)) return false;
        if (!cardDetail.equals(that.cardDetail)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = mana;
        result = 31 * result + id;
        result = 31 * result + classType.hashCode();
        result = 31 * result + cardDetail.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
