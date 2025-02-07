package ClienteWS;

import java.io.*;
import java.net.*;
import org.codehaus.jackson.*;
import org.codehaus.jackson.map.*;

public class ClienteWS
{
    public static Object getObjeto (Class tipoObjetoRetorno,
                                    String urlWebService,
                                    String... parametros)      // "..." significa que é possível passar VÁRIOS parâmetros - VETOR, mas somente APÓS passar os parâmetros com VÍRGULA
    {
        Object objetoRetorno = null;

        try
        {
            for(String parametro: parametros) //foreach
                urlWebService = urlWebService + "/" + parametro.replaceAll(" ", "%20");

            URL url = new URL(urlWebService);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            //connection.setRequestProperty("login", "seulogin");
            //connection.setRequestProperty("senha", "suasenha");
            connection.connect();

            String responseJson = inputStreamToString(connection.getInputStream());
            connection.disconnect();

            return fromJson(responseJson, tipoObjetoRetorno);
        }
        catch (Exception erro)
        {
            erro.printStackTrace();
        }

        return objetoRetorno;
    }

    public static Object postObjeto (Object objetoEnvio,
                                     Class tipoObjetoRetorno,
                                     String urlWebService)
    {
        Object objetoRetorno = null;

        try
        {
            String requestJson = toJson(objetoEnvio);

            URL url = new URL(urlWebService);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            //connection.setRequestProperty("login", "seulogin");
            //connection.setRequestProperty("senha", "suasenha");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(requestJson.length()));

            DataOutputStream stream =
                    new DataOutputStream (connection.getOutputStream());
            stream.write (requestJson.getBytes("UTF-8"));
            stream.flush ();
            stream.close ();
            connection.connect ();

            String responseJson = inputStreamToString (connection.getInputStream());
            connection.disconnect();
            objetoRetorno = fromJson (responseJson, tipoObjetoRetorno);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return objetoRetorno;
    }

    /*public static Object putObjeto   (Object objetoEnvio,
                                     Class tipoObjetoRetorno,
                                     String urlWebService)
    {
        Object objetoRetorno = null;

        try
        {
            String requestJson = toJson(objetoEnvio);

            URL url = new URL(urlWebService);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            //connection.setRequestProperty("login", "seulogin");
            //connection.setRequestProperty("senha", "suasenha");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(requestJson.length()));

            DataOutputStream stream =
                    new DataOutputStream (connection.getOutputStream());
            stream.write (requestJson.getBytes("UTF-8"));
            stream.flush ();
            stream.close ();
            connection.connect ();

            String responseJson = inputStreamToString (connection.getInputStream());
            connection.disconnect();
            objetoRetorno = fromJson (responseJson, tipoObjetoRetorno);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return objetoRetorno;
    }*/

    /*public static Object deleteObjeto (Class tipoObjetoRetorno,
                                    String urlWebService,
                                    String... parametros)      // "..." significa que é possível passar VÁRIOS parâmetros - VETOR, mas somente APÓS passar os parâmetros com VÍRGULA
    {
        Object objetoRetorno = null;

        try
        {
            for(String parametro: parametros) //foreach
                urlWebService = urlWebService + "/" + parametro.replaceAll(" ", "%20");

            URL url = new URL(urlWebService);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(15000);
            //connection.setRequestProperty("login", "seulogin");
            //connection.setRequestProperty("senha", "suasenha");
            connection.connect();

            String responseJson = inputStreamToString(connection.getInputStream());
            connection.disconnect();

            return fromJson(responseJson, tipoObjetoRetorno);
        }
        catch (Exception erro)
        {
            erro.printStackTrace();
        }

        return objetoRetorno;
    }*/

    public static String inputStreamToString (InputStream is) throws IOException
    {
        if (is != null)
        {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try
            {
                Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1)
                {
                    writer.write(buffer, 0, n);
                }
            }
            finally
            {
                is.close();
            }

            return writer.toString();
        }
        else
        {
            return "";
        }
    }

    public static String toJson(Object objeto) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter jsonValue = new StringWriter();
        mapper.writeValue(new PrintWriter(jsonValue), objeto);
        return jsonValue.toString();
    }

    public static Object fromJson(String json, Class objectClass) throws Exception
    {
        JsonFactory f = new MappingJsonFactory();
        JsonParser jp = f.createJsonParser(json);
        Object obj = jp.readValueAs(objectClass);
        return obj;
    }
}