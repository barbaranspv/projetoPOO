/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Universidade
 * @author barba
 */
public class Universidade extends PontoInteresse implements Serializable {
    protected ArrayList<Curso> cursos= new ArrayList();
   
    /**
     *construtor
     */
    public Universidade() {}
    
    ////FUNÇÕES
    @Override
    public float calculaTotal(){
        super.custoTotal=0;
        return 0;}    
    
    //CONSTRUTOR

    /**
     *construtor
     * @param nomePonto
     * @param horario
     * @param prefPonto
     */
    public Universidade(String nomePonto,Horario horario,int prefPonto){
        this.nomePonto=nomePonto;
        this.horario=horario;
        this.prefPonto=prefPonto;
        this.custoTotal=0;
    }
    
    //METODOS DE ACESSO

    /**
     *retorna cursos da universidade
     * @return
     */
    public ArrayList<Curso> getCursos(){
        return cursos;
    }
    
    /**
     *coloca curso
     * @param curso
     */
    public void setCurso(Curso curso){
        (this.cursos).add(curso);
    }
    
    
    @Override
    public String toString(){
       return  "---> NOME DA UNIVERSIDADE: "+ nomePonto+"\nLOCALIZAÇÃO: "+local.getNomeCidade() +"\nHORÁRIO: "+ horario+ "\nPREFERÊNCIA: "+ prefPonto+ "\n CURSOS RELACIONADOS COM INFORMÁTICA: "+ cursos + "\n----------------------------------------------------------------------------------------------------------------------------------------------\n";
   }
}
