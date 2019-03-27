/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Parque cultural
 * @author barba
 */
public class ParqueCultural extends Parque implements Serializable{
    private ArrayList<String> vistas= new ArrayList();
    
    /**
     *construtor
     */
    public ParqueCultural(){
    }
    
    /**
     *colocar vista do parque
     * @param vista
     */
    public void setVista(String vista){
        vistas.add(vista);
    }
    
    /**
     *retorna vistas do parque
     * @return
     */
    public ArrayList<String>  getVistas(){
        return vistas;
    }
    
public String toString(){   
        return  "---> NOME DO PARQUE: "+ nomePonto+"\nLOCALIZAÇÃO: "+local.getNomeCidade() +"\nHORÁRIO: "+ horario+ "\nPREFERÊNCIA: "+ prefPonto+ "\nCUSTO DE ENTRADA: "+custoEntrada +"€\nDESPESAS DENTRO DO PARQUE:"+despesa+ "€\nDESPESAS TOTAIS NO PARQUE:"+ custoTotal+ "€\nVISTAS: " + vistas +"\n----------------------------------------------------------------------------------------------------------------------------------------------\n";
   }}