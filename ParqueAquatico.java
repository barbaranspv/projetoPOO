/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Parque aquatico
 * @author barba
 */
public class ParqueAquatico extends ParqueDiversoes implements Serializable{
    public ParqueAquatico(){}
    public String toString(){   
        return "---> NOME DO PARQUE AQUÁTICO: "+ nomePonto+"\nLOCALIZAÇÃO: "+local.getNomeCidade() +"\nHORÁRIO: "+ horario+ "\nPREFERÊNCIA: "+ prefPonto+ "\nCUSTO DE ENTRADA: "+custoEntrada +"€\nDESPESAS DENTRO DO PARQUE:"+despesa+ "€\nDESPESAS TOTAIS NO PARQUE:"+ custoTotal+ "€\nATRAÇÕES: " + atracoes +"\n----------------------------------------------------------------------------------------------------------------------------------------------\n";
   }
    
}
