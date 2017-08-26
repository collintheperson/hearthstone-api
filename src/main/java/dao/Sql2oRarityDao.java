package dao;

import models.Characteristics;
import models.Rarity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
        String sql = "INSERT INTO rarity ( mana, classtype, name, carddetail, typerarity) VALUES (:attack, :health, :mana, :classType, :name, :cardDetail, typeRarity)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("mana", rarity.getMana())
                    .addParameter("classType", rarity.getClassType())
                    .addParameter("name", rarity.getName())
                    .addParameter("cardDetail", rarity.getCardDetail())
                    .addParameter("typeRarity",rarity.getTypeRarity())
                    .addColumnMapping("MANA","mana")
                    .addColumnMapping("CLASSTYPE", "classtype")
                    .addColumnMapping("NAME", "name")
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
    public void update ( int mana, String cardDetail, int id) {
        String sql = "UPDATE rarity SET( mana, carddetail) = (:mana, :cardDetail) WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("mana", mana)
                    .addParameter("carddetail",cardDetail)
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
}

}
