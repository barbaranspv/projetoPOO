/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Classe Aluno
 * 
 * @author barba
 */
public class Aluno implements Serializable{

    /**
     *Nome utilizados
     */
    protected String nome;

    /**
     *Numero de aluno
     */
    protected int numAluno;

    /**
     *Idade
     */
    protected int idade;

    /**
     *Orcamento
     */
    protected float orcamento;

    /**
     *Viagem escolhida
     */
    protected Viagem viagem;
    
    protected ArrayList<Local> locaisPreferidos= new ArrayList();
    protected ArrayList<PontoInteresse> pontosPreferidos= new ArrayList();
    protected ArrayList <Viagem> viagensPessoais= new ArrayList();
 
    /**
     *Construtor
     */
    public Aluno(){}
 
 
 
 
 //METODOS DE ACESSO

    /**
     *Coloca nome
     * @param nome
     */
 public void setNome(String nome){
     this.nome=nome;
 } 
 
    /**
     *Retorna nome
     * @return
     */
    public String getNome(){
     return nome;
 } 
 
    /**
     *Coloca idade
     * @param numAluno
     */
    public void setNumAluno(int numAluno){
     this.numAluno=numAluno;
 } 

    /**
     *Retorna idade
     * @return
     */
    public int getNumAluno(){
     return numAluno;
 } 
 
    /**
     *Coloca idade
     * @param idade
     */
    public void setIdade(int idade){
     this.idade=idade;
 } 
 
    /**
     *Retorna idade
     * @return
     */
    public int getIdade(){
     return idade;
 } 
 
    /**
     *Coloca orcamento
     * @param orcamento
     */
    public void setOrcamento(float orcamento){
     this.orcamento=orcamento;
 } 
 
    /**
     *Retorna orcamento
     * @return
     */
    public float getOrcamento(){
     return orcamento;
 } 
 
    /**
     *Atribui viagem 
     * @param viagem
     * @return
     */
    public int setViagem(Viagem viagem){
        int temp=0;
        if (viagensPessoais.isEmpty()){
            this.viagensPessoais.add(viagem);
            temp=1;
        }
        else{
            for (int i=0;i<viagensPessoais.size();i++){
                 if (viagensPessoais.get(i)==viagem){
                     temp=0;
                     break;
                 }
                 else if ((i==viagensPessoais.size()-1) && viagensPessoais.get(i)!=viagem){
                     this.viagensPessoais.add(viagem);
                     temp=1;
                     break;
                 }
        }
        }
         this.viagem=viagem;
         return temp;
     } 
 
    /**
     *Retorna Viagrm
     * @return
     */
    public Viagem getViagem(){
     return viagem;
 } 
 
 public int setLocPreferido(Local local){
        if (locaisPreferidos.isEmpty()){
           local.aumentaPreferencia();
           locaisPreferidos.add(local);
           return 0;
        }
        else{
            for (int j=0; j< locaisPreferidos.size();j++){
                            if ((local== locaisPreferidos.get(j))){
                                            break;
                                        }
                            else if ((j==locaisPreferidos.size()-1) && (local!= locaisPreferidos.get(j) )){
                                    local.aumentaPreferencia();
                                    locaisPreferidos.add(local);
                                    return 0;

                                    }
                        }
            return 1;
        }
        
        
 } 
    /**
     *Coloca varios locais como preferidos
     * @param locais
     */
    public void setLocaisPreferidos(ArrayList<Local> locais){
        for (int i=0;i < locais.size();i++){
            locaisPreferidos.add(locais.get(i));
        }}
 
    /**
     *Retorna locais preferidos
     * @return
     */
    public ArrayList<Local> getLocPreferidos(){
     return locaisPreferidos;
 } 
 
    /**
     *Devolve pontos preferidos
     * @return
     */
    public ArrayList<PontoInteresse> getPontosPreferidos(){
     return pontosPreferidos;
 } 
  
    /**
     *Devolve viagens geradas para a pessoa
     * @return
     */
    public ArrayList <Viagem> getViagensPessoais(){
      return viagensPessoais;
      
  }
  
   
    public PontoInteresse getHot(){
        return null;
    
}

   
    public void setHot(PontoInteresse ponto){
    
}

    public Local getNot(){
    return null;
}

  
    public void setNot(Local local){
    
}

    /**
     *Coloca ponto preferido
     * @param ponto
     */
    public void setPontoPreferido(PontoInteresse ponto){
        ponto.aumentaPreferencia();
        if (locaisPreferidos.isEmpty())
           setLocPreferido(ponto.getLocal());
        else{
            for (int j=0; j< locaisPreferidos.size();j++){
                            if ((ponto.getLocal()== locaisPreferidos.get(j)) ){
                                            break;
                                        }
                            if ((j==locaisPreferidos.size()-1) && (ponto.getLocal()!= locaisPreferidos.get(j))){
                                           setLocPreferido(ponto.getLocal());

                                    }
                        }
            }
        (this.pontosPreferidos).add(ponto);
 }

}
