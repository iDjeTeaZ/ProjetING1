package com.cours.dao;


import com.cours.entities.salle;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalleImpl implements IinitDb {

    private DBprojet  db = new DBprojet();
    private  int ok;
    private ResultSet rs;

    @Override
    public int add(salle s) {
//initialisation de la requette
        String sql="INSERT INTO salle VALUES (?, ?, ?, ?,?)";
        ok=0;
//check requette  et passage des valeurs
        try {
            db.initPrepare(sql);
            db.getPstm().setString(1,s.getNumero());
            db.getPstm().setInt(2,s.getDescription());
            db.getPstm().setInt(3,s.getCordX());
            db.getPstm().setInt(4,s.getcordY());
            db.getPstm().setInt(5,s.status());
             
  //execution de la requette          

            ok= db.executeMaj();

        }catch (Exception ex){
 //gestion des execption
            ex.printStackTrace();
        }


        return ok;
    }
//mise ajour de la classe salle
    @Override
    public int update(salle s) {
        String sql="UPDATE salle SET numero= ?,description = ?, cordX= ?,cordY=? WHERE numero = ?";
        ok=0;

        try {
            db.initPrepare(sql);
            db.getPstm().setString(1,s.getNumero());
            db.getPstm().setInt(2,s.getDescription());
            db.getPstm().setInt(3,s.getCordX());
            db.getPstm().setInt(4,s.getcordY());
        
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ok;
    }
//suppresion d'une salle
    @Override
    public int delete(int numero) {

        String sql=" DELETE FROM salle WHERE numero= ?";
        ok=0;

        try {
            db.initPrepare(sql);
            db.getPstm().setInt(1,numero);
            ok=db.executeMaj();

        }catch (Exception e){

            e.printStackTrace();
        }

        return ok;
    }

    @Override
    public List<Salle> list() {
        List<Salle> salle = new ArrayList<>();
        String sql ="SELECT * FROM  salle";

        try {
            db.initPrepare(sql);
            rs  = db.ExecuteSELECT();

            while (rs.next()){
               Salle salle1 =  new Salle();
                salle1.setNumero(rs.getInt(1));
                salle1.setDescription(rs.getString(2));
                salle1.setCordX(rs.getInt(3));
                salle1.setCordY(rs.getInt(4));
                salle1.setStatus(rs.getInt(5));
               

                salle.add(salle1);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }

        return salle;
    }

   


}
