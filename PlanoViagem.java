/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;


/**
 *Aplicação de Planeamento de viagens
 * @author barba
 */
public class PlanoViagem extends JFrame{

        private JLabel labelHot, labelNot,labelLocaisPref,labelVerLoc, labelPontosPref,labelVerPonto, labelAreaLoc, labelAreaPontos,labelAreaViagens,labelEscolher;
        private JButton  orcamento,buttonNot,buttonViagensPessoais, buttonViagensCres,buttonViagensDec, locCres,locDec,locBut,pontosCres,pontosDec,pontosBut,buttonPrefLoc,buttonVerViagem, locFamous, pontosFamous,buttonEscolher ,buttonVerLoc,buttonPrefPonto, buttonVerPonto, buttonViagem, buttonViagens, buttonSair,buttonHot,buttonViagensSemPref;
        private JPanel panel2;
        private JComboBox <String> not, verLoc, verPonto, hot,escolher;
        private JList newLocais,pontos;
        private JTextArea areaLocais,areaPontos,areaViagens;

    /**
     *Lista que armazena os pontos de interesse todos
     */
    protected ArrayList <PontoInteresse> listaPontos= new ArrayList();

    /**
     *Lista de pontos que vai ser ordenada segundo custo do ponto de intesse
     */
     protected ArrayList <PontoInteresse>ordenaPontos= new ArrayList();

    /**
     *
     * Lista de pontos cuja preferencia é maior que um, logo foram colocados como preferencias dos clientes anteriores
     */
     protected ArrayList <PontoInteresse> pontosFamosos= new ArrayList();
        private Ficheiros f;

    /**
     *Lista de alunos que já usaram a aplicação
     */
    private ArrayList<Aluno> alunos= new ArrayList();

    /**
     *
     * Lista que armazena os locais todos
     */
    protected ArrayList<Local> listaLocais ;

    /**
     *
     * Lista de locais que vai ser ordenada segundo custo 
     */
    protected ArrayList<Local>  ordenaLocais= new ArrayList();

    /**
     *
     * Lista de locais cuja preferencia é maior que um, logo foram colocados como preferencias dos clientes anteriores
     */
    protected ArrayList<Local> locaisFamosos= new ArrayList();

    /**
     *
     * Lista utilizada como armazem das preferencias do utilizador quando este decide carregar na opcao "viagem sem preferencias"
     */
    protected ArrayList<Local> locaisPrefTemp= new ArrayList();

    /**
     *Utilizador atual
     */
    protected Aluno aluno;

    /**
     *Viagens possiveis tendo em conta preferencias e orcamento do utilizador, e que visitem um museu
     */
    protected ArrayList<Viagem> viagensPossiveis= new ArrayList();

    /**
     *
     * Lista de viagens que vai ser ordenada segundo custo 
     */
     protected ArrayList<Viagem>ordenaViagens= new ArrayList();

    /**
     *
     * Lista de viagens sem contar com o orcamento
     */
     protected ArrayList<Viagem>viagensSemOrcamento = new ArrayList();

    /**
     *
     * Lista de viagens sem contar preferencias
     */
     protected ArrayList<Viagem>viagensSemPref= new ArrayList();

    /**
     *Array de strings para a combobox de escolher viagem
     */
    protected ArrayList<String> comboArray=new ArrayList();

    /**
     *Variavel para ver se carregou naquele botao pela primeira vez
     */
    protected int click=0;

    /**
     *Variavel para ver se carregou naquele botao pela primeira vez
     */
    protected int click2=0;

    /**
     *Variavel para ver se carregou naquele botao pela primeira vez
     */
    protected int click3=0;

    /**
     *Variável para ver se o comprimento das preferencias mudou
     */
    protected int comp=0;

    /**
     *Variável para ver se o comprimento das preferencias mudou
     */
    protected int comp2=0;
        
        //////NÃO ESQUECER DE FAZER BOTAO DE SAIDA PARA GUARDAR TODAS A MODIFICAÇOES

    /**
     *Função para criar a frame de planeamento de viagens
     * @throws IOException
     */
        
        public void criaFrame() throws IOException{
           
            criaPlanner();
            
            this.setSize(1950, 1000);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.setVisible(true);
        }
        
    /**
     * Construtor do plano de viagem, efetua leitura de ficheiros e coloca pontos de interesse dos locais num array
     */
    public PlanoViagem(){
            this.f=new Ficheiros();
            f.leFicheiroAlunos(alunos);
          
            this.listaLocais = f.leFicheiroObjetos();
            
//            for (int i=0 ; i<listaLocais.size();i++){
//                listaLocais.get(i).setCustoLocal();
//            }
            copiaArrayLocais(ordenaLocais,listaLocais);
            
            //f.escreveFicheiroObjetos(listaLocais);
            for (int i=0;i<listaLocais.size(); i++){
                            for (int j=0; j<listaLocais.get(i).getPontosInteresseLocais().size();j++){
                                listaPontos.add(listaLocais.get(i).getPontosInteresseLocais().get(j));
                                if (listaLocais.get(i).getPontosInteresseLocais().get(j).getPrefPonto()>0)
                                    pontosFamosos.add(listaLocais.get(i).getPontosInteresseLocais().get(j));
                            }
          
                        }
            for (int i= 0; i<listaLocais.size();i++){
                         if (listaLocais.get(i).getPrefLocal()>0){
                             locaisFamosos.add(listaLocais.get(i));
                         }
                     }
            copiaArrayPontos(ordenaPontos,listaPontos);
                    }

    /**
     * Funcao para definir aluno como sendo de mestrado
     * @param aluno
     */
    public void setAlunoMestrado(AlunoMestrado aluno){
            this.aluno=aluno;
        }
        
    /**
     *Funcao para definir aluno como sendo de licenciatura
     * @param aluno
     */
    public void setAlunoLicenciatura(AlunoLicenciatura aluno){
            this.aluno=aluno;
        }
        
