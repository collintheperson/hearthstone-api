package dao;

import models.CardText;
import models.Rarity;

import java.util.List;


public interface RarityDao {
    //create
    void  add(Rarity rarity);

    void addRarityToCardText(Rarity rarity, CardText cardtext);

    //read
    List<Rarity> getAll();

    List<Rarity> getAllCardTextsForARarity(int rarityid);

    //find
    Rarity findById(int id);

    //update
    void update(int id, int mana, String cardDetail);

    //delete
    void deleteById(int id);

}

