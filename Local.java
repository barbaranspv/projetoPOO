/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Classe para locais
 * @author barba
 */
public class Local implements Serializable, Comparable <Local>{

    /**
     *nome da cidade (Local)
     */
    protected String nomeCidade;

    /**
     *distancia a coimbra
     */
    protected float distancia;

    /**
     *preferencia do local
     */
    protected int prefLocal;

    /**
     *custo do local
     */
    protected float custoLocal;
    ArrayList<PontoInteresse> pontosLocal= new ArrayList();
    
    /**
     *construtor
     */
    public Local(){}
   
    /**
     *contrutor
     * @param nomeCidade
     * @param distancia
     * @param prefLocal
     * @param pontosLocal
     */
    public Local(String nomeCidade,float distancia,int prefLocal, ArrayList<PontoInteresse> pontosLocal ){
         this.nomeCidade=nomeCidade;
         this.distancia=distancia;
         this.prefLocal=prefLocal;
         this.pontosLocal=pontosLocal;
    }
    
    
     //METODOS DE ACESSO

    /**
     *coloca nome
     * @param nomeCidade
     */
    public void setNomeCidade(String nomeCidade){
        this.nomeCidade=nomeCidade;
    } 

    /**
     *retorna nome
     * @return
     */
    public String getNomeCidade(){
        return nomeCidade;
    } 
    
    /**
     *coloca distancia
     * @param distancia
     */
    public void setDistancia(float distancia){
        this.distancia=distancia;
    }
    
    /**
     *retorna cidade
     * @return
     */
    public float getDistancia(){
        return distancia;
    }
    
    /**
     *retorna preferencia do local
     * @return
     */
    public int getPrefLocal(){
        return prefLocal;
     } 
    
    /**
     *coloca preferencia
     * @param prefLocal
     */
    public void setPrefLocal(int prefLocal){
        this.prefLocal=prefLocal;
    }
    
    /**
     *retorna custo total
     * @return
     */
    public float getCustoLocal(){
       return custoLocal;
   }
 
    /**
     *calcula custo total
     */
    public void setCustoLocal(){
       for (int i=0;i<pontosLocal.size();i++){
          custoLocal+=pontosLocal.get(i).custoTotal;   
       }
    }
    
    /**
     *
     * @return
     */
    public ArrayList<PontoInteresse> getPontosInteresseLocais(){
        return pontosLocal;
    }
    
    /**
     *coloca ponto de interesse do local
     * @param ponto
     */
    public void setPontoInteresseLocal(PontoInteresse ponto){
        pontosLocal.add(ponto);
    }

    /**
     *coloca pontos de interesse do local
     * @param pontos
     */
    public void setPontosInteresseLocal(ArrayList<PontoInteresse> pontos){
        for (int i=0;i < pontos.size();i++){
            pontosLocal.add(pontos.get(i));
        }}
    


    /**
     *aumenta preferencia do local
     */
    protected void aumentaPreferencia(){
        prefLocal+=1;
    }
    
    /**
     *diminui preferencia do local
     */
    protected void diminuiPreferencia(){
        prefLocal-=1;
    }

    
    @Override
     public String toString(){
        String info= "---> NOME DO LOCAL: "+nomeCidade+"\nDISTÂNCIA A COIMBRA: "+distancia+ "Km\nPREFERÊNCIA DO LOCAL: "+ prefLocal+ "\nCUSTO MÉDIO DO LOCAL: " + custoLocal + "€\nPONTOS DE INTERESSE NO LOCAL: ";
        for (int i=0; i<pontosLocal.size();i++){
            if (i!=pontosLocal.size()-1)
                info+=pontosLocal.get(i).nomePonto +", ";
            else
                info+=pontosLocal.get(i).nomePonto +" ";
                
    }   info+="\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        return info;
       
    }
     
  

       @Override
    public int compareTo(Local otherObject)
    {
        Local local = ( Local)otherObject;
         
        if (getCustoLocal() < local.getCustoLocal())
        {
            return -1;
        }
       
        if (getCustoLocal() > local.getCustoLocal())
        {   
            return 1;
        }
       
        return 0;
    }
    
        
}



