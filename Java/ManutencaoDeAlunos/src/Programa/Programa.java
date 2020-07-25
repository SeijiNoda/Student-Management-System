package Programa;

public class Programa
{
    public static void main(String[] args)
    {
        try
        {
            Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "http://api.postmon.com.br/v1/cep/13098393");
            System.out.println("\n\n");
            System.out.println(logradouro);
        }
        catch(Exception erro)
        {
            System.err.println(erro.getMessage());
        }
    }
}