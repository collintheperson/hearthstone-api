package dao;

import models.CardText;
import models.Rarity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/25/17.
 */
public class Sql2oCardTextDaoTest {

    private Sql2oCardTextDao cardTextDao;
    private Sql2oRarityDao rarityDao;

    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        cardTextDao = new Sql2oCardTextDao(sql2o);
        rarityDao = new Sql2oRarityDao(sql2o);

        conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_InstantiatesANewCard()  throws Exception    {
        CardText card = setUpNewCard();
        assertTrue(card instanceof CardText);
    }
//    @Test
//    public void add_InstantiatesANewSpell() throws Exception    {
//        CardText spell = setUpNewSpell();
//        assertTrue(spell instanceof CardText);
//    }
    @Test
    public void getAll_RetrieveAllCards()   throws Exception    {
        CardText card = setUpNewCard();
        CardText spell = new CardText(6,"b","c","d",1,5);
        cardTextDao.add(card);
        cardTextDao.add(spell);
        assertEquals(2,cardTextDao.getAll().size());
    }
    @Test
    public void findById_FindASpecificCardText_FishWitch() throws Exception {
        CardText test1 = setUpNewCard();
        CardText test2 = new CardText (3,"Warlock","Destroy your other minions and gain their stats","Corruptor the Undoer",8,2);
        cardTextDao.add(test1);
        cardTextDao.add(test2);
        CardText foundCardText = cardTextDao.findById(2);
        assertEquals("Corruptor the Undoer",foundCardText.getName());
    }
    @Test
    public void update_EvaluateIfCardTextChanged() throws Exception {
        CardText test1 = setUpNewCard();
        cardTextDao.add(test1);
        cardTextDao.update(1,2,4,1);
        CardText foundCardText = cardTextDao.findById(1);
        assertEquals(1, foundCardText.getAttack());
    }
    @Test
    public void delete_SeeIfACardIsDeleted()    throws Exception    {
        CardText test1 = setUpNewCard();
        cardTextDao.add(test1);
        int idToDelete = test1.getId();
        cardTextDao.deleteById(idToDelete);
        assertEquals(0,cardTextDao.getAll().size());
    }

    @Test
    public void getAllRarityForACardTextReturnsRaritysCorrectly() throws Exception {
        Rarity testRarity  = new Rarity(1,"priest","its a beast","legsDude","epic");
        rarityDao.add(testRarity);

        Rarity otherRarity  = new Rarity(3,"warlock","gahhh","the armed pirate","common");
        rarityDao.add(otherRarity);

        CardText testCardText = setUpNewCard();
        cardTextDao.add(testCardText);
        cardTextDao.addCardTextToRarity(testCardText,testRarity);
        cardTextDao.addCardTextToRarity(testCardText,otherRarity);

        Rarity[] foodtypes = {testRarity, otherRarity};

        assertEquals(cardTextDao.getAllRaritysForACardText(testCardText.getId()), Arrays.asList(foodtypes));
    }



//    @Test
//    public void addRarityToCardTextAddsTypeCorrectly() throws Exception {
//
//        Rarity testCard1 = new Rarity(2,"a","b","b","legendary");
//        Rarity testCard2 = new Rarity(3,"b","f","g","epic");
//
//        rarityDao.add(testCard1);
//        rarityDao.add(testCard2);
//
//        CardText cardText1 = setUpNewCard();
//
//        cardTextDao.add(cardText1);
//
//        cardTextDao.addCardTextToRarity(cardText1, testCard1);
//        cardTextDao.addCardTextToRarity(cardText1, testCard2);
//
//        assertEquals(2, rarityDao.getAllCardTextsForARarity(cardText1.getId()).size());
////    }
    //helper methods
    public static CardText setUpNewCard ()  {
        return new CardText(2,"a","b","Labrapriest",2,5);
    }
//    public static CardText setUpNewSpell()  {
//        return new CardText(8,"Mage","Destroy all minions","Ragnarok");
//    }

}