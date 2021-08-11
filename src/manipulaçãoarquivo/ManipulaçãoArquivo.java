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
import java.util.Scanner;

public class ManipulaçãoArquivo {
    
    public static void validaEntrada(String nome,String telefone, File file) throws FileNotFoundException, IOException{
    
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));
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
            novoContato(nome, telefone, file);                
        }               
    }
    
    public static void novoContato(String nome,String telefone, File file){
                  
        try{
            
            FileWriter arquivo = new FileWriter(file,true);
            BufferedWriter escritaArq = new BufferedWriter(arquivo);
        
            String linha = nome+";"+telefone;
        
            escritaArq.append(linha+"\n");
        
            escritaArq.close();
            arquivo.close();
                      
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
        
        Scanner teclado = new Scanner(System.in);
        
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
          
            String linha = br.readLine();
            ArrayList<String> salvar = new ArrayList();
              
            boolean confirma= false;
            boolean tem = false;
            
            while(linha != null){
                
                String[] separa = linha.split(";"); 
                
                if(excluirContato.equalsIgnoreCase(separa[0])== true){                                                             
                    tem = true;
                }
                    confirma = true;    
                    
                    if(separa[0].equalsIgnoreCase(excluirContato)== false){
                        confirma = true;
                        salvar.add(linha);                     
                    }                                                
                                    
                linha = br.readLine();  
            }
            
            if(tem == false){
                System.out.println("Contato não existe!");
                confirma = false;
            }            
            
            br.close();
            fr.close();
               
            String opcao = "";
            if(confirma == true){
                System.out.println("Deseja realmente apagra o contato? 1-Sim ou 0-Não");
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
        
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));  
                
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
                System.out.println("Informe o contato que desenha excluir:");
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
            
            default:
                System.out.println("Encerrando..");
                                    
            }        
    }
    
    public static void main(String[] args) throws IOException{
                
        Scanner teclado = new Scanner(new BufferedInputStream(System.in)); 
        File file = new File("agenda.txt");
        
        if(file.exists()){
            System.out.println("------------------------------------------");   
        
            System.out.println("Informe a Opção: \n1-Novo Contato \n2-Pesquisa \n3-Excluir \n4-Listar Contatos \n0-SAIR\n");
            String opcao = teclado.nextLine();
                
            boolean valida = true;
            while(valida){
                if(opcao.length()>1){
                    System.out.println("Entrada invalida, informe novamente:");
                    opcao = teclado.nextLine();
                }else{
                    valida=false;
            }
        }    
        
            menu(opcao,file);
            
        }else{
            System.out.println("Arquivo não Existe!!");
        }
        
    }
}