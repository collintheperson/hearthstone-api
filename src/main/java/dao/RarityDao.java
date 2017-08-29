package dao;

import models.CardText;
import models.Rarity;

import java.util.List;


public interface RarityDao {
    //create
    void  add(Rarity rarity);
//    void addRarityToCardText(Rarity rarity, CardText cardtext); // E

    //read
    List<Rarity> getAll();

    List<CardText> getAllCardTextsForARarity(int rarityId); //E we will implement this NOW :)


    //find
    Rarity findById(int id);

    //update
    void update(int id, int mana, String cardDetail);

    //delete
    void deleteById(int id);

}

