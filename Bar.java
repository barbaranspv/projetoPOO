/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Bar
 * @author barba
 */
public class Bar extends PontoInteresse implements Serializable{
    private float classificacao;
    private float despesa;
    
    /**
     *construtor 
     */
    public Bar(){
    }
    
    @Override
    public float calculaTotal(){
        super.custoTotal=despesa;
        return custoTotal;
        
    }
   //METODOS DE ACESSO

    /**
     *retorna despesa media no bar
     * @return
     */
   public float getDespesa(){
       return despesa;
   }
   
    /**
     *coloca despesa media no bar
     * @param despesa
     */
    public void setDespesa(float despesa){
       this.despesa=despesa;
   }
   
    /**
     *coloca classificacao media no bar
     * @param classificacao
     */
    public void setClassificacao(int classificacao){
       this.classificacao=classificacao;
   }
   
    /**
     *retorna classificacao
     * @return
     */
    public float getClassificacao(){
       return classificacao;
             
   }
   
   
   public String toString(){
       return "---> NOME DO BAR: "+ nomePonto+"\nLOCALIZAÇÃO: "+local.getNomeCidade() +"\nHORÁRIO: "+ horario+ "\nPREFERÊNCIA: "+ prefPonto+ "\nDESPESA TOTAL NO BAR: "+ custoTotal+ "€\nCLASSIFICAÇÃO: " + classificacao + "\n----------------------------------------------------------------------------------------------------------------------------------------------\n";
   }
}
