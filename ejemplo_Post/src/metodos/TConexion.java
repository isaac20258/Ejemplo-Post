/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;
import java.net.URL;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.UnsupportedEncodingException;
/**
 *
 * @author USER
 */
public class TConexion {
    
    private HttpURLConnection Con;
    
    public TConexion(){
        Con = null;
    }
    
    public HttpURLConnection getConexion(){
        return Con;
    }
    
    public String getCampo(String Campo){
        return Con.getHeaderField(Campo);
    }
    
    public String getEstudiante(){
        String Est;
        Est = getCampo("Estudiante");
        try{
            if(Est!= null){
                Est = new String(Est.getBytes("ISO-8859-1"),"UTF-8");
            }
            else{
                Est = "Estudiante no encontrado";
            }
        }
        catch(UnsupportedEncodingException Error){
            Error.printStackTrace();
        }
        return Est;
    }
    
    public String UrlServicio(){// para peticion en formato Post
        return "http://pronet.somee.com/Personas.aspx";
    }
    
    public boolean Conectar(){
        URL U;
        try{
            U = new URL(UrlServicio());
            Con = (HttpURLConnection)U.openConnection();
            return true;
        }
        catch(MalformedURLException Error){
            Error.printStackTrace();
            return false;  
        }
        catch(IOException Error){
            Error.printStackTrace();
            return false;
        }
    }
    
    protected void Preparar(int Tamaño,long Cedula){
        try{
          Con.setRequestMethod("POST");
          Con.setRequestProperty("Codigo", Cedula + "");
          Con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
          Con.setRequestProperty("Content-Length", Tamaño + "");
          Con.setDoOutput(true);//habilitar la escritura hacia el servidor
        }
        catch(IOException Error){
            Error.printStackTrace();
            
        }
    }
    
    public void Consumir(long Ced,EOperacion Opr,TPersona Per){
        String Json;
        byte Vec[];// vector de byte a enviar (escritura) en la conexion con el servidor
        OutputStream OS;
        try{
            Json = "Persona=" + Per.TextoJson(Opr);// formato campo = valor de html
            Vec = Json.getBytes();//generamos un vector de bytes de los caracteres de un String
            Preparar(Vec.length,Ced);
            OS = Con.getOutputStream();//salida para escribir del lado del servidor
            OS.write(Vec,0,Vec.length);
            OS.flush();
            OS.close();
        }
        catch(IOException Error){
            Error.printStackTrace();
            
        }
    }
    
    public String RecibirRespuesta(){
        String Linea,Res;
        BufferedReader BR;
        Res = "";
        try{
            BR = new BufferedReader(new InputStreamReader(Con.getInputStream()));
            while((Linea =BR.readLine())!= null){
                Res = Res + Linea + "\n";
            }
            BR.close();
            Con.disconnect();
        }
        catch(IOException Error){
            Error.printStackTrace();
            Res = Error.getMessage();
            
        }
        
        return Res.trim();
    }
    
}
