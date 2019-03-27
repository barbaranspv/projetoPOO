/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Horario
 * @author barba
 */
public class Horario implements Serializable{
    private String diasUteis;
    private String fimDeSem;
    
    /**
     *construtor
     * @param diasUteis
     * @param fimDeSem
     */
    public Horario(String diasUteis, String fimDeSem){
        this.diasUteis=diasUteis;
        this.fimDeSem=fimDeSem;
       
}
public String toString(){
    return "Dias Uteís: "+ diasUteis+ " Fim de semana: "+ fimDeSem ;
}
}
