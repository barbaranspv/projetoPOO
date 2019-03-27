/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Aluno de licenciatura
 * @author barba
 */
public class AlunoLicenciatura extends Aluno implements Serializable {
    private PontoInteresse pontoHot= new PontoInteresse() {
        @Override
        public float calculaTotal() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    /**
     *Construtor
     */
    public AlunoLicenciatura(){}
    
    /**
     *Construtor
     * @param nome
     * @param numAluno
     * @param idade
     * @param orcamento
     */
    public AlunoLicenciatura(String nome,int numAluno,int idade,float orcamento){
        this.nome=nome;
        this.idade=idade;
        this.numAluno=numAluno;
        this.orcamento=orcamento;
       
    }
    
    
    //METODOS DE ACESSO

    /**
     *Coloca ponto hot se e aluno de licenciatura
     * @param pontoHot
     */
     
   @Override
     public void setHot(PontoInteresse pontoHot){
        this.pontoHot=pontoHot;
        this.pontoHot.aumentaPreferencia();
        setPontoPreferido(pontoHot);
        
 } 

    /**
     *Retorna ponto hot
     * @return
     */
    @Override
    public PontoInteresse getHot(){
        
        return pontoHot;
 }  
    




    
}
