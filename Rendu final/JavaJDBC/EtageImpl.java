package com.cours.dao;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EtageImpl implements IinitDb {

    private DBprojet  db = new DBprojet();
    private  int ok;
    private ResultSet rs;

    @Override
    public int add(Etage e) {
//initialisation de la requette
        String sql="INSERT INTO Etage VALUES (?, ?, ?, ?,?)";
        ok=0;
//check requette  et passage des valeurs
        try {
            db.initPrepare(sql);
            db.getPstm().setString(1,e.getNumero());
            db.getPstm().setInt(2,e.getnumeros());
            db.getPstm().setInt(3,e.nbsalle());
            
             
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
    public int update(Etage e) {
        String sql="UPDATE Etage SET numero= ?,numeros = ?, nbsalle= ?";
        ok=0;

        try {
            db.initPrepare(sql);
            db.getPstm().setString(1,e.getNumero());
            db.getPstm().setInt(2,e.getNumeros());
            db.getPstm().setInt(3,e.nbsalle());
        
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ok;
    }
//suppresion d'une salle
    @Override
    public int delete(int numero) {

        String sql=" DELETE FROM Etage WHERE numero= ?";
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
    public List<Etage> list() {
        List<etage> etage = new ArrayList<>();
        String sql ="SELECT * FROM  etage";

        try {
            db.initPrepare(sql);
            rs  = db.ExecuteSELECT();

            while (rs.next()){
               Etage etage1 =  new Etage();
                etage1.setNumero(rs.getInt(1));
                etage1.setNumeroS(rs.getInt(2));
                etage1.setNbsalle(rs.getInt(3));
                
               

                etage.add(etage1);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }

        return etage;
    }

   


}
