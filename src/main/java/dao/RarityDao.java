package dao;

import models.Rarity;

import java.util.List;


public interface RarityDao {
    //create
    void  add(Rarity rarity);
    //   void addRarityToFoodType(Rarity rarity, Foodtype foodtype);

    //get all
    List<Rarity> getAll();


    //find
    Rarity findById(int id);
    //    List<Rarity> findAllCardsByRarity(int rarityId);
    //update
//    void update(int id, int mana, int attack, int health);

    //delete
    void deleteById(int id);

}

