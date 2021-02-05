package com.cours.dao;

import java.sql.*;


public class DBprojet {
//Pour la connexion a la base de donnée
    private Connection cnx;
    //recuperation des requetes select
    private ResultSet rs;
    //preparation des requetes et executions des requetes sql
    private PreparedStatement pstm;
    //récupère les requetes de type INSERT,UPDATE,DELETE
    private int ok;


    public void getConextion() {
        String url = "jdbc:mysql://localhost:3306/projets2";
        String user = "dkd";
        String password = "patrick1997";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url,user,password);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//initialise la requette préparé  sql
    public void initPrepare(String sql){
        getConextion();
        /**
         * try {
         if(sql.toLowerCase().startsWith("insert")){
         //recuperé l'id autio increment
         pstm=cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

         }else{
         pstm=cnx.prepareStatement(sql);
         }

         }catch (Exception e){
         e.printStackTrace();
         }
         *
         */

        try {

                pstm=cnx.prepareStatement(sql);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // la requette update ou  delete
    public int executeMaj(){

        try {
            ok=pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

        return ok;
    }
    //acces en mode consultation
    public  ResultSet ExecuteSELECT(){
        try {
            rs=pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  rs;
    }
//preparation de la requette
    public PreparedStatement getPstm() {

        return this.pstm;
    }

    public void Closeconnexion(){
        try {
            if(cnx!=null){
                cnx.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}