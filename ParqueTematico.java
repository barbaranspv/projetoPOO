/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Parque tematico
 * @author barba
 */
public class ParqueTematico  extends ParqueDiversoes implements Serializable{
    public ParqueTematico(){}
    
    public String toString(){   
        return "---> NOME DO PARQUE TEMÁTICO: "+ nomePonto+"\nLOCALIZAÇÃO: "+local.getNomeCidade() +"\nHORÁRIO: "+ horario+ "\nPREFERÊNCIA: "+ prefPonto+ "\nCUSTO DE ENTRADA: "+custoEntrada +"€\nDESPESAS DENTRO DO PARQUE:"+despesa+ "€\nDESPESAS TOTAIS NO PARQUE:"+ custoTotal+ "€\nATRAÇÕES: " + atracoes +"\n----------------------------------------------------------------------------------------------------------------------------------------------\n";
   }
}
