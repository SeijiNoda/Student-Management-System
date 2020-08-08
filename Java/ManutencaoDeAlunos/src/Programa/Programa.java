package Programa;

import Fila.Fila;
import Resultado.*;
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

        Resultado resultado;
        String respostaWeb;

        Scanner leitor = new Scanner(System.in);
        Fila<Resultado> resultados = new Fila<Resultado>();
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

                resultado = new Resultado(ra, codigoDisciplina, nota, frequencia);
                resultados.guardeUmItem(resultado);
            }

            while(!resultados.isVazia())
            {
                resultado = resultados.recupereUmItem();
                respostaWeb = (String) ClienteWS.getObjeto(Object.class, "http://localhost:3000/main", resultado.getRa()+"", resultado.getCodDisciplina()+"", resultado.getNota()+"", resultado.getFrequencia()+"");
                if(respostaWeb.equals("Sucesso"))
                    respostaWeb = "SUCESSO!";
                if(respostaWeb.equals("Operacao mal sucedida: RequestError: Violation of PRIMARY KEY constraint 'PK__Resultad__E391926C46486B8E'. Cannot insert duplicate key in object 'BD19162.Resultado_ed'."))
                    respostaWeb = "OPERAÇÃO MAL-SUCEDIDA: Este aluno já foi registrado!";
                if(respostaWeb.equals("Operacao mal sucedida: RequestError: The INSERT statement conflicted with the FOREIGN KEY constraint \"fk_ra03\". The conflict occurred in database \"BD19162\", table \"BD19162.Aluno_ed\", column 'ra'."))
                    respostaWeb = "OPERAÇÃO MAL-SUCEDIDA: Este aluno não existe!";
                if(respostaWeb.equals("Operacao mal sucedida: RequestError: The INSERT statement conflicted with the FOREIGN KEY constraint \"fk_codDisciplina03\". The conflict occurred in database \"BD19162\", table \"BD19162.Disciplina_ed\", column 'codDisciplina'."))
                    respostaWeb = "OPERAÇÃO MAL-SUCEDIDA: Esta disciplina não existe!";
                if(respostaWeb.equals("Operacao mal sucedida: RequestError: Sem matricula para deletar."))
                    respostaWeb = "OPERAÇÃO MAL-SUCEDIDA: Este aluno não foi matriculado!";
                if(respostaWeb.equals("Operacao mal sucedida: RequestError: Arithmetic overflow error for data type smallint, value = " + resultado.getRa() + "."))
                    respostaWeb = "OPERAÇÃO MAL-SUCEDIDA: RA inválido!";

                System.out.println(resultados.recupereUmItem() + "STATUS: " + respostaWeb + "\n"); //Insere a resposta do WebService também
                resultados.removaUmItem();
            }
        }
        catch(Exception erro)
        {
            System.err.println("Erro ao analisar a matrícula");
        }
    }
}