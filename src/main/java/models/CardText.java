package models;

/**
 * Created by Guest on 8/25/17.
 */
public class CardText extends Characteristics {
    private String classType;
    private String cardDetail;
    private String name;

//    public CardText(int mana, String classType, String cardDetail, String name) {
//        super(mana);
//        this.classType = classType;
//        this.cardDetail= cardDetail;
//        this.name=name;
//    }

    public CardText(int attack, int health, int mana, String classType, String cardDetail, String name) {
        super(attack, health, mana);
        this.classType= classType;
        this.cardDetail= cardDetail;
        this.name= name;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getCardDetail() {
        return cardDetail;
    }

    public void setCardDetail(String cardDetail) {
        this.cardDetail = cardDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CardText cardText = (CardText) o;

        if (classType != null ? !classType.equals(cardText.classType) : cardText.classType != null) return false;
        if (cardDetail != null ? !cardDetail.equals(cardText.cardDetail) : cardText.cardDetail != null) return false;
        return name != null ? name.equals(cardText.name) : cardText.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (classType != null ? classType.hashCode() : 0);
        result = 31 * result + (cardDetail != null ? cardDetail.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
