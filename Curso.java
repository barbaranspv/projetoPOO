/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.Serializable;

/**
 *Curso
 * @author barba
 */
public class Curso implements Serializable{
    private String nomeCurso;
    
    /**
     *construtor
     * @param nomeCurso
     */
    public Curso(String nomeCurso){
        this.nomeCurso=nomeCurso;
    }
    
    
    //METODOS DE ACESSO

    /**
     *coloca nome de curso
     * @param nomeCurso
     */
    public void setNomeCurso(String nomeCurso){
     this.nomeCurso=nomeCurso;
    } 
 
    /**
     *retorna nome do curso
     * @return
     */
    public String getNomeCurso(){
     return nomeCurso;
    } 
    
    public String toString(){
        return nomeCurso;
    }
}