    private class ButtonActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                //BOTAO PARA VER TODOS OS LOCAIS
                if(e.getSource() == locBut) {
                    
                    areaLocais.setText(listaLocais.toString());
                    
                    
                }//BOTAO PARA VER TODOS OS PONTOS
                if(e.getSource() == pontosBut) {
                    areaPontos.setText(listaPontos.toString());
                }
                //BOTAO VER LOCAL SELECIONADO
                if(e.getSource() == buttonVerLoc) {
                     for (int i=0; i<listaLocais.size();i++){
                         if (verLoc.getSelectedIndex() == i){
                             areaLocais.setText(listaLocais.get(i).toString());
                             
                             break;
                         }
                     }
                 }//BOTAO PARA VER PONTO SELECIONADO
                if(e.getSource() == buttonVerPonto) {
                     for (int i=0; i<listaPontos.size();i++){
                         if (verPonto.getSelectedIndex() == i){
                             areaPontos.setText(listaPontos.get(i).toString());
                             break;
                         }
                     }
                 }//BOTAO PARA SELECIONAR LOCAIS PREFERIDOS
                if(e.getSource() == buttonPrefLoc) {
                    int[] indexes;
                    if (!newLocais.isSelectionEmpty()){
                        indexes=newLocais.getSelectedIndices();
                        for (int i=0; i<listaLocais.size();i++){
                            for (int j =0; j<indexes.length; j++){
                                if ( i== indexes[j]){
                                    if (aluno.setLocPreferido(listaLocais.get(i))==0){
                                        if (locaisFamosos.isEmpty()&& (listaLocais.get(i).getPrefLocal()>0 ))
                                                locaisFamosos.add(listaLocais.get(i));
                                        else{
                                            for (int k=0; k< locaisFamosos.size();k++){
                                                if (locaisFamosos.get(k)== listaLocais.get(i))
                                                    break;
                                                else if ((k==locaisFamosos.size()-1) && (locaisFamosos.get(k)!= listaLocais.get(i) && (listaLocais.get(i).getPrefLocal()>0 )) ){
                                                        locaisFamosos.add(listaLocais.get(i));
                                                }
                                            }
                                        }
                                    }
                                        break;
                                }
                            }
                               
                        }JOptionPane.showMessageDialog(null, "Preferencias colocadas!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                        buttonPrefLoc.setEnabled(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Nenhuma preferencia colocada!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                    }
                }//VER LOCAIS PREFERIDOS DOS UTILIZADORES
                if(e.getSource() == locFamous) {
                   
                    if (locaisFamosos.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Ainda não existem locais famosos!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                        
                    }else{
                        Collections.sort(locaisFamosos, (Local l1, Local l2) -> (int)( l2.getPrefLocal()-l1.getPrefLocal()));
                        areaLocais.setText(locaisFamosos.toString());                    
                    }
                     
                 }//COLOCA PREFERENCIAS DE PONTOS
                if(e.getSource() == buttonPrefPonto) {
                    int[] indexes;
                    if (!pontos.isSelectionEmpty()){
                        indexes=pontos.getSelectedIndices();
                        for (int i=0; i<listaPontos.size();i++){
                            for (int j =0; j<indexes.length; j++){
                                if ( i== indexes[j]){
                                        aluno.setPontoPreferido(listaPontos.get(i));
                                        if (pontosFamosos.isEmpty())
                                                pontosFamosos.add(listaPontos.get(i));
                                        else{
                                            for (int k=0; k< pontosFamosos.size();k++){
                                                if (pontosFamosos.get(k)== listaPontos.get(i))
                                                    break;
                                                else if ((k==pontosFamosos.size()-1) && (pontosFamosos.get(k)!= listaPontos.get(i))){
                                                        pontosFamosos.add(listaPontos.get(i));
                                                }
                                            }
                                        }
                                        if (locaisFamosos.isEmpty() && (listaPontos.get(i).getLocal().getPrefLocal()>0 ))
                                            locaisFamosos.add(listaPontos.get(i).getLocal());
                                        else{
                                            for (int k=0; k< locaisFamosos.size();k++){
                                                if (locaisFamosos.get(k)== listaPontos.get(i).getLocal() )
                                                    break;
                                            else if ((k==locaisFamosos.size()-1) && (locaisFamosos.get(k)!= listaPontos.get(i).getLocal())&& (listaPontos.get(i).getLocal().getPrefLocal()>0 )){
                                                    locaisFamosos.add(listaPontos.get(i).getLocal());
                                                            }
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Preferencias colocadas!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                        buttonPrefPonto.setEnabled(false);
                    }
                    
                    else{
                        JOptionPane.showMessageDialog(null, "Nenhuma preferencia colocada!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                    }
                }//VER PONTOS MAIS FAMOSOS ENTRE OS UTILIZADORES
                if(e.getSource() == pontosFamous) {
                   
                    if (pontosFamosos.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Ainda não existem pontos famosos!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                        
                    }else{
                        Collections.sort(pontosFamosos, (PontoInteresse l1, PontoInteresse l2) -> (int)( l2.getPrefPonto()-l1.getPrefPonto()));
                        areaPontos.setText(pontosFamosos.toString());                    
                    }
                }//SE O BOTAO NOT FOI SELECIONADO
                if(e.getSource() == buttonNot) {
                    for (int i=0; i<listaLocais.size();i++){
                         if (not.getSelectedIndex() == i){
                            aluno.setNot(listaLocais.get(i));
                            if (!locaisFamosos.isEmpty()){
                                {for (int k=0; k< locaisFamosos.size();k++){
                                        if (locaisFamosos.get(k)== listaLocais.get(i)){
                                                if (locaisFamosos.get(k).getPrefLocal()<1){
                                                    locaisFamosos.remove(locaisFamosos.get(k));
                                                }
                                            
                                            }}
                                        }}
                            
                            break;
                         }
                    }
                
                    buttonNot.setEnabled(false);
                    buttonPrefLoc.setEnabled(true);
                    buttonPrefPonto.setEnabled(true);
                    buttonViagensSemPref.setEnabled(true);
                    buttonViagem.setEnabled(true);
                    orcamento.setEnabled(true);
                 } //SE O BOTAO HOT FOI SELECIONADO
                if(e.getSource() == buttonHot) {
                    for (int i=0; i<listaPontos.size();i++){
                         if (hot.getSelectedIndex() == i){
                            aluno.setHot(listaPontos.get(i));
                            if (pontosFamosos.isEmpty())
                                pontosFamosos.add(listaPontos.get(i));
                            else{
                                for (int k=0; k< pontosFamosos.size();k++){
                                        if (pontosFamosos.get(k)== listaPontos.get(i))
                                                break;
                                        else if ((k==pontosFamosos.size()-1) && (pontosFamosos.get(k)!= listaPontos.get(i))){
                                                pontosFamosos.add(listaPontos.get(i));
                                                }
                                            }
                               
                            }
                            if (locaisFamosos.isEmpty())
                                locaisFamosos.add(listaPontos.get(i).getLocal());
                            else{
                                for (int k=0; k< locaisFamosos.size();k++){
                                    if (locaisFamosos.get(k)== listaPontos.get(i).getLocal()&& (listaPontos.get(i).getLocal().getPrefLocal()>0 ))
                                        break;
                                    else if ((k==locaisFamosos.size()-1) && (locaisFamosos.get(k)!= listaPontos.get(i).getLocal())&& (listaPontos.get(i).getLocal().getPrefLocal()>0 )){
                                        locaisFamosos.add(listaPontos.get(i).getLocal());
                                        }
                                    }
                            }
                            break;
                         }
                    }
                     buttonHot.setEnabled(false);
                     buttonPrefLoc.setEnabled(true);
                     buttonPrefPonto.setEnabled(true);
                     buttonViagensSemPref.setEnabled(true);
                     buttonViagem.setEnabled(true);
                     orcamento.setEnabled(true);
                }
                //LOCAIS CRESCENTES
                if(e.getSource() == locCres) {
                    Collections.sort(ordenaLocais);
                    areaLocais.setText(ordenaLocais.toString());
         
         
                    }
                //LOCAIS DECRESCENTES
                if(e.getSource() == locDec) {
                    Collections.sort(ordenaLocais);
                    Collections.reverse(ordenaLocais);
                    areaLocais.setText(ordenaLocais.toString());
         
         
                    }
                //PONTOS CRESCENTES
                 if(e.getSource() == pontosCres) {
                    Collections.sort(ordenaPontos);
                    areaPontos.setText(ordenaPontos.toString());
         
                    }
                //PONTOS DECRESCENTES 
                if(e.getSource() == pontosDec) {
                    Collections.sort(ordenaPontos);
                    Collections.reverse(ordenaPontos);
                    areaPontos.setText(ordenaPontos.toString());
         
                    }
                         
                //VIAGENS POSSIVEIS DENTRO DAS PREFERENCIAS E ORCAMENTO
                if(e.getSource() == buttonViagens) {
                    areaViagens.setText(viagensPossiveis.toString());
                }
                //VIAGENS CRESCENTES
                if(e.getSource() == buttonViagensCres){
                    Collections.sort(ordenaViagens);
                    areaViagens.setText(ordenaViagens.toString());
                    
                }
                //VIAGENS DECRESCENTES
                if(e.getSource() == buttonViagensDec){
                    Collections.sort(ordenaViagens);
                    Collections.reverse(ordenaViagens);
                    areaViagens.setText(ordenaViagens.toString());
                    
                }
                //VER PROPRIA VIAGEM
                if(e.getSource() == buttonVerViagem) {
         
                        if (aluno.getViagem()!=null){
                            areaViagens.setText(aluno.getViagem().toString());
                            
                        }
                        else
                             JOptionPane.showMessageDialog(null, "Ainda não tem nenhuma viagem atribuida!", "", JOptionPane.PLAIN_MESSAGE);
                    
                   
                }
                //VER VIAGENS PESSOAIS
                if(e.getSource() == buttonViagensPessoais) {
                        if (aluno.getViagensPessoais()!=null){
                            areaViagens.setText(aluno.getViagensPessoais().toString());
                            
                        }
                        else
                             JOptionPane.showMessageDialog(null, "Ainda não tem nenhuma viagem atribuida!", "", JOptionPane.PLAIN_MESSAGE);

                   
                    
                    
                }
                //GERAR VIAGEM PESSOAL
                if(e.getSource() == buttonViagem) {
                    click++;
                    buttonViagensSemPref.setEnabled(false);
                    buttonViagem.setEnabled(false);
                    orcamento.setEnabled(false);
                    if (click==1){
                            JOptionPane.showMessageDialog(null, "Espere um pouco, estamos a calcular as melhores viagens para si!", "AVISO", JOptionPane.PLAIN_MESSAGE);

                            geraViagens(viagensPossiveis);                        
                            if (aluno.getViagem()==null){
                                JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para o seu orçamento que inclua as preferências e visite um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                areaViagens.setText("");}
                            else{
                           
                                areaViagens.setText(aluno.getViagem().toString());
                                copiaArrayViagens(ordenaViagens,viagensPossiveis);
                                buttonViagens.setEnabled(true);
                                buttonViagensCres.setEnabled(true);
                                buttonViagensDec.setEnabled(true);
                                buttonVerViagem.setEnabled(true);
                                buttonViagensPessoais.setEnabled(true);
                                
                            }
                           comp=aluno.getLocPreferidos().size();
                        
                       
                    }
                   
                    else{
                        
                        viagem();
                    }buttonViagensSemPref.setEnabled(true);
                    buttonViagem.setEnabled(true);
                    orcamento.setEnabled(true);
                   
                }
                
                
                
                //ESCOLHE UMA VIAGEM PARA SIM
                if(e.getSource() == buttonEscolher) {
                    for (int i=0; i<aluno.getViagensPessoais().size();i++){
                             if (escolher.getSelectedIndex() == i){
                                    aluno.setViagem(aluno.getViagensPessoais().get(i));
                                    buttonVerViagem.setEnabled(true);
                                    JOptionPane.showMessageDialog(null, "Viagem Escolhida!", "AVISO", JOptionPane.PLAIN_MESSAGE);    
                                    break;
                             }}
                    
                }
                //DESEJA SAIR DO PROGRAMA
                if(e.getSource() == buttonSair) {
                    if (JOptionPane.showConfirmDialog(null, "Tens a certeza que queres sair?", "AVISO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (JOptionPane.showConfirmDialog(null, "Queres guardar a tua sessão?", "AVISO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            alunos.add(aluno);
                            
                            f.escreveFicheiroAlunos(alunos);
                            f.escreveFicheiroObjetos(listaLocais);
                            System.exit(0);
                        }
                        else {
                            System.exit(0);
                        }
                        
                    } else {
                        // no option
                    }
                }
                //VIAGEM SEM ORCAMENTO
                if(e.getSource() == orcamento) {
                    float temp;
                    buttonViagensSemPref.setEnabled(false);
                    buttonViagem.setEnabled(false);
                    orcamento.setEnabled(false);
                    click2++;
                    if (click2==1){
                            JOptionPane.showMessageDialog(null, "Espere um pouco, estamos a calcular as melhores viagens para si!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                            temp=aluno.getOrcamento();
                            aluno.setOrcamento(10000);
                            
                            geraViagens(viagensSemOrcamento);
                            
                            if (aluno.getViagem()==null){
                                JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para as suas preferencias e que inclua pelo menos um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                areaViagens.setText("");}
                            else{
                                areaViagens.setText(aluno.getViagem().toString());
                                buttonVerViagem.setEnabled(true);
                                buttonViagensPessoais.setEnabled(true);

                            }
                           comp2=aluno.getLocPreferidos().size();
                        }
                        
                    else{
                            temp=aluno.getOrcamento();
                            viagensSemOrcamento( temp);
                        }
                    
                    aluno.setOrcamento(temp);
                    buttonViagensSemPref.setEnabled(true);
                    buttonViagem.setEnabled(true);
                    orcamento.setEnabled(true);
                    
                    
                }
                //VIAGEM SEM PREFERENCIAS
                if(e.getSource() == buttonViagensSemPref) {
                    buttonViagensSemPref.setEnabled(false);
                    buttonViagem.setEnabled(false);
                    orcamento.setEnabled(false);
                    
                    click3++;
                    if (click3==1){
                            JOptionPane.showMessageDialog(null, "Espere um pouco, estamos a calcular as melhores viagens para si!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                            copiaArrayLocais(locaisPrefTemp,aluno.getLocPreferidos());
                            aluno.getLocPreferidos().clear();
                            
                            geraViagens(viagensSemPref);
                            
                            if (aluno.getViagem()==null){
                                JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para o seu orcamento e que visite um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                areaViagens.setText("");}
                            else{
                                areaViagens.setText(aluno.getViagem().toString());
                                buttonVerViagem.setEnabled(true);
                                buttonViagensPessoais.setEnabled(true);
                            }
                            
                    }
                   
                    else{
                        viagensSemPreferencias();
                      
                    }
                    
                    aluno.setLocaisPreferidos(locaisPrefTemp);
                    
                               
                    buttonViagensSemPref.setEnabled(true);
                    buttonViagem.setEnabled(true);
                    orcamento.setEnabled(true);}
            
            }
            
         }

    /**
     *Copia Array de locais
     * @param novo
     * @param velho
     */
    protected void copiaArrayLocais(ArrayList <Local> novo,ArrayList <Local> velho ){
            for (int i=0;i < velho.size();i++){
                novo.add(velho.get(i));
            }
    }

    /**
     *Copia Array de pontos
     * @param novo
     * @param velho
     */
    protected void copiaArrayPontos(ArrayList <PontoInteresse> novo,ArrayList <PontoInteresse> velho ){
            for (int i=0;i < velho.size();i++){
                novo.add(velho.get(i));
            }
    }

    /**
     *Copia array de viagens
     * @param novo
     * @param velho
     */
    protected void copiaArrayViagens(ArrayList <Viagem> novo,ArrayList <Viagem> velho ){
            novo.clear();
            for (int i=0;i < velho.size();i++){
                novo.add(velho.get(i));
            }
    }
        
    /**
     *Funçao para ver uma viagem possivel dentro das preferencias e orcamento do utilizador, contendo um museu
     * 
     */
    protected void viagem(){
                            Random rand = new Random();
                            int r;
                            if (aluno.getLocPreferidos().size()!= comp){
                                viagensPossiveis.clear();
                                JOptionPane.showMessageDialog(null, "Espere um pouco, estamos a calcular as melhores viagens para si!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                
                                geraViagens(viagensPossiveis);
                                
                                if ( viagensPossiveis.isEmpty()){
                                    JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para o seu orçamento que inclua as preferências e visite um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                    areaViagens.setText("");
                                }    
                                    

                               else if (aluno.getViagem()==null){
                                    JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para o seu orçamento que inclua as preferências e visite um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                    areaViagens.setText("");
                                }
                                else{
                                    
                                    copiaArrayViagens(ordenaViagens,viagensPossiveis);
                                    String combo="";
                                    int retorna;
                                    r=rand.nextInt(viagensPossiveis.size());
                                    retorna=aluno.setViagem(viagensPossiveis.get(r));
                                    areaViagens.setText(aluno.getViagem().toString());
                                    for (int i=0; i<viagensPossiveis.get(r).getLocAVisitar().size();i++){
                                        combo+=viagensPossiveis.get(r).getLocAVisitar().get(i).getNomeCidade()+" ";
                                        
                                    }if (retorna== 1){
                                        escolher.addItem(combo);
                                    }
                                    buttonViagens.setEnabled(true);
                                    buttonViagensCres.setEnabled(true);
                                    buttonViagensDec.setEnabled(true);
                                    buttonVerViagem.setEnabled(true);
                                    buttonViagensPessoais.setEnabled(true);
                                    
                                    
                                } comp=aluno.getLocPreferidos().size();
                            }
                            else{
                                if(!viagensPossiveis.isEmpty()){
                                    String combo="";
                                    int retorna;
                                    r=rand.nextInt(viagensPossiveis.size());
                                    retorna=aluno.setViagem(viagensPossiveis.get(r));
                                    areaViagens.setText(aluno.getViagem().toString());
                                    for (int i=0; i<viagensPossiveis.get(r).getLocAVisitar().size();i++){
                                        combo+=viagensPossiveis.get(r).getLocAVisitar().get(i).getNomeCidade()+" ";
                                        
                                    }if (retorna== 1){
                                        escolher.addItem(combo);
                                    }}
                                else{
                                    JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para o seu orçamento que inclua as preferências e visite um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                    areaViagens.setText("");
                                    buttonViagens.setEnabled(false);
                                    buttonViagensCres.setEnabled(false);
                                    buttonViagensDec.setEnabled(false);
                                    buttonVerViagem.setEnabled(false);
                                }comp=aluno.getLocPreferidos().size();
                                     
                            }
}

    /**
     *Funcao para visualizar viagem sem as preferencias, passando sempre por um museu , ignorando o local not se é aluno de mestrado e passando pelo ponto de interesse hot se é de licenciatura
     */
    protected void viagensSemPreferencias(){
                            Random rand = new Random();
                            int r;
                            copiaArrayLocais(locaisPrefTemp,aluno.getLocPreferidos());
                            aluno.getLocPreferidos().clear();
                            if(!viagensSemPref.isEmpty()){
                                    String combo="";
                                    int retorna;
                                    r=rand.nextInt(viagensSemPref.size());
                                    retorna=aluno.setViagem(viagensSemPref.get(r));
                                    areaViagens.setText(aluno.getViagem().toString());
                                    for (int i=0; i<viagensSemPref.get(r).getLocAVisitar().size();i++){
                                        combo+=viagensSemPref.get(r).getLocAVisitar().get(i).getNomeCidade()+" ";
                                        
                                    }if (retorna== 1){
                                        escolher.addItem(combo);
                                    }
                                    }
                            else{
                                    JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para o seu orcamento e que visite um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                    areaViagens.setText("");
                                    buttonVerViagem.setEnabled(false);
                                }
}

    /**
     *Funcao para visualizar viagem sem contar com orcamento, passando sempre por um museu , ignorando o local not se é aluno de mestrado e passando pelo ponto de interesse hot se é de licenciatura
     * @param temp
     */
    protected void viagensSemOrcamento(float temp){
                            Random rand = new Random();
                            int r;
                            //temp=alunoM.getOrcamento();
                            aluno.setOrcamento(10000);
                            if (aluno.getLocPreferidos().size()!= comp2){
                                viagensSemOrcamento.clear();
                                JOptionPane.showMessageDialog(null, "Espere um pouco, estamos a calcular as melhores viagens para si!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                
                                geraViagens(viagensSemOrcamento);
                               
                                if ( viagensSemOrcamento.isEmpty()){
                                    JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para as suas preferencias e que inclua pelo menos um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                    areaViagens.setText("");
                                }    
                                    

                                else if (aluno.getViagem()==null){
                                    JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para as suas preferencias e que inclua pelo menos um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                    areaViagens.setText("");
                                }
                                else{
                                   String combo="";
                                    int retorna;
                                    r=rand.nextInt(viagensSemOrcamento.size());
                                    retorna=aluno.setViagem(viagensSemOrcamento.get(r));
                                    areaViagens.setText(aluno.getViagem().toString());
                                    for (int i=0; i<viagensSemOrcamento.get(r).getLocAVisitar().size();i++){
                                        combo+=viagensSemOrcamento.get(r).getLocAVisitar().get(i).getNomeCidade()+" ";
                                        
                                    }if (retorna== 1){
                                        escolher.addItem(combo);
                                    }

                                    buttonVerViagem.setEnabled(true);
                                    buttonViagensPessoais.setEnabled(true);
                                    

                                } comp2=aluno.getLocPreferidos().size();
                            }
                            else{
                                if(!viagensSemOrcamento.isEmpty()){
                                    String combo="";
                                    int retorna;
                                    r=rand.nextInt(viagensSemOrcamento.size());
                                    retorna=aluno.setViagem(viagensSemOrcamento.get(r));
                                    areaViagens.setText(aluno.getViagem().toString());
                                    for (int i=0; i<viagensSemOrcamento.get(r).getLocAVisitar().size();i++){
                                        combo+=viagensSemOrcamento.get(r).getLocAVisitar().get(i).getNomeCidade()+" ";
                                        
                                    }if (retorna== 1){
                                        escolher.addItem(combo);
                                    }
                                    }
                                else{
                                    JOptionPane.showMessageDialog(null, "Não há nenhuma viagem para as suas preferencias e que inclua pelo menos um museu!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                    areaViagens.setText("");
                                    buttonVerViagem.setEnabled(false);
                                }comp2=aluno.getLocPreferidos().size();
                                     
                            }
    
}
    
    
    
    
     /**
     *Funçao que gera array de viagens possiveis, de acordo com a opçao escolhida no menu
     * @param viagens
     */
    protected void geraViagens(ArrayList <Viagem> viagens){
        int size=0;
        int acum=0;
        int retorna;
        Random rand= new Random();
        int r;
        buttonViagensSemPref.setEnabled(false);
        buttonViagem.setEnabled(false);
        orcamento.setEnabled(false);
        while (acum!= 2000){
            if (aluno.getNot()!=null){
                calculaViagemMestrado(viagens);
            }
            else{
                calculaViagemLicenciatura(viagens);

            }
            if (viagens.size()==size)
                acum ++;
            else 
                acum=0;

            size= viagens.size();

        }
        if (!viagens.isEmpty()){
            r=rand.nextInt(viagens.size());
            String combo="";
            for (int i=0; i<viagens.get(r).getLocAVisitar().size();i++){
                 combo+=viagens.get(r).getLocAVisitar().get(i).getNomeCidade()+" ";
            }
            retorna=aluno.setViagem(viagens.get(r));
            if (retorna==1){
                escolher.addItem(combo);
            }
            
            buttonEscolher.setEnabled(true);
        }buttonViagensSemPref.setEnabled(true);
         buttonViagem.setEnabled(true);
         orcamento.setEnabled(true);
    }
    
    /**
     *Função para calcular as viagens relativas aos alunos de mestrado
     * @param viagens
     */
    protected void calculaViagemMestrado( ArrayList <Viagem> viagens){
        Viagem viagem= new Viagem();
        
        //SE TIVER 3 OU  MAIS  LOCAIS FAVORITOS
        if (aluno.getLocPreferidos().size()>=3){
           viagemMestradoTresOuMaisFavoritos( viagens);
            } 
        //TEM MENOS DE 3 LOCAIS FAVORITOS
                        
        //SE NAO SELECIONOU PREFERENCIAS
            
        else if (aluno.getLocPreferidos().isEmpty() )
        {       calculaViagemMestradoSemPreferencias( viagens);
//                    
            } 
            //SE SELECIONOU MENOS DE 3 LOCAIS E MENOS DE 3 PONTOS
        else if ((aluno.getLocPreferidos().size()>0 && aluno.getLocPreferidos().size()<3) ){
                //SE FICA COM MENOS DE 3 LOCAIS PREFERIDOS 
                viagemMestradoMenosDeTresFavoritos(viagens);
                
                
                
                
      }
    }

     /**
     *Função para calcular as viagens relativas aos alunos de licenciatura
     * @param viagens
     */
    protected void calculaViagemLicenciatura( ArrayList <Viagem> viagens){
        Viagem viagem= new Viagem();
        int i;
        int n=0;
        if (aluno.getHot()!=null){
                for (i=0; i<aluno.getHot().getLocal().getPontosInteresseLocais().size();i++){
                    if (aluno.getHot().getLocal().getPontosInteresseLocais().get(i).getTipo().equals("Museu")){
                         n++;
                         break;
                    }}
            viagem.setLocAVisitar(aluno.getHot().getLocal());
        }
        //SE TIVER 3 OU  MAIS  LOCAIS FAVORITOS
        if (aluno.getLocPreferidos().size()>=3){
            viagemLicenciaturaTresOuMaisFavoritos(viagens, viagem,n);          
        } 
        //TEM MENOS DE 3 LOCAIS FAVORITOS
        else if ( aluno.getLocPreferidos().isEmpty()){
            calculaViagemLicenciaturaSemPreferencias(viagens, viagem,n);

            } 
         //SE SELECIONOU MENOS DE 3 LOCAIS E MENOS DE 3 PONTOS
        else if ((aluno.getLocPreferidos().size()>0 && aluno.getLocPreferidos().size()<3)|| (aluno.getPontosPreferidos().size()>0 && aluno.getPontosPreferidos().size()<3)){
                viagemLicenciaturaMenosDeTresFavoritos( viagens,viagem,n);

                
        }
    }

    
    
    
    
    
    /**
     *Função que gera uma viagem para um aluno de mestrado se este tiver 3 ou mais preferencias de local
     * @param viagens
     */
    protected void viagemMestradoTresOuMaisFavoritos(ArrayList <Viagem> viagens){
        Viagem viagem= new Viagem();
        int i;
        Random rand = new Random();
        int r;
        int acum=0;
        int n=0;
        while (viagem.getLocAVisitar().size()<3){
                r=rand.nextInt(aluno.getLocPreferidos().size());
                
                if (viagem.getLocAVisitar().isEmpty()){
                    for (i=0; i<aluno.getLocPreferidos().get(r).getPontosInteresseLocais().size();i++){
                        if (aluno.getLocPreferidos().get(r).getPontosInteresseLocais().get(i).getTipo().equals("Museu"))
                            n++;
                    }
                    viagem.setLocAVisitar(aluno.getLocPreferidos().get(r));
                }
                else{
                    if (acum==3)
                            break;
                    for (i=0; i<aluno.getLocPreferidos().get(r).getPontosInteresseLocais().size();i++){
                        if (aluno.getLocPreferidos().get(r).getPontosInteresseLocais().get(i).getTipo().equals("Museu"))
                            n++;
                    }
                    for (int j=0;j<viagem.getLocAVisitar().size();j++){
                        if (viagem.getLocAVisitar().get(j)==aluno.getLocPreferidos().get(r)|| (aluno.getLocPreferidos().get(r).equals(aluno.getNot()))){
                            acum++;
                            break;
                        }
                        else if ((j==viagem.getLocAVisitar().size()-1) && (viagem.getLocAVisitar().get(j)!=aluno.getLocPreferidos().get(r))&& (aluno.getLocPreferidos().get(r)!=(aluno.getNot()))){
                            acum=0;
                            if (n>0)
                                viagem.setLocAVisitar(aluno.getLocPreferidos().get(r));

                        }       
                    }
                
                }
            }
            if (viagem.getLocAVisitar().size()==3){
                if (viagem.despesasTotais()<= aluno.getOrcamento()){
                            if (viagens.isEmpty()){
                                //alunoM.setViagem(viagem);
                                viagens.add(viagem);
                            }
                            else    {   
                                for ( i=0;i< viagens.size();i++){
                                    if ((viagem.calculaDeslocamento()==viagens.get(i).calculaDeslocamento())&& viagem.despesasTotais() == viagens.get(i).despesasTotais()){
                                        i=-1;
                                        break;}
                                }if (i!=-1){
                                    //alunoM.setViagem(viagem);
                                    viagens.add(viagem);
                                }
                            }
                        }
            }
    
}

    /**
     *Função que gera uma viagem para um aluno de mestrado se este nao tiver preferencias de local
     * @param viagens
     */
    protected void calculaViagemMestradoSemPreferencias(ArrayList<Viagem> viagens){
        Viagem viagem= new Viagem();
        int i;
        Random rand = new Random();
        int r;
        int n=0;
        while (viagem.getLocAVisitar().size()<3){
                        r=rand.nextInt(listaLocais.size());
                        if (viagem.getLocAVisitar().isEmpty()){
                            if (!listaLocais.get(r).equals(aluno.getNot())){
                                for (i=0; i<listaLocais.get(r).getPontosInteresseLocais().size();i++){
                                    if (listaLocais.get(r).getPontosInteresseLocais().get(i).getTipo().equals("Museu"))
                                        n++;
                                }
                                 viagem.setLocAVisitar(listaLocais.get(r));
                            }
                        }else{
                            for (i=0; i<listaLocais.get(r).getPontosInteresseLocais().size();i++){
                                if (listaLocais.get(r).getPontosInteresseLocais().get(i).getTipo().equals("Museu"))
                                    n++;
                            }
                            for (int j=0;j<viagem.getLocAVisitar().size();j++){
                                        if ((viagem.getLocAVisitar().get(j)==listaLocais.get(r)) || (listaLocais.get(r).equals(aluno.getNot()))){
                                            break;
                                        }
                                        else if ((j==viagem.getLocAVisitar().size()-1) && (viagem.getLocAVisitar().get(j)!=listaLocais.get(r))&&  (listaLocais.get(r)!=(aluno.getNot()))){
                                            if (n>0)
                                                viagem.setLocAVisitar(listaLocais.get(r));
                                            //viagem.setPontosAVisitar(listaLocais.get(r).getPontosInteresseLocais());
                                        }
                                    }

                    }}
                    if (viagem.despesasTotais()<= aluno.getOrcamento()){
                            if (viagens.isEmpty()){
                                //alunoM.setViagem(viagem);
                                viagens.add(viagem);
                            }
                            else    {   
                                for ( i=0;i< viagens.size();i++){
                                    if ((viagem.calculaDeslocamento()==viagens.get(i).calculaDeslocamento())&& viagem.despesasTotais() == viagens.get(i).despesasTotais()) {
                                        i=-1;
                                        break;}
                                }if (i!=-1){
                                    //alunoM.setViagem(viagem);
                                    viagens.add(viagem);
                                }
                            }
                    }
    
}

    /**
     *Função que gera uma viagem para um aluno de mestrado se este tiver menos de 3 preferencias de local
     * @param viagens
     */
    protected void viagemMestradoMenosDeTresFavoritos(ArrayList<Viagem> viagens){
        Viagem viagem= new Viagem();
        int i;
        Random rand = new Random();
        int r;
        int n=0;
        for (i=0; i<aluno.getLocPreferidos().size(); i++){
                        for (int j =0;j<aluno.getLocPreferidos().get(i).getPontosInteresseLocais().size();j++){
                            if (aluno.getLocPreferidos().get(i).getPontosInteresseLocais().get(j).getTipo().equals("Museu"))
                                n++;}
                            viagem.setLocAVisitar(aluno.getLocPreferidos().get(i));
         }
        while (viagem.getLocAVisitar().size()<3){
                        r=rand.nextInt(listaLocais.size());
                        for (i=0; i<listaLocais.get(r).getPontosInteresseLocais().size();i++){
                                if (listaLocais.get(r).getPontosInteresseLocais().get(i).getTipo().equals("Museu"))
                                    n++;
                            }
                        for (int j=0;j<viagem.getLocAVisitar().size();j++){
                                if (viagem.getLocAVisitar().get(j)==listaLocais.get(r) || (listaLocais.get(r).equals(aluno.getNot()))){
                                    break;
                                }
                                else if ((j==viagem.getLocAVisitar().size()-1) && (viagem.getLocAVisitar().get(j)!=listaLocais.get(r)) &&  (listaLocais.get(r)!=(aluno.getNot()))){
                                    if (n>0)
                                        viagem.setLocAVisitar(listaLocais.get(r));
                                    else
                                        break;
                                }
                            }
     
        }
                            
        if (viagem.despesasTotais()<= aluno.getOrcamento()){
                        if (viagens.isEmpty()){
                            viagens.add(viagem);
                        }
                        else    {   
                            for ( i=0;i< viagens.size();i++){
                                if ((viagem.calculaDeslocamento()==viagens.get(i).calculaDeslocamento())&& viagem.despesasTotais() == viagens.get(i).despesasTotais()) {
                                    i=-1;
                                    break;}
                            }if (i!=-1){
                                viagens.add(viagem);
                            }
                        }
        }
                
    
}
        
   
    /**
     *Função que gera uma viagem para um aluno de licenciatura se este tiver 3 ou mais preferencias de local
     * @param viagens
     * @param viagem
     * @param n
     */
    protected void viagemLicenciaturaTresOuMaisFavoritos(ArrayList <Viagem> viagens,Viagem viagem, int n){
        int i;
        Random rand = new Random();
        int r;
        while (viagem.getLocAVisitar().size()<3){
                r=rand.nextInt(aluno.getLocPreferidos().size());
                
                if (viagem.getLocAVisitar().isEmpty()){
                    if (aluno.getHot()!=null){
                        for (i=0; i<aluno.getHot().getLocal().getPontosInteresseLocais().size();i++){
                            if (aluno.getHot().getLocal().getPontosInteresseLocais().get(i).getTipo().equals("Museu"))
                                n++;
                    }
                    viagem.setLocAVisitar(aluno.getHot().getLocal());
                    }
                    if (aluno.getLocPreferidos().get(r)!=aluno.getHot().getLocal()){
                        for (i=0; i<aluno.getLocPreferidos().get(r).getPontosInteresseLocais().size();i++){
                            if (aluno.getLocPreferidos().get(r).getPontosInteresseLocais().get(i).getTipo().equals("Museu"))
                                n++;
                        }
                    viagem.setLocAVisitar(aluno.getLocPreferidos().get(r));
                    }
                }
                else{
                    for (i=0; i<aluno.getLocPreferidos().get(r).getPontosInteresseLocais().size();i++){
                        if (aluno.getLocPreferidos().get(r).getPontosInteresseLocais().get(i).getTipo().equals("Museu"))
                            n++;
                    }
                    for (int j=0;j<viagem.getLocAVisitar().size();j++){
                        if (viagem.getLocAVisitar().get(j)==aluno.getLocPreferidos().get(r)){

                            break;
                        }
                        else if ((j==viagem.getLocAVisitar().size()-1) && (viagem.getLocAVisitar().get(j)!=aluno.getLocPreferidos().get(r))){
                            if (n>0)
                                viagem.setLocAVisitar(aluno.getLocPreferidos().get(r));
                            else
                                break;
                            

                        }       
                    }
                
             }}
            if (viagem.despesasTotais()<= aluno.getOrcamento()){
                        if (viagens.isEmpty()){
                            viagens.add(viagem);
                        }
                        else    {   
                            for ( i=0;i< viagens.size();i++){
                                if ((viagem.calculaDeslocamento()==viagens.get(i).calculaDeslocamento())&& viagem.despesasTotais() == viagens.get(i).despesasTotais()){
                                    i=-1;
                                    break;}
                            }if (i!=-1){
                               // alunoL.setViagem(viagem);
                                viagens.add(viagem);
                            }
                        }
                    }
           
        
}
        
    /**
     *Função que gera uma viagem para um aluno de licenciatura se este não tiver preferencias de local
     * @param viagens
     * @param viagem
     * @param n
     */
    protected void calculaViagemLicenciaturaSemPreferencias(ArrayList<Viagem> viagens, Viagem viagem, int n){
        int i;
        Random rand = new Random();
        int r;
        while (viagem.getLocAVisitar().size()<3){
                        r=rand.nextInt(listaLocais.size());
                        for (i=0; i<listaLocais.get(r).getPontosInteresseLocais().size();i++){
                                if (listaLocais.get(r).getPontosInteresseLocais().get(i).getTipo().equals("Museu"))
                                    n++;
                            }
                        for (int j=0;j<viagem.getLocAVisitar().size();j++){
                                        if ((viagem.getLocAVisitar().get(j)==listaLocais.get(r)) ){
                                            break;
                                        }
                                        else if ((j==viagem.getLocAVisitar().size()-1) && (viagem.getLocAVisitar().get(j)!=listaLocais.get(r))){
                                            if (n>0){
                                                viagem.setLocAVisitar(listaLocais.get(r));
                                                break;
                                                }
                                            else
                                                break;
                                        }
                                    }

        }
                    if (viagem.despesasTotais()<= aluno.getOrcamento()){
                            if (viagens.isEmpty()){
                                viagens.add(viagem);
                            }
                            else    {   
                                for ( i=0;i< viagens.size();i++){
                                    if ((viagem.calculaDeslocamento()==viagens.get(i).calculaDeslocamento())&& viagem.despesasTotais() == viagens.get(i).despesasTotais()) {
                                        i=-1;
                                        break;}
                                }if (i!=-1){
                                    viagens.add(viagem);
                                }
                            }
                    }
    
}

    /**
     *Função que gera uma viagem para um aluno de licenciatura se este tiver menos de 3 preferencias de local
     * @param viagens
     * @param viagem
     * @param n
     */
    protected void viagemLicenciaturaMenosDeTresFavoritos(ArrayList<Viagem> viagens, Viagem viagem, int n){
        int i;
        Random rand = new Random();
        int r;
        for (i=0; i<aluno.getLocPreferidos().size(); i++){
                    if (aluno.getLocPreferidos().get(i) !=aluno.getHot().getLocal()){
                        for (int j =0;j<aluno.getLocPreferidos().get(i).getPontosInteresseLocais().size();j++){
                                if (aluno.getLocPreferidos().get(i).getPontosInteresseLocais().get(j).getTipo().equals("Museu"))
                                    n++;
                        }
                                 
                        viagem.setLocAVisitar(aluno.getLocPreferidos().get(i));}
                }
         
        while (viagem.getLocAVisitar().size()<3){
                        r=rand.nextInt(listaLocais.size());
                        for (i=0; i<listaLocais.get(r).getPontosInteresseLocais().size();i++){
                                if (listaLocais.get(r).getPontosInteresseLocais().get(i).getTipo().equals("Museu")){
                                    n++;
                            }}
                        for (int j=0;j<viagem.getLocAVisitar().size();j++){
                                if (viagem.getLocAVisitar().get(j)==listaLocais.get(r)){
                                    break;
                                }
                                if ((j==viagem.getLocAVisitar().size()-1) && (viagem.getLocAVisitar().get(j)!=listaLocais.get(r))){
                                    if (n>0)
                                        viagem.setLocAVisitar(listaLocais.get(r));
                                    else
                                        break;
                                }
                            }
     
        }
                            
        if (viagem.despesasTotais()<= aluno.getOrcamento()){
                        if (viagens.isEmpty()){
                            viagens.add(viagem);
                        }
                        else    {   
                            for ( i=0;i< viagens.size();i++){
                                if ((viagem.calculaDeslocamento()==viagens.get(i).calculaDeslocamento())&& viagem.despesasTotais() == viagens.get(i).despesasTotais()) {
                                    i=-1;
                                    break;}
                            }if (i!=-1){
                                viagens.add(viagem);
                            }
                        }
                
                
        }
}

   
    /**
     *Cria planner de viagens
     * @throws IOException
     */
    protected void criaPlanner() throws IOException{
                            Color VERY_DARK_GREY = new Color(51,51,51);
                            //LISTA COM OS NOMES DOS PONTOS DE INTERESSE PARA COLOCAR NAS COMBOBOXES
                            ArrayList<String> pontosInt= new ArrayList();
                            for (int i=0;i <listaLocais.size();i++){
                                for (int j=0;j<listaLocais.get(i).pontosLocal.size();j++){
                                    pontosInt.add(listaLocais.get(i).pontosLocal.get(j).nomePonto+" - "+ listaLocais.get(i).nomeCidade);
                                }
                            }
                            
                            
     //CRIAR COMPONENTES DO PAINEL2
                            String[] locais={"Lisboa","Porto","Aveiro","Figueira da Foz","Guimaraes","Madrid" ,"Barcelona" ,"Amsterdam","Warsaw","Paris","Bruxelles","Braga","Prague","Valencia","Vienna","Covilh�","Budapest","Salamanca","Coimbra","Rome"};
                            if (aluno.getNot()!=null){
                                //LOCAL A NAO VISITAR
                                labelNot=new JLabel("Qual o local a não visitar?");
                                labelNot.setBounds(50,10, 200, 25);
                                labelNot.setForeground(Color.WHITE);
                                not = new JComboBox(locais);
                                not.setBounds(250, 10, 200, 25);
                                not.setBackground(Color.BLACK);
                                not.setForeground(Color.WHITE);
                                buttonNot =new JButton("Escolher Local NOT");
                                buttonNot.setBounds(250,45,200,25);
                                buttonNot.setBackground(Color.BLACK);
                                buttonNot.setForeground(Color.WHITE);
                            }
                            
                            else{
                                //LOCAL A NAO VISITAR
                                labelHot=new JLabel("Qual o ponto a visitar?");
                                labelHot.setBounds(50,10, 200, 25);
                                labelHot.setForeground(Color.WHITE);
                                hot = new JComboBox(pontosInt.toArray());
                                hot.setBounds(250, 10, 200, 25);
                                hot.setBackground(Color.BLACK);
                                hot.setForeground(Color.WHITE);
                                buttonHot =new JButton("Escolher Ponto HOT");
                                buttonHot.setBounds(250,45,200,25);
                                buttonHot.setBackground(Color.BLACK);
                                buttonHot.setForeground(Color.WHITE);
                                
                            }
                                //ESCOLHER VIAGEM DAS VIAGENS PESSOAIS
                            labelEscolher=new JLabel("Qual viagem gerada quer escolher?");
                            labelEscolher.setBounds(30,100, 250, 25);
                            labelEscolher.setForeground(Color.WHITE);
                            escolher = new JComboBox();
                            escolher.setBounds(250, 100, 200, 25);
                            escolher.setBackground(Color.BLACK);
                            escolher.setForeground(Color.WHITE);
                            buttonEscolher =new JButton("Escolher Viagem");
                            buttonEscolher.setBounds(250,135,200,25);
                            buttonEscolher.setBackground(Color.BLACK);
                            buttonEscolher.setForeground(Color.WHITE);
                            
                            //VISUALIZAR LOCAL
                            labelVerLoc=new JLabel("Qual o local que quer visualizar?");
                            labelVerLoc.setBounds(500,10, 200, 25);
                            labelVerLoc.setForeground(Color.WHITE);
                            verLoc = new JComboBox(locais);
                            verLoc.setBounds(730, 10, 200, 25);
                            verLoc.setBackground(Color.BLACK);
                            verLoc.setForeground(Color.WHITE);
                            buttonVerLoc =new JButton("Visualizar local");
                            buttonVerLoc.setBounds(730,45,200,25);
                            buttonVerLoc.setBackground(Color.BLACK);
                            buttonVerLoc.setForeground(Color.WHITE);
                            //VISUALIZAR PONTO
                            labelVerPonto=new JLabel("Qual o ponto que quer visualizar?");
                            labelVerPonto.setBounds(500,100, 200, 25);
                            labelVerPonto.setForeground(Color.WHITE);
                            verPonto = new JComboBox(pontosInt.toArray());
                            verPonto.setBounds(730, 100, 200, 25);
                            verPonto.setBackground(Color.BLACK);
                            verPonto.setForeground(Color.WHITE);
                            buttonVerPonto =new JButton("Visualizar ponto");
                            buttonVerPonto.setBounds(730,135,200,25);
                            buttonVerPonto.setBackground(Color.BLACK);
                            buttonVerPonto.setForeground(Color.WHITE);
                            
                         
                            //BOTÕES PARA MOSTRAR LISTA DE LOCAIS
                            locCres =new JButton("Mostrar locais por ordem crescente");
                            locCres.setBounds(500,200, 250, 40);
                            locCres.setBackground(Color.BLACK);
                            locCres.setForeground(Color.WHITE);
                            locDec=new JButton("Mostrar locais por ordem decrescente");
                            locDec.setBounds(250,200, 250, 40);
                            locDec.setBackground(Color.BLACK);
                            locDec.setForeground(Color.WHITE);
                            locBut=new JButton("Mostrar locais ");
                            locBut.setBounds(50, 200, 200, 40);
                            locBut.setBackground(Color.BLACK);
                            locBut.setForeground(Color.WHITE);
                            locFamous=new JButton("Mostrar locais famosos");
                            locFamous.setBounds(750,200, 200, 40);
                            locFamous.setBackground(Color.BLACK);
                            locFamous.setForeground(Color.WHITE);
                            //BOTÕES PARA MOSTRAR LISTA DE PONTOS DE INTERESSE
                            pontosCres =new JButton("Mostrar Pontos por ordem crescente");
                            pontosCres.setBounds(1620,200, 250, 40);
                            pontosCres.setBackground(Color.BLACK);
                            pontosCres.setForeground(Color.WHITE);
                            pontosDec=new JButton("Mostrar Pontos por ordem decrescente");
                            pontosDec.setBounds(1370,200, 250, 40);
                            pontosDec.setBackground(Color.BLACK);
                            pontosDec.setForeground(Color.WHITE);
                            pontosBut=new JButton("Mostrar Pontos de Interesse ");
                            pontosBut.setBounds(1170, 200, 200, 40);
                            pontosBut.setBackground(Color.BLACK);
                            pontosBut.setForeground(Color.WHITE);
                            pontosFamous=new JButton("Mostrar pontos mais famosos");
                            pontosFamous.setBounds(950,200, 220, 40);
                            pontosFamous.setBackground(Color.BLACK);
                            pontosFamous.setForeground(Color.WHITE);
                            //BOTAO PARA VISUALIZAR VIAGENS
                            buttonViagens =new JButton("Visualizar Viagens Possiveis");
                            buttonViagens.setBounds(850,250,200,25);
                            buttonViagens.setBackground(Color.BLACK);
                            buttonViagens.setForeground(Color.WHITE);
                            //BOTAO PARA NOVA VIAGEM
                            buttonViagem =new JButton("Gerar Nova Viagem");
                            buttonViagem.setBounds(850,900,200,45);
                            buttonViagem.setBackground(Color.BLACK);
                            buttonViagem.setForeground(Color.WHITE);
                            //BOTAO PARA VISUALIZAR VIAGEM
                            buttonVerViagem =new JButton("Ver a minha Viagem");
                            buttonVerViagem.setBounds(650,900,200,45);
                            buttonVerViagem.setBackground(Color.BLACK);
                            buttonVerViagem.setForeground(Color.WHITE);
                            //BOTAO PARA VIAGENS PESSOAIS
                            buttonViagensPessoais =new JButton("Ver Viagens Pessoais");
                            buttonViagensPessoais.setBounds(1050,900,200,45);
                            buttonViagensPessoais.setBackground(Color.BLACK);
                            buttonViagensPessoais.setForeground(Color.WHITE);
                            //BOTAO PARA VISUALIZAR VIAGENS CRESCENTE DE PRECO
                            buttonViagensCres =new JButton("Custo Crescente");
                            buttonViagensCres.setBounds(650,250,200,45);
                            buttonViagensCres.setBackground(Color.BLACK);
                            buttonViagensCres.setForeground(Color.WHITE);
                            //BOTAO PARA VISUALIZAR VIAGENS DECRESCENTE DE PRECO
                            buttonViagensDec =new JButton("Custo Decrescente");
                            buttonViagensDec.setBounds(1050,250,200,45);
                            buttonViagensDec.setBackground(Color.BLACK);
                            buttonViagensDec.setForeground(Color.WHITE);
                            
                            //BOTAO SEM ORCAMENTO
                            orcamento=new JButton("Gerar Viagem Sem Orçamento");
                            orcamento.setBounds(1250,900,225,45);
                            orcamento.setBackground(Color.BLACK);
                            orcamento.setForeground(Color.WHITE);
                            //BOTAO SEM ORCAMENTO
                            buttonViagensSemPref=new JButton("Gerar Viagem Sem Preferencias");
                            buttonViagensSemPref.setBounds(425,900,225,45);
                            buttonViagensSemPref.setBackground(Color.BLACK);
                            buttonViagensSemPref.setForeground(Color.WHITE);
                            //BOTÃO PARA LIMPAR TUDO
                            buttonSair =new JButton("Sair");
                            buttonSair.setBounds(1750,910,150,35);
                            buttonSair.setBackground(Color.BLACK);
                            buttonSair.setForeground(Color.WHITE);
                            
                            
                            
                            
                             //SELECIONAR PREFERENCIA DE LOCAL
                            labelLocaisPref= new JLabel("Quais os seus Locais preferidos? Prima CTRL para selecionar multiplos");
                            labelLocaisPref.setBounds(1010, 10, 400, 20);
                            labelLocaisPref.setForeground(Color.WHITE);
                            newLocais = new JList(locais);
                            newLocais.setVisibleRowCount(5);
                            newLocais.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                            newLocais.setBackground(Color.BLACK);
                            newLocais.setForeground(Color.WHITE);
                            JScrollPane scrollPane = new JScrollPane(newLocais);
                            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                            scrollPane.setBounds(1110,40,200,120);
                            
                            buttonPrefLoc =new JButton("Colocar Preferencias");
                            buttonPrefLoc.setBounds(1110,160,200,25);
                            buttonPrefLoc.setBackground(Color.BLACK);
                            buttonPrefLoc.setForeground(Color.WHITE);
                            //SELECIONAR PREFERENCIA DE PONTOS DE INTERESSE
                            labelPontosPref= new JLabel("Quais os seus pontos preferidos? Prima CTRL para selecionar multiplos");
                            labelPontosPref.setBounds(1460, 10, 400, 20);
                            labelPontosPref.setForeground(Color.WHITE);
                            pontos = new JList(pontosInt.toArray());
                            pontos.setVisibleRowCount(5);
                            pontos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                            pontos.setBackground(Color.BLACK);
                            pontos.setForeground(Color.WHITE);
                            JScrollPane scrollPanePontos = new JScrollPane(pontos);
                            scrollPanePontos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                            scrollPanePontos.setBounds(1510,40,300,120);
                            buttonPrefPonto =new JButton("Colocar Preferencias");
                            buttonPrefPonto.setBounds(1510,160,300,25);
                            buttonPrefPonto.setBackground(Color.BLACK);
                            buttonPrefPonto.setForeground(Color.WHITE);
                           
                            
                            
                            
                            //CRIAR TEXT AREAS
                            ////PARA LOCAIS
                            labelAreaLoc=new JLabel("ÁREA DE LOCAIS");
                            labelAreaLoc.setBounds(250,280,200,20);
                            labelAreaLoc.setForeground(Color.WHITE);
                            areaLocais = new JTextArea();
                            areaLocais.setSize(500,600);
                            Color c = new Color(0,0,0,200);
                            areaLocais.setBackground(c);
                            areaLocais.setForeground(Color.WHITE);
                            JScrollPane scroll = new JScrollPane(areaLocais);
                            areaLocais.setEditable(false);
                            areaLocais.setBackground(VERY_DARK_GREY);
                            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                            scroll.setBounds(50,300, 500,600);
                            ////PARA PONTOS DE INTERESSE
                            labelAreaPontos=new JLabel("ÁREA DE PONTOS DE INTERESSE");
                            labelAreaPontos.setBounds(1550,280,200,20);
                            labelAreaPontos.setForeground(Color.WHITE);
                             areaPontos = new JTextArea();
                            areaPontos.setSize(500,600);
                            areaPontos.setBackground(VERY_DARK_GREY);
                            areaPontos.setForeground(Color.WHITE);
                            JScrollPane scroll2 = new JScrollPane(areaPontos);
                            areaPontos.setEditable(false);
                            scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                            scroll2.setBounds(1370,300, 500,600);
                            ////PARA VIAGENS
                            Color c2 = new Color(0,0,0,180);
                            labelAreaViagens=new JLabel("ÁREA DE VIAGENS");
                            labelAreaViagens.setBounds(900,280,200,20);
                            labelAreaViagens.setForeground(Color.WHITE);
                            areaViagens = new JTextArea();
                            areaViagens.setSize(500,600);
                            areaViagens.setForeground(Color.WHITE);
                            JScrollPane scroll3 = new JScrollPane(areaViagens);
                            areaViagens.setBackground(VERY_DARK_GREY);
                            areaViagens.setEditable(false);
                            scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                            scroll3.setBounds(650,300, 600,600);
                            
                            
                            areaLocais.setVisible(true);          
                           //CRIAR PAINEL 2
                            panel2= new JPanel(); 
                            panel2.setLayout(null);
                            panel2.add(labelLocaisPref);
                            panel2.add(scrollPane);
                            panel2.add(scrollPanePontos);
                            scrollPane.setVisible(true);
                            panel2.add(locCres);
                            panel2.add(locDec);
                            panel2.add(locBut);
                            panel2.add(pontosCres);
                            panel2.add(pontosDec);
                            panel2.add(pontosBut);
                            panel2.add(buttonPrefLoc);
                            panel2.add(buttonPrefPonto);
                            panel2.add(locFamous);
                            panel2.add(verLoc);
                            panel2.add(labelVerLoc);
                            panel2.add(pontosFamous);
                            panel2.add(buttonVerLoc);
                            panel2.add(labelPontosPref);
                            panel2.add(verPonto);
                            panel2.add(labelVerPonto);
                            panel2.add(buttonVerPonto);
                            panel2.add(buttonViagens);
                            panel2.add(buttonViagem);
                            panel2.add(buttonVerViagem);
                            panel2.add(buttonSair);
                            panel2.add(buttonViagensDec);
                            panel2.add(buttonViagensCres);
                            panel2.add(buttonViagensPessoais);
                            panel2.add(buttonEscolher);
                            panel2.add(scroll);
                            panel2.add(scroll2);
                            panel2.add(scroll3);
                            panel2.add(labelAreaPontos);
                            panel2.add(labelAreaLoc);
                            panel2.add(labelAreaViagens);
                            panel2.add(labelEscolher);
                            panel2.add(escolher);
                            panel2.add(orcamento);
                            panel2.add(buttonViagensSemPref);
                            
                             if (aluno.getNot()== null){
                                panel2.add(labelHot);
                                panel2.add(hot);
                                panel2.add(buttonHot);
                                buttonHot.addActionListener(new ButtonActionListener());
                                
                            }
                            else{
                                panel2.add(labelNot);
                                panel2.add(not);
                                panel2.add(buttonNot);
                                buttonNot.addActionListener(new ButtonActionListener());
                            }
                            locCres.addActionListener(new ButtonActionListener());
                            locDec.addActionListener(new ButtonActionListener());
                            locBut.addActionListener(new ButtonActionListener());
                            pontosCres.addActionListener(new ButtonActionListener());
                            pontosDec.addActionListener(new ButtonActionListener());
                            pontosBut.addActionListener(new ButtonActionListener());
                            buttonPrefLoc.addActionListener(new ButtonActionListener());
                            locFamous.addActionListener(new ButtonActionListener());
                            pontosFamous.addActionListener(new ButtonActionListener()); 
                            buttonVerLoc.addActionListener(new ButtonActionListener());
                            buttonPrefPonto.addActionListener(new ButtonActionListener());
                            buttonVerPonto.addActionListener(new ButtonActionListener());
                            buttonViagem.addActionListener(new ButtonActionListener());
                            buttonVerViagem.addActionListener(new ButtonActionListener());
                            buttonViagens.addActionListener(new ButtonActionListener());
                            buttonSair.addActionListener(new ButtonActionListener());
                            buttonViagensCres.addActionListener(new ButtonActionListener());
                            buttonViagensDec.addActionListener(new ButtonActionListener());
                            buttonViagensPessoais.addActionListener(new ButtonActionListener());
                            buttonEscolher.addActionListener(new ButtonActionListener());
                            orcamento.addActionListener(new ButtonActionListener());
                            buttonViagensSemPref.addActionListener(new ButtonActionListener());
                            buttonViagensSemPref.setEnabled(false);
                            buttonViagem.setEnabled(false);
                            orcamento.setEnabled(false);
                            buttonViagens.setEnabled(false);
                            buttonViagensPessoais.setEnabled(false);
                            buttonViagensCres.setEnabled(false);
                            buttonViagensDec.setEnabled(false);
                            buttonVerViagem.setEnabled(false);
                            buttonPrefLoc.setEnabled(false);
                            buttonPrefPonto.setEnabled(false);
                            buttonEscolher.setEnabled(false);
                            //panel2.add(new JPanelWithBackground("imagem2.jpg"));
                            JPanelWithBackground bg= new JPanelWithBackground("imagem6.jpg");
                            bg.setBounds(0,0,1950,1000);
                            panel2.add(bg);
                            this.add(panel2);
                            this.setTitle("Planner de Viagens");
                            
                            
                            
                           
                            
}


    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        FrameLogin log=new FrameLogin();
        
        log.criaPainelLogin();
        log.setSize(700, 300);
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setVisible(true);
        
        
        
      
    }
    
}
