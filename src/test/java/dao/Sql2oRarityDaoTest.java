package dao;

import models.CardText;
import models.Rarity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/28/17.
 */
public class Sql2oRarityDaoTest {

    private Sql2oRarityDao rarityDao;
    private Sql2oCardTextDao cardTextDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        rarityDao = new Sql2oRarityDao(sql2o);
        cardTextDao= new Sql2oCardTextDao(sql2o);
        conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }


    @Test
        public void add_InstantiatesANewSpell()  throws Exception    {
            Rarity spell = setUpNewSpell();
            assertTrue(spell instanceof Rarity);
    }

    @Test
    public void getAll_RetrieveAllRarities()   throws Exception    {
        Rarity rare = setUpNewSpell();
        Rarity spell = new Rarity(6,"b","c","d","legendary");
        rarityDao.add(rare);
        rarityDao.add(spell);
        assertEquals(2,rarityDao.getAll().size());
    }
    @Test
    public void findById_FindASpecificRarity_FishWitch() throws Exception {
        Rarity test1 = setUpNewSpell();
        Rarity test2 = new Rarity (3,"Warlock","Destroy your other minions and gain their stats","Corruptor the Undoer","legendary");
        rarityDao.add(test1);
        rarityDao.add(test2);
        Rarity foundRarity = rarityDao.findById(2);
        assertEquals("Corruptor the Undoer",foundRarity.getName());
    }
    @Test
    public void update_EvaluateIfManaCostChanged() throws Exception {
        Rarity test1 = setUpNewSpell();
        rarityDao.add(test1);
        rarityDao.update(1,2,"Throws 4 fireballs at 3 minions");
        Rarity foundRarity = rarityDao.findById(1);
        assertEquals("Throws 4 fireballs at 3 minions", foundRarity.getCardDetail());
    }

    @Test
    public void delete_SeeIfACardIsDeleted()    throws Exception    {
        Rarity test1 = setUpNewSpell();
        rarityDao.add(test1);
        int idToDelete = test1.getId();
        rarityDao.deleteById(idToDelete);
        assertEquals(0,rarityDao.getAll().size());
    }

    @Test
    public void addRarityToCardTextAddsTypeCorrectly() throws Exception {

        CardText testCard1 = new CardText(2,"a","b","b",5,4);
        CardText testCard2 = new CardText(3,"b","f","g",4,2);

        cardTextDao.add(testCard1);
        cardTextDao.add(testCard2);

        Rarity testRarity = setUpNewSpell();

        rarityDao.add(testRarity);

        rarityDao.addRarityToCardText(testRarity, testCard1);
        rarityDao.addRarityToCardText(testRarity, testCard2);

        assertEquals(2, rarityDao.getAllCardTextsForARarity(testRarity.getId()).size());
    }


    //helper method
    public static Rarity setUpNewSpell()  {
        return new Rarity(4,"Priest","Gives all minions -3 attack","Pint-sized potion","rare");
    }


}