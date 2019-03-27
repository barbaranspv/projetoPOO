/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Viagem
 * @author barba
 */
public class Viagem implements Serializable ,Comparable{

    /**
     *Variável para calcular distancia percorrida na viagem
     */
    protected float deslocamento;

    /**
     *Variavel para calcula despesas totais da viagem
     */
    protected float despesasTotais;

    /**
     *Array com os locais a visitar na viagem
     */
    protected ArrayList<Local> locaisAVisitar= new ArrayList();
    
    /**
     *Construtor
     */
    public Viagem(){
        
    }

    /**
     *Coloca um local a visitar
     * @param local
     */
    public void setLocAVisitar(Local local){
        this.locaisAVisitar.add(local);
 } 
    
    /**
     *Devolve locais a visitar
     * @return
     */
    public  ArrayList<Local> getLocAVisitar(){
        return locaisAVisitar;
        
    } 

    /**
     *Calcula a estimativa da distancia total a percorrer
     * @return
     */
    public float calculaDeslocamento(){
        this.deslocamento=0;
        for (int i=0; i<locaisAVisitar.size();i++){
            if (i==0){
                this.deslocamento+=locaisAVisitar.get(i).getDistancia();
            }
            else if (i==1){
                this.deslocamento+= locaisAVisitar.get(i).getDistancia()/3;
                
            }
            else if (i==2){
                this.deslocamento+= locaisAVisitar.get(1).getDistancia()/3 + locaisAVisitar.get(i).getDistancia();
            }
            
        }
        return deslocamento;
        
    }
    
    /**
     *Calcula estimativa das dispesas totais
     * @return
     */
    public float despesasTotais(){
        this.despesasTotais=0;
        calculaDeslocamento();
        for (int j=0; j<locaisAVisitar.size();j++){
            for (int i=0;i<locaisAVisitar.get(j).getPontosInteresseLocais().size();i++){
                this.despesasTotais+=locaisAVisitar.get(j).getPontosInteresseLocais().get(i).calculaTotal();

            }
        }
        this.despesasTotais+=deslocamento*0.2;
        return despesasTotais;
    }
    public String toString(){
        return "\nVIAGEM ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\nDISTANCIA A PERCORRER: "+ deslocamento+ "Km\nDESPESAS: "+ despesasTotais +"€\nLOCAIS A VISITAR:\n"+ locaisAVisitar +"\n-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.\n";
    }
    
    @Override
    public int compareTo(Object otherObject)
    {
        Viagem viagem = ( Viagem)otherObject;
         
        if (despesasTotais() < viagem.despesasTotais())
        {
            return -1;
        }
       
        if (despesasTotais() > viagem.despesasTotais())
        {   
            return 1;
        }
       
        return 0;
    }
    
}
