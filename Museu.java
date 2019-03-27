/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Museu
 * @author barba
 */
public class Museu extends PontoInteresse implements Serializable{
    private float custoEntrada;
    private String descricao;
    
     @Override
    public float calculaTotal(){
        super.custoTotal=custoEntrada;
        return custoTotal;
        
    }
   //METODOS DE ACESSO

    /**
     *retorna custo de entrada
     * @return
     */
   public float getCustoEntrada(){
       return custoEntrada;
   }
   
    /**
     *coloca custo de entrada
     * @param custoEntrada
     */
    public void setCustoEntrada(float custoEntrada){
       this.custoEntrada=custoEntrada;
   }

    /**
     *coloca descricao 
     * @param descricao
     */
    public void setDescricao(String descricao){
       this.descricao=descricao;
   }
    @Override
   public String toString(){
       return  "---> NOME DO MUSEU: "+ nomePonto+"\nDESCRIÇÃO: "+descricao+ "\nLOCALIZAÇÃO: "+local.getNomeCidade() +"\nHORÁRIO: "+ horario+ "\nPREFERÊNCIA: "+ prefPonto+ "\nCUSTO TOTAL DO MUSEU: "+ custoTotal+ "€\n----------------------------------------------------------------------------------------------------------------------------------------------\n";
   }
}
