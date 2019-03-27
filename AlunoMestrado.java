/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Classe Aluno mestrado
 * @author barba
 */
public class AlunoMestrado extends Aluno implements Serializable{
    private Local localNot= new Local();
    
    
   /* public AlunoMestrado(String nome,int numAluno,int idade,float orcamento, Viagem viagem, ArrayList<Local> locaisPreferidos, ArrayList<PontoInteresse> pontosPreferidos, PontoInteresse pontoHot){
        this.nome=nome;
        this.idade=idade;
        this.numAluno=numAluno;
        this.orcamento=orcamento;
        this.viagem=viagem;
        this.locaisPreferidos=locaisPreferidos;
        this.pontosPreferidos=pontosPreferidos;
        this.pontoHot=pontoHot;
       
    }*/

    /**
     *Construtor
     */

    public AlunoMestrado(){}
    
    /**
     *Construtor
     * @param nome
     * @param numAluno
     * @param idade
     * @param orcamento
     */
    public AlunoMestrado(String nome,int numAluno,int idade,float orcamento){
        this.nome=nome;
        this.idade=idade;
        this.numAluno=numAluno;
        this.orcamento=orcamento;

        
       
    }
    //METODOS DE ACESSO

    /**
     * Coloca local not se o aluno é de mestrado
     * @param localNot
     */
    @Override
    public void setNot(Local localNot){
        this.localNot=localNot;
        this.localNot.diminuiPreferencia();
 } 
    
    /**
     *Retorna local not
     * @return
     */
    @Override
    public Local getNot(){
        return localNot;
 }  
    
    @Override
    public String toString(){
        return nome + idade+ numAluno + orcamento + localNot;
    }
    
    /**
     *Coloca Ponto de interesse preferido
     * @param ponto
     */
    @Override
    public void setPontoPreferido(PontoInteresse ponto){
        ponto.aumentaPreferencia();
        if (locaisPreferidos.isEmpty() && (!ponto.getLocal().equals(localNot)))
           setLocPreferido(ponto.getLocal());
        else{
            for (int j=0; j< locaisPreferidos.size();j++){
                            if ((ponto.getLocal()== locaisPreferidos.get(j))|| (ponto.getLocal().equals(localNot))){
                                            break;
                                        }
                            else if ((j==locaisPreferidos.size()-1) && (ponto.getLocal()!= locaisPreferidos.get(j) && (!ponto.getLocal().equals(localNot)))){
                                    setLocPreferido(ponto.getLocal());

                                    }
                        }
            (this.pontosPreferidos).add(ponto);
        }
 }
    
    
    /**
     *Coloca local como preferido
     * @param local
     * @return 
     */
    @Override
    public int setLocPreferido(Local local){
        if (locaisPreferidos.isEmpty() && (!local.equals(localNot))){
           local.prefLocal+=1;
           locaisPreferidos.add(local);
           return 0;
        }
        else{
            for (int j=0; j< locaisPreferidos.size();j++){
                            if ((local== locaisPreferidos.get(j))|| (local.equals(localNot))){
                                            break;
                                        }
                            else if ((j==locaisPreferidos.size()-1) && (local!= locaisPreferidos.get(j) && (!local.equals(localNot)))){
                                    local.aumentaPreferencia();
                                    locaisPreferidos.add(local);
                                    return 0;

                                    }
                        }
            return 1;
        }
 } 
 

}
