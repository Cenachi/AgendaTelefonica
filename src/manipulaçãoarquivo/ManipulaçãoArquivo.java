package manipulaçãoarquivo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ManipulaçãoArquivo {
     
    
    public static void validaEntrada(String nome,String telefone, File file) throws FileNotFoundException, IOException{
    
        Scanner teclado = new Scanner(System.in,"ISO-8859-1");
        String regex = "^\\([1-9]{2}\\)[0-9]{5}\\-[0-9]{4}$";  
        
        boolean inicia = true;
        
        while(inicia){            
            
            if(nome.length()>20 || nome.length()<5){
                System.out.println("Nome invalido, informe-o novamente nome e telefone:");
                nome = teclado.nextLine();
                telefone = teclado.nextLine();
            }else if(telefone.matches(regex)!=true){
                System.out.println("Telefone invalido, informe-o novamente nome e telefone:");
                nome = teclado.nextLine();
                telefone = teclado.nextLine();
            }else{
                inicia = false;
            }                     
        }   
        novoContato(nome, telefone, file);        
    }
    
    public static void novoContato(String nome,String telefone, File file){
                  
        try{
            
            FileWriter arquivo = new FileWriter(file,true);
            BufferedWriter escritaArq = new BufferedWriter(arquivo);
        
            String linha = nome+";"+telefone;
        
            escritaArq.append(linha+"\n");
        
            escritaArq.close();
            arquivo.close();
                      
           organizaAgenda(file);
            
        }catch(IOException ex){
            
        }
    }
    
    public static void organizaAgenda(File file){
        
        try{
            
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
          
            String linha = br.readLine();
            ArrayList<String> salvar = new ArrayList();
            
            
            while(linha != null){
                                               
                salvar.add(linha);
                
                Collections.sort(salvar);
                                    
                linha = br.readLine();  
            }                                                
          
            FileWriter fw2 = new FileWriter(file,true);
            fw2.close();

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i=0; i<salvar.size();i++){
                bw.write(salvar.get(i));
                bw.newLine();
            }                        

            bw.close();
            fw.close();
                  
                        
            String[] args = null;        
            main(args);
            
        }catch(IOException ex){
            
        }
        
    }
    
    public static void pesquisa(String nomePesquisa, File file){
        
        try{
            
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
          
            String linha = br.readLine();
                          
            boolean tem = false;
            
            while(linha != null){
                
                String[] separa = linha.split(";"); 
                
                if(nomePesquisa.equalsIgnoreCase(separa[0])== true){
                    System.out.println(linha);                                              
                    tem = true;
                }
                                
                linha = br.readLine();  
            }
            
            if(tem == false){
                System.out.println("Contato não existe!");
            }
            
            br.close();
            fr.close();
                                       
            String[] args = null;        
            main(args);
            
        }catch(IOException ex){
            
        }
    }
    
    public static void excluir(String excluirContato, File file){
        
        Scanner teclado = new Scanner(System.in,"ISO-8859-1");
        
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
          
            String linha = br.readLine();
            ArrayList<String> salvar = new ArrayList();
              
            boolean confirma= false;
            boolean existeContato = false;
            
            while(linha != null){
                
                String[] separa = linha.split(";"); 
                
                if(excluirContato.equalsIgnoreCase(separa[0])== true){                                                             
                    existeContato = true;
                }
                    confirma = true;    
                    
                    if(separa[0].equalsIgnoreCase(excluirContato)== false){
                        confirma = true;
                        salvar.add(linha);                     
                    }                                                
                                    
                linha = br.readLine();  
            }
            
            if(existeContato == false){
                System.out.println("Contato não existe!");
                confirma = false;
            }            
            
            br.close();
            fr.close();
               
            String opcao = "";
            if(confirma == true){
                System.out.println("Deseja realmente apagar o contato? 1-Sim ou 0-Não");
                opcao = teclado.nextLine();
                     
                boolean valida =true;    
                
                if((opcao.equalsIgnoreCase("1"))|| (opcao.equalsIgnoreCase("0"))){
                    valida=false;
                }                
                
                while(valida){
                    System.out.println("Digite apenas: 1-SIM ou 0-NÃO");
                    opcao = teclado.nextLine();
                    
                    if((opcao.equalsIgnoreCase("1"))|| (opcao.equalsIgnoreCase("0"))){
                        valida=false;
                    } 
                }                
            }
                                
            if(opcao.equalsIgnoreCase("1")){
                FileWriter fw2 = new FileWriter(file,true);
                fw2.close();
            
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
            
                for(int i=0; i<salvar.size();i++){
                    bw.write(salvar.get(i));
                    bw.newLine();
            }                        
                bw.close();
                fw.close();
                
                System.out.println("Contato apagado com sucesso...");
            }
                        
            String[] args = null;        
            main(args);
            
        }catch(IOException ex){
            
        }
    }
    
    public static void listarContatos(File file){
        
        try{
            
            FileReader arquivo = new FileReader(file);
            BufferedReader leituraArq = new BufferedReader(arquivo);
        
            String linha = leituraArq.readLine();        
            
            while(linha != null){
                System.out.println(linha);
                linha = leituraArq.readLine();
            }
        
            leituraArq.close();
            arquivo.close();
        
            String[] args = null;        
            main(args);
            
        }catch(IOException ex){
            
        }
    }
    
    public static void menu(String opcao, File file) throws IOException {
        
        Scanner teclado = new Scanner(System.in,"ISO-8859-1");  
                
            switch(opcao){
            case "1":
                System.out.println("------------------------------------------");
                System.out.println("Informe o seu nome e telefone:");
                               
                String nome = teclado.nextLine();
                String telefone = teclado.nextLine();
                
                validaEntrada(nome, telefone,file);                
            break;
            
            case "2":
                System.out.println("------------------------------------------");
                System.out.println("Informe o nome do contato:");
                String nomePesquisa = teclado.nextLine();
                
                pesquisa(nomePesquisa, file);
            break;
            
            case "3":
                System.out.println("------------------------------------------");
                System.out.println("Informe o contato que deseja excluir:");
                String excluirContato = teclado.nextLine();
                
                excluir(excluirContato,file);
            break;
            
            case "4":
                System.out.println("------------------------------------------");
                System.out.println("Lista de todos os contatos:\n");
                listarContatos(file);
            break;
            
            case "0":                
                System.out.println("------------------------------------------");
            break;   
            
            //Valida a entrada de um valor entre 5 e 9; 
            default:
                int convert = Integer.parseInt(opcao);
                while(convert>4){
                    System.out.println("Entrada invalida, informe novamente:\n1-Novo Contato \n2-Pesquisa \n3-Excluir \n4-Listar Contatos \n0-SAIR\n");
                    opcao = teclado.nextLine();
                    //Retorno para o menu;
                    menu(opcao, file);
                }  
            }                 
    }
    
    public static File arquivo = null;    // Variavel Global 'arquivo' inciada de null;
    
    public static void main(String[] args) throws IOException{
                
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));         
          
        JFileChooser chooser = new JFileChooser();        
       
        String finaliza= "";
              
        //Enquanto um arquivo não for selecionado, repete a janela de seleção do arquivo;
        while(arquivo == null){     
             
            JOptionPane.showMessageDialog(null,"Selecione o arquivo para gravar a Agenda");
            //Filtrando txt;
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Selecione apenas arquivo .txt", "txt");

            chooser.setFileFilter(filter); 

            // Pegando o endereço do arquivo atraves do JFileChooser;
            if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                arquivo = chooser.getSelectedFile();                 
            }
             
            //Caso o usuario feche ou não selecione um arquivo, a varivel permanecer null;
            if(arquivo == null){
                System.out.println("Deseja finalizar? 1-Sim 0-Não"); 
                String confirma = teclado.nextLine();              
                    
                boolean valida =true;    
                    
                // Se o usuario digitar a informação correta, 'valida' recebe false, assim não passa pelo while abaixo;
                if((confirma.equalsIgnoreCase("1"))|| (confirma.equalsIgnoreCase("0"))){ 
                    valida=false;  
                }                          
                    
                //Caso a variavel confirma receba outra informação, 'valida' permanece como true;
                while(valida){ 
                    System.out.println("Digite apenas: 1-SIM ou 0-NÃO");  
                    //Pede novamente a confirmação para o ususario;
                    confirma = teclado.nextLine();                   
                        
                    // Se o usuario digitar a informação correta, 'valida' recebe false, saindo então do while;
                    if((confirma.equalsIgnoreCase("1"))|| (confirma.equalsIgnoreCase("0"))){   
                        valida=false;   
                    } 
                }
                    
                if(confirma.equals("1")){      // Caso o usuario ao fechar o JFile, queira finalizar a aplicação, e digite 1;
                    arquivo = new File("0");  // O 'arquivo' recebe um valor qualquer para não retornar ao while;
                    finaliza = "0";          // E uma variavel 'finaliza' o valor de 0;
                }   
            
            } 
        }        
           
        //Varivel para opção do Menu;
        String opcao = "";         
            
        // Caso o 'finaliza' receber 0, significa que o usuario decidiu cancelar a execução;
        if(finaliza.equals("0")){   
            opcao = "0";    // 'opcao' recebe 0, pois é a opção de saida do algoritmo;
            
        // Se não significa que o arquivo foi selecionado e agora necessita ler a 'opcao';
        }else{            
            System.out.println("------------------------------------------");
            
            System.out.println("Informe a Opção: \n1-Novo Contato \n2-Pesquisa \n3-Excluir \n4-Listar Contatos \n0-SAIR\n");
            opcao = teclado.nextLine();
        }
                           
        boolean valida = true;  
            
        //Valida a entrada de palavras, numeros com mais de 1 digito e valores negativos;
        while(valida){
            if((opcao.length()>1)){                    
                System.out.println("Entrada invalida, informe novamente:");
                opcao = teclado.nextLine();
            }else{
                valida=false;
            }
        }    
        
        //Chamando meu método menu;
        menu(opcao,arquivo);                                
    }
}