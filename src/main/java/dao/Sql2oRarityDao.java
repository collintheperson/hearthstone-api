package dao;

import models.CardText;
import models.Characteristics;
import models.Rarity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 8/25/17.
 */
public class Sql2oRarityDao implements RarityDao {
    private final Sql2o sql2o;
    public Sql2oRarityDao(Sql2o sql2o)    {
        this.sql2o=sql2o;}

    @Override
    public void add (Rarity rarity) {
        String sql = "INSERT INTO rarity ( mana, classtype, name, carddetail, typerarity) VALUES ( :mana, :classType, :name, :cardDetail, :typeRarity)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("mana", rarity.getMana())
                    .addParameter("name", rarity.getName())
                    .addParameter("classType", rarity.getClassType())
                    .addParameter("cardDetail", rarity.getCardDetail())
                    .addParameter("typeRarity",rarity.getTypeRarity())
                    .addColumnMapping("MANA","mana")
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("CLASSTYPE", "classType")
                    .addColumnMapping("CARDDETAIL","cardDetail")
                    .addColumnMapping("TYPERARITY","typeRarity")
                    .executeUpdate()
                    .getKey();
            rarity.setId(id);
        }   catch (Sql2oException ex)  {
            System.out.println(ex);
        }
    }

    @Override
    public List<Rarity> getAll() {
        try ( Connection con = sql2o.open())    {
            return con.createQuery("SELECT * FROM rarity")
                    .executeAndFetch(Rarity.class);
        }
    }
    @Override
    public Rarity findById(int id) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM rarity WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Rarity.class);
        }
    }
    @Override
    public void update (int id, int mana, String cardDetail ) {
        String sql = "UPDATE rarity SET( mana, carddetail) = (:mana, :cardDetail) WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("mana", mana)
                    .addParameter("cardDetail",cardDetail)
                    .addParameter("id",id)
                    .executeUpdate();
        }   catch (Sql2oException ex)  {
            System.out.println(ex);
        }
    }
    @Override
    public void deleteById(int id)  {
        try ( Connection con = sql2o.open())    {
            con.createQuery("DELETE FROM rarity WHERE id = :id")
                    .addParameter("id",id)
                    .executeUpdate();
        }   catch (Sql2oException ex)   {
            System.out.println(ex);
        }
    }
    @Override
        public void addRarityToCardText(Rarity rarity, CardText cardText){
            String query = "INSERT INTO cardtext_rarity(rarityId, cardTextId) VALUES (:rarityId, :cardTextId)";
            try(Connection con = sql2o.open()){
                con.createQuery(query)
                        .addParameter("rarityId", rarity.getId())
                        .addParameter("cardTextId", cardText.getId())
                        .executeUpdate();
            } catch (Sql2oException e){
                System.out.println(e);
            }
        }


       @Override
    public   List<Rarity> getAllCardTextsForARarity(int rarityid){
        List<Rarity> rarites = new ArrayList<>();
        String query = "SELECT rarityid FROM cardtext_rarity WHERE cardtextid = :cardtextid";
        try(Connection con =sql2o.open()){
            List<Integer> allRarityId = con.createQuery(query)
                    .addParameter("rarities", rarites)
                    .executeAndFetch(Integer.class);
            for (Integer cardTextId : allRarityId) {
                String query2 = "SELECT * from rarity WHERE rarityid = :rarityId";
                rarites.add(
                        con.createQuery(query2)
                                .addParameter("cardTextId", cardTextId)
                                .executeAndFetchFirst(Rarity.class));
            }
        }catch(Sql2oException e) {
            System.out.println(e);
        }
        return rarites;
    }
}


