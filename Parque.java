/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Parque
 * @author barba
 */
public class Parque extends PontoInteresse implements Serializable{

    /**
     *despesa no parque
     */
    protected float despesa;

    /**
     *custo de entrada
     */
    protected float custoEntrada;
    
    /**
     *construtor
     */
    public Parque(){}
    
    @Override
   public float calculaTotal(){
       super.custoTotal=despesa + custoEntrada;
       return custoTotal;
   }
   
   //METODOS DE ACESSO

    /**
     *retorna despesa no parque
     * @return
     */
   public float getDespesa(){
       return despesa;
   }
   
    /**
     *coloca despesa
     * @param despesa
     */
    public void setDespesa(float despesa){
       this.despesa=despesa;
   }
   
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
   
}
