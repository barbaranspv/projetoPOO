/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;

/**
 *Classe para ler e escrever ficheiros
 * @author barba
 */
public class Ficheiros implements Serializable{
    
    /**
     *Construtor
     */
    public Ficheiros(){}

    /**
     *Lê ficheiro de texto
     * @return
     */
    protected ArrayList<Local> leFicheiroLocais(){
        File f = new File("places2.txt");
        ArrayList<Local> locais= new ArrayList();
        String nomeLocal;
        String[] infos;
        int pref;
        float dist;
        String[] pontosInt;
        String[] temp;
        if(f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;   
                

                while((line = br.readLine()) != null) {
                    nomeLocal=line;
                    line=br.readLine();
                    dist=Float.parseFloat(line);
                    line=br.readLine();
                    pref=Integer.parseInt(line);
                    line=br.readLine();
                    pontosInt=line.split("\\s*,\\s*");
                    ArrayList<PontoInteresse> pontos= new ArrayList();
                    for (int i=0; i<pontosInt.length;i++){
                            infos = pontosInt[i].split("\\s*-\\s*");
                     
                            if (infos[1].equals( "Museu")){
                                Museu pontoInt= new Museu();
                                pontoInt.setNomePonto(infos[0].trim());
                                pontoInt.setDescricao(infos[2].trim());
                                Horario horario=new Horario(infos[3],"9:00/19:00");
                                pontoInt.setHorario(horario);
                                pontoInt.setPrefPonto(0);
                                pontoInt.setCustoEntrada(Float.parseFloat(infos[5]));
                                pontoInt.calculaTotal();
                                pontoInt.setTipo(infos[1]);
                                pontos.add(pontoInt);
            
                            }
                            else if (infos[1].equals( "Bar")){
                                Bar pontoInt= new Bar();
                                pontoInt.setNomePonto(infos[0].trim());
                                Horario horario=new Horario(infos[2],"12:00/4:00");
                                pontoInt.setHorario(horario);
                                pontoInt.setPrefPonto(0);
                                pontoInt.setDespesa(Float.parseFloat(infos[5]));
                                pontoInt.calculaTotal();
                                pontoInt.setClassificacao((int) Float.parseFloat(infos[4]));
                                pontoInt.setTipo(infos[1]);
                                pontos.add(pontoInt);
                                
                                
                           }
                            else if (infos[1].equals( "Universidade")){
                               Universidade pontoInt= new Universidade();
                               
                               pontoInt.setNomePonto(infos[0].trim());
                               Horario horario=new Horario("7:00/21:00","Fechado");
                               pontoInt.setHorario(horario);
                               pontoInt.setPrefPonto(0);
                               pontoInt.calculaTotal();
                               pontoInt.setTipo(infos[1]);
                             
                               temp= infos[2].split("\\s*;\\s*");
                              
                               for (int j =0; j<temp.length;j++){
                                   Curso curso= new Curso(temp[j]);
                                   pontoInt.setCurso(curso);
                               }
                               pontos.add(pontoInt);
                                
                            }
                            else if (infos[1].equals( "Tematico")){
                                ParqueTematico pontoInt= new ParqueTematico();
                                pontoInt.setNomePonto(infos[0].trim());
                                Horario horario=new Horario("10:00/21:00","10:00/21:00");
                                pontoInt.setHorario(horario);
                                pontoInt.setPrefPonto(0);
                                pontoInt.setCustoEntrada(Float.parseFloat(infos[2]));
                                pontoInt.setDespesa(Float.parseFloat(infos[3]));
                                pontoInt.calculaTotal();
                                pontoInt.setTipo(infos[1]);
                                temp= infos[4].split("\\s*;\\s*");
                                
                                 for (int j =0; j<temp.length; j++){
                                   pontoInt.setAtracao(temp[j]);
                               }
                               
                           pontos.add(pontoInt);
                           }
                            else if (infos[1].equals( "Aquatico")){
                                ParqueAquatico pontoInt= new ParqueAquatico();
                                pontoInt.setNomePonto(infos[0].trim());
                                Horario horario=new Horario("10:00/20:00","10:00/19:00");
                                pontoInt.setHorario(horario);
                                pontoInt.setPrefPonto(0);
                                pontoInt.setCustoEntrada(Float.parseFloat(infos[2]));
                                pontoInt.setDespesa(Float.parseFloat(infos[3]));
                                pontoInt.calculaTotal();
                                pontoInt.setTipo(infos[1]);
                                temp= infos[4].split("\\s*;\\s*");
                                
                                 for (int j =0; j<temp.length; j++){
                                   pontoInt.setAtracao(temp[j]);
                               }
                               pontos.add(pontoInt);
                                
                            }
                          else if (infos[1].equals( "Cultural")){
                                ParqueCultural pontoInt= new ParqueCultural();
                                pontoInt.setNomePonto(infos[0].trim());
                                Horario horario=new Horario("10:00/21:00","10:00/21:00");
                                pontoInt.setHorario(horario);
                                pontoInt.setPrefPonto(0);
                                pontoInt.setCustoEntrada(Float.parseFloat(infos[2]));
                                pontoInt.setDespesa(Float.parseFloat(infos[3]));
                                pontoInt.calculaTotal();
                                pontoInt.setTipo(infos[1]);
                                temp= infos[4].split("\\s*;\\s*");
                                
                                 for (int j =0; j<temp.length; j++){
                                   pontoInt.setVista(temp[j]);
                               }
                          

                           pontos.add(pontoInt);
                               
                           }
                           
                          
                            
                        }
                    
                    line=br.readLine();
                    Local local=new Local(nomeLocal,dist,pref,pontos);
                    for (int j=0; j< local.getPontosInteresseLocais().size();j++){
                        local.getPontosInteresseLocais().get(j).setLocal(local);
                    }
                    locais.add(local);
                }
                        br.close();
                        
                        
                
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
      } else {
            System.out.println("Ficheiro não existe.");
      }
      
       return locais;
    }
    
    /**
     *Escreve ficheiro de objetos de locais
     * @param locais
     */
    protected void escreveFicheiroObjetos( ArrayList<Local> locais){
        File f=new File("locais.txt");
      
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
       
            oos.writeObject(locais);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro locais.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro locais. ");
            }
        
    }
    
    /**
     *Lê ficheiro de objetos de locais
     * @return
     */
    protected ArrayList<Local> leFicheiroObjetos(){
        File f=new File("locais.txt");
        ArrayList<Local> locais= new ArrayList();
        int i=0;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

         locais = (ArrayList<Local>)ois.readObject();
            
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro locais.");
        } catch (IOException ex) {
            System.out.println("Erro a ler ficheiro locais.");
       } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto locais.");
       }

        return locais;
    }
    
    /**
     *Escreve ficheiro de objetos de alunos
     * @param alunos
     */
    protected void escreveFicheiroAlunos( ArrayList<Aluno> alunos){
        File f=new File("alunos.txt");
      
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(alunos);
            oos.close();
            System.out.println("ESCREVEU ficheiro alunos.");
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro alunos.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro alunos.");
            }
        
    }

    /**
     *Lê ficheiro de objetos de alunos
     * @param alunos
     * @return
     */
    protected ArrayList<Aluno> leFicheiroAlunos(ArrayList<Aluno> alunos){
        File f=new File("alunos.txt");
        try {
            
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            alunos= (ArrayList<Aluno>)ois.readObject();
            System.out.println("LEU ficheiro alunos");
            
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro alunos.");
        } catch (IOException ex) {
            System.out.println("Erro a ler ficheiro alunos.");
       } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto alunos.");
       }
      

        return alunos;
    }
}
