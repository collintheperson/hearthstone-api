package dao;

import models.CardText;

import models.Rarity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;


public class Sql2oCardTextDao implements CardTextDao {
    private final Sql2o  sql2o;
    public Sql2oCardTextDao(Sql2o sql2o)    {
        this.sql2o=sql2o;}

    @Override
    public void add (CardText cardText) {
        String sql = "INSERT INTO cardtext ( attack, health, mana, classtype,name,carddetail) VALUES (:attack, :health, :mana, :classType, :name, :cardDetail)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("attack", cardText.getAttack())
                    .addParameter("health", cardText.getHealth())
                    .addParameter("mana", cardText.getMana())
                    .addParameter("classType", cardText.getClassType())
                    .addParameter("name", cardText.getName())
                    .addParameter("cardDetail", cardText.getCardDetail())
                    .addColumnMapping("ATTACK","attack")
                    .addColumnMapping("HEALTH","health")
                    .addColumnMapping("MANA","mana")
                    .addColumnMapping("CLASSTYPE", "classtype")
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("CARDDETAIL","cardDetail")
                    .executeUpdate()
                    .getKey();
                 cardText.setId(id);
        }   catch (Sql2oException ex)  {
            System.out.println(ex);
        }
    }

    @Override
    public List<CardText> getAll() {
        try ( Connection con = sql2o.open())    {
            return con.createQuery("SELECT * FROM cardtext")
                    .executeAndFetch(CardText.class);
        }
    }
    @Override
    public CardText findById(int id) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM cardtext WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(CardText.class);
        }
    }
    @Override
    public void update (int attack, int health, int mana, int id) {
        String sql = "UPDATE cardtext SET( attack, health, mana) = (:attack, :health, :mana) WHERE id=:id";
        try (Connection con = sql2o.open()) {
             con.createQuery(sql)
                    .addParameter("attack", attack)
                    .addParameter("health", health)
                    .addParameter("mana", mana)
                    .addParameter("id",id)
                    .executeUpdate();
        }   catch (Sql2oException ex)  {
            System.out.println(ex);
        }
    }
    @Override
    public void deleteById(int id)  {
        try ( Connection con = sql2o.open())    {
            con.createQuery("DELETE FROM cardtext WHERE id = :id")
                    .addParameter("id",id)
                    .executeUpdate();
        }   catch (Sql2oException ex)   {
            System.out.println(ex);
        }
    }
//    @Override
//    public void addCardTextToRarity(CardText cardtext, Rarity rarity);{
////do stuff here.
//    }
//
//    @Override
//    List<CardText> findAllRaritiesByCardText(int cardTextId);
//        List<CardText> cardTexts = new ArrayList<>();
//        String query = "SELECT cardtextid FROM cardtext_rarity WHERE rarityid = :rarityid";
//        try(Connection con =sql2o.open()){
//            List<Integer> allCardTextId = con.createQuery(query)
//                    .addParameter("cardTexts", cardTexts)
//                    .executeAndFetch(Integer.class);
//            for (Integer cardTextsId : allCardTextId) {
//                String query2 = "SELECT * from cardtext WHERE cardtextid = :cardtextid";
//                cardTexts.add(
//                        con.createQuery(query2)
//                                .addParameter("cardTextId", rarity)
//                                .executeAndFetchFirst(Rarity.class));
//            }
//        }catch(Sql2oException e) {
//            System.out.println(e);
//        }
//        return cardTexts;
//    }
@Override
public List<Rarity> getAllRaritysForCards(int rarityid) {
    ArrayList<Rarity>  rarityTypes = new ArrayList<>();

    String joinQuery = "SELECT rarityid FROM cardtext_rarity WHERE cardtextid = :cardTextId";

    try (Connection con = sql2o.open()) {
        List<Integer> allRarityIds = con.createQuery(joinQuery)
                .addParameter("rarityid", rarityid)
                .executeAndFetch(Integer.class);
        for (Integer rarityId : allRarityIds){
            String Query = "SELECT * FROM rarity WHERE rarityid = :rarityid";
            rarityTypes.add(
                    con.createQuery(Query)
                            .addParameter("rarityId", rarityId)
                            .executeAndFetchFirst(Rarity.class));
        }
    } catch (Sql2oException ex){
        System.out.println(ex);
    }
    return rarityTypes;
}
}
