/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Parque de diversoes
 * @author barba
 */
public class ParqueDiversoes  extends Parque implements Serializable{
    protected ArrayList<String> atracoes= new ArrayList();
    
    /**
     *construtor
     */
    public ParqueDiversoes(){
    }
    
    /**
     *coloca atracao
     * @param atracao
     */
    public void setAtracao(String atracao){
        atracoes.add(atracao);
    }
    
    /**
     *retorna atracoes do parque
     * @return
     */
    public ArrayList<String>  getAtracoes(){
        return atracoes;
    }
    
    }
