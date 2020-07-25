package Programa;

import Matricula.*;
import ClienteWS.*;

import java.util.Scanner;

public class Programa
{
    public static void main(String[] args)
    {
        int ra = -1;
        int codigoDisciplina = -1;
        float nota = -1;
        float frequencia = -1;

        Scanner leitor = new Scanner(System.in);
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
                            throw new Exception("resposta inválida");

                        break;
                    }
                    catch(Exception ex)
                    {
                        System.err.println("Resposta inválida! Tente novamente!\n");
                    }
                }
            }
        }
        catch(Exception erro)
        {
            System.err.println("Erro ao analisar a matrícula");
        }
    }
}