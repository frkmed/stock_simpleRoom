package com.marwa.stocksimple.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface StockDao {


    @Insert(onConflict = REPLACE)
    void insert(Stock obj);

    @Insert
    void insertAll(List<Stock> objs);

    @Update
    void update(Stock obj);


    @Delete
    void delete(Stock obj);

    @Query("DELETE FROM stock")
    void deleteAll();

    @Query("SELECT * FROM stock")
    List<Stock> getAll();


    @Query("SELECT * FROM stock WHERE id=:id ORDER BY nomProduit")
    public Stock getById(long id);


    @Query("SELECT count(*) FROM stock WHERE id=:id ORDER BY nomProduit")
    public int getCount(long id);
}
