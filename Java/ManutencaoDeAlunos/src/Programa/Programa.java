package Programa;

import Fila.Fila;
import Matricula.*;
import ClienteWS.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Programa
{
    public static void main(String[] args)
    {
        int ra;
        int codigoDisciplina;
        float nota;
        float frequencia;

        Matricula matricula;
        String respostaWeb = "";

        Scanner leitor = new Scanner(System.in);
        Fila<Matricula> matriculas = new Fila<Matricula>();
        boolean querProsseguir = true;

        try
        {
            System.out.println("//--------------MANUTENÇÃO DE MATRÍCULAS--------------\\\\");
            System.out.println("                                           , ,\n" +
                    "                                         ,','\n" +
                    "                                        ; ;\n" +
                    "                                        `.`.\n" +
                    "                                          ) ;\n" +
                    "                                     ,,,-','\n" +
                    "                      _____,,,,---''\",,,-'\n" +
                    "            ___,,--'\"\"_____,,,,--''\"\"\n" +
                    "    __,,--'\"__,,--'\"\"\"\n" +
                    " ,-\"_,,--'\"\"\n" +
                    "; ,'               .,------,....___\n" +
                    "; ;               /       ;        \"\"\"`---.._\n" +
                    "`.``-.._____,,,,,/       ;                   \"\"``.\n" +
                    "  ``--...___;;;;/-\"\"\"\"\"-;                         \n" +
                    "            ```;        ;                         ;;\n" +
                    "              ;        ;                         / ;\n" +
                    "             ;\"----....;___                     ; ;;\n" +
                    "             ;-,,,,,___    \"\"`\"--..._         ,' ; ;\n" +
                    "             ;         \"\"\"\"``---...__\"\"-...,-' ,'  ;\n" +
                    "             ;                       \"`-....,-'   /\n" +
                    "             `-._     _-------_                 ,'\n" +
                    "                 \"`--'\"\"\"\"\"\"\"\"\"``--..        ,,'\n" +
                    "                                     \"\"`---'\"\n\n");


            while(querProsseguir)
            {
                System.out.print("Digite o RA: ");
                try
                {
                    ra = leitor.nextInt();
                }
                catch(Exception ex)
                {
                    System.err.println("RA inválido! Tente novamente!");
                    return;
                }

                System.out.print("Digite o código da disciplina: ");
                try
                {
                    codigoDisciplina = leitor.nextInt();
                }
                catch(Exception ex)
                {
                    System.err.println("Código da disciplina inválido! Tente novamente!");
                    return;
                }

                System.out.print("Digite a nota de 0 a 10: ");
                try
                {
                    nota = leitor.nextFloat();
                    if(nota < 0 || nota > 10)
                        throw new Exception("nota inválida");
                }
                catch(Exception ex)
                {
                    System.err.println("Nota inválida! Tente novamente!");
                    return;
                }

                System.out.print("Digite a frequência de 0,0 a 1,0: ");
                try
                {
                    frequencia = leitor.nextFloat();
                    if(frequencia < 0.0 || frequencia > 1.0)
                        throw new Exception("frequencia inválida");
                }
                catch(Exception ex)
                {
                    System.err.println("Frequência inválida! Tente novamente!");
                    return;
                }
                System.out.print("\n");

                for(;;)
                {
                    try
                    {
                        System.out.print("Deseja prosseguir (S/N): ");
                        String resposta = leitor.next();
                        resposta = resposta.toUpperCase();

                        if(resposta.equals("N"))
                            querProsseguir = false;
                        else
                            if(!resposta.equals("S"))
                                throw new Exception("resposta inválida");

                        System.out.print("\n");

                        break;
                    }
                    catch(Exception ex)
                    {
                        System.err.println("Resposta inválida! Tente novamente!\n");
                        TimeUnit.SECONDS.sleep(1);
                    }
                }

                matricula = new Matricula(ra, codigoDisciplina, nota, frequencia);
                matriculas.guardeUmItem(matricula);
            }

            //WEBSERVICE AQUI

            while(!matriculas.isVazia())
            {
                System.out.println(matriculas.recupereUmItem() + "STATUS: " + respostaWeb + "\n"); //Insere a resposta do WebService também
                matriculas.removaUmItem();
            }
        }
        catch(Exception erro)
        {
            System.err.println("Erro ao analisar a matrícula");
        }
    }
}