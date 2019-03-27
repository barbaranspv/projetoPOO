/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Classe para Pontos de interesse
 * @author barba
 */
public abstract class PontoInteresse implements Serializable , Comparable<PontoInteresse>{

    /**
     *nome do ponto
     */
    protected String nomePonto;

    /**
     *tipo de ponto de interesse
     */
    protected String tipo;

    /**
     *horario
     */
    protected Horario horario;

    /**
     *preferencia
     */
    protected int prefPonto;

    /**
     *custo total
     */
    protected float custoTotal;

    /**
     *local onde se encontra
     */
    protected Local local;
    
    /**
     *construtor
     */
    public PontoInteresse(){}
    
     //METODOS DE ACESSO

    /**
     *coloca local
     * @param local
     */
    public void setLocal(Local local){
        this.local=local;
    } 

    /**
     *retorna local
     * @return
     */
    public Local getLocal(){
        return local;
    } 
    
    /**
     *coloca nome
     * @param nomePonto
     */
    public void setNomePonto(String nomePonto){
        this.nomePonto=nomePonto;
    } 

    /**
     *retorna nome
     * @return
     */
    public String getNomePonto(){
        return nomePonto;     
    } 
    
    /**
     *coloca horario
     * @param horario
     */
    public void setHorario(Horario horario){
        this.horario=horario;
    } 
    
    /**
     *retorna horario
     * @return
     */
    public Horario getHorario(){
        return horario;
    } 
    
    /**
     *retorna preferencia ponto
     * @return
     */
    public int getPrefPonto(){
        return prefPonto;
     } 
    
    /**
     *coloca preferencia
     * @param prefPonto
     */
    public void setPrefPonto(int prefPonto){
        this.prefPonto=prefPonto;}
    
    /**
     *aumenta preferencia
     */
    protected void aumentaPreferencia(){
        this.prefPonto+=1;
    }
    
    /**
     *retorna tipo
     * @return
     */
    public String getTipo(){
       return tipo;
   }

    /**
     *coloca tipo
     * @param tipo
     */
    public void setTipo(String tipo){
       this.tipo=tipo;
   }

    /**
     *calcula custo total
     * @return
     */
    public abstract float calculaTotal();
 
    
     @Override
    public int compareTo(PontoInteresse otherObject)
    {
        PontoInteresse ponto = ( PontoInteresse)otherObject;
         
        if (calculaTotal() < ponto.calculaTotal())
        {
            return -1;
        }
       
        if (calculaTotal() > ponto.calculaTotal())
        {   
            return 1;
        }
       
        return 0;
    }
    
}
