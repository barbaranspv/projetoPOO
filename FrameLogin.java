/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planoviagem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *Criação de frame para login na aplicação
 * @author barba
 */
public class FrameLogin extends JFrame {
        private JLabel labelAlunos, labelNome,labelIdade,labelNum,labelOrcamento,label;
        private JTextField nomeAluno,numAluno,idadeAluno,orcamentoAluno;
        private JButton buttonAction;
        private JPanel panel;
        private JComboBox <String> jComboBoxAlunos;
        private Ficheiros f= new Ficheiros();
        
        
        public FrameLogin(){
        }
        
        protected void escondeFrame(){
            this.setVisible(false);
        }
        private class ButtonActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
               
               if(e.getSource() == buttonAction) {
                    if((jComboBoxAlunos.getSelectedIndex() == 0) && (!"".equals(nomeAluno.getText())) && (!"".equals(numAluno.getText())) && (!"".equals(idadeAluno.getText())) && (!"".equals(orcamentoAluno.getText()))) {
                        
                        try{
     

                            PlanoViagem plano=new PlanoViagem();
                            plano.setAlunoMestrado( new AlunoMestrado(nomeAluno.getText(), Integer.parseInt(numAluno.getText()),Integer.parseInt(idadeAluno.getText()),Float.parseFloat(orcamentoAluno.getText())));
                            
                            try {
                               
                                escondeFrame();
                                JOptionPane.showMessageDialog(null, "Login Efetuado!", "AVISO", JOptionPane.PLAIN_MESSAGE); 
                                plano.criaFrame();
                            } catch (IOException ex) {
                                Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
                            }


                            }catch(NumberFormatException ex){
                                 JOptionPane.showMessageDialog(null, "Verifique os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
                                //not int
                            }
                        
                        
                    }
                       
                    
                     else if(jComboBoxAlunos.getSelectedIndex() == 1 && (!"".equals(nomeAluno.getText())) && (!"".equals(numAluno.getText())) && (!"".equals(idadeAluno.getText())) && (!"".equals(orcamentoAluno.getText()))){
                       try{
                            PlanoViagem plano=new PlanoViagem();
                            plano.setAlunoLicenciatura( new AlunoLicenciatura(nomeAluno.getText(), Integer.parseInt(numAluno.getText()),Integer.parseInt(idadeAluno.getText()),Float.parseFloat(orcamentoAluno.getText())));
                            
                            try {
                                
                                escondeFrame();
                                JOptionPane.showMessageDialog(null, "Login Efetuado!", "AVISO", JOptionPane.PLAIN_MESSAGE);
                                plano.criaFrame();
                            } catch (IOException ex) {
                                Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
                            }


                            }catch(NumberFormatException ex){
                                 JOptionPane.showMessageDialog(null, "Verifique os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
                            }
                   
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Tem um ou mais campos em falta!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        
                    }}
                    }
          }
            
    /**
     *Cria painel login
     * @throws IOException
     */
    protected void criaPainelLogin() throws IOException{
            Color VERY_DARK_GREY = new Color(51,51,51);
            label = new JLabel("Viagens DEI");
            label.setFont(new Font("Nirmala UI", Font.BOLD, 40));
            label.setForeground(Color.WHITE);
            label.setBounds(30,0,250,100);
               ///Criar botoes etc
               
            labelNome= new JLabel("Qual o seu nome?");
            labelNome.setBounds(50, 70, 200, 25);
            
            nomeAluno=new JTextField();
            nomeAluno.setBounds(260, 70, 200, 25);
            nomeAluno.setBackground(VERY_DARK_GREY);
            nomeAluno.setForeground(Color.WHITE);
            nomeAluno.setCaretColor(Color.WHITE);
            labelNome.setForeground(Color.WHITE);
            
            labelNum= new JLabel("Qual o seu número de Aluno?");
            labelNum.setBounds(50, 100, 200, 25);
            numAluno=new JTextField();
            numAluno.setBounds(260, 100, 200, 25);
            numAluno.setCaretColor(Color.WHITE);
            numAluno.setBackground(VERY_DARK_GREY);
            labelNum.setForeground(Color.WHITE);
            numAluno.setForeground(Color.WHITE);
            labelIdade= new JLabel("Qual a sua Idade?");
            labelIdade.setBounds(50, 130, 200, 25);
            idadeAluno=new JTextField();
            idadeAluno.setCaretColor(Color.WHITE);
            idadeAluno.setBounds(260, 130, 200, 25);
            idadeAluno.setBackground(VERY_DARK_GREY);
            labelIdade.setForeground(Color.WHITE);
            idadeAluno.setForeground(Color.WHITE);

            labelOrcamento= new JLabel("Qual o seu orçamento?");
            labelOrcamento.setBounds(50, 160, 200, 25);
            orcamentoAluno=new JTextField();
            orcamentoAluno.setCaretColor(Color.WHITE);
            orcamentoAluno.setBounds(260, 160, 200, 25);
            orcamentoAluno.setBackground(VERY_DARK_GREY);
            labelOrcamento.setForeground(Color.WHITE);
            orcamentoAluno.setForeground(Color.WHITE);
            labelAlunos= new JLabel("É Aluno de Mestrado ou Licenciatura?");
            String[] alunos ={"Aluno de Mestrado","Aluno de Licenciatura"};
            jComboBoxAlunos= new JComboBox(alunos);
            jComboBoxAlunos.setBounds(490,70,145,25);
            jComboBoxAlunos.setBackground(VERY_DARK_GREY);
            labelAlunos.setForeground(Color.WHITE);
            jComboBoxAlunos.setForeground(Color.WHITE);
            
            buttonAction =new JButton("Entrar");
            buttonAction.setBounds(490,105,145,25);
            buttonAction.setBackground(VERY_DARK_GREY);
            buttonAction.setForeground(Color.WHITE);
            
               
            //CRIAR PAINEL
            panel = new JPanel();
            panel.setLayout(null);

            panel.add(labelAlunos);
            panel.add(jComboBoxAlunos);
            panel.add(labelNome);
            panel.add(nomeAluno);
            panel.add(labelNum);
            panel.add(label);
            panel.add(numAluno);
            panel.add(labelIdade);
            panel.add(idadeAluno);
            panel.add(labelOrcamento);
            panel.add(orcamentoAluno);
            panel.add(buttonAction);
            buttonAction.addActionListener(new FrameLogin.ButtonActionListener());
            jComboBoxAlunos.addActionListener(new FrameLogin.ButtonActionListener());
            panel.setBackground(Color.BLACK);
            panel.setForeground(Color.WHITE);
           
            this.setTitle("LOGIN");
            this.setResizable(false);
            JPanelWithBackground bg= new JPanelWithBackground("imagem8.jpg");
            bg.setBounds(0,0,900,300);
            panel.add(bg);
           this.add(panel);
}}
