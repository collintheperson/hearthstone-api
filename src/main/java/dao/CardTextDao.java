package dao;


import models.CardText;
import models.Rarity;

import java.util.List;

public interface CardTextDao {
    //create
    void  add(CardText cardtext);

//    void addCardTextToRarity(CardText cardtext, Rarity rarity);

    //read
    List<CardText> getAll();

    List<Rarity> getAllRaritysForCards(int rarityid);
    //find
    CardText findById(int id);

    //update
   void update(int id, int mana, int attack, int health);

    //delete
    void deleteById(int id);
}
