/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;
import org.json.JSONObject;
import org.json.JSONException;

/**
 *
 * @author USER
 */
public class TPersona {
    private long Cedula;
    private String Apellidos;
    private String Nombres;
    private byte Edad;
    private float Estatura;
    
    public TPersona(){
        Cedula = 0;
        Apellidos = "";
        Nombres = "";
        Edad = 0;
        Estatura = 0;
    }
    
    public void setCedula(long Ced){
        if(Ced > 0){
            Cedula = Ced;
        }
    }
    
    public void setApellidos(String Ape){
        Apellidos = Ape.trim();
    }
    
    public void setNombres(String Nom){
        Nombres = Nom.trim();
    }
    
    public void setEdad(byte Eda){
        if(Eda >= 0 && Eda <= 110){
            Edad = Eda;
        }
    }
    
    public void setEstatura(float Est){
        if(Est >= 0 && Est <= 2.15){
        Estatura = Est;
    }
  }
    public long getCedula(){
        return Cedula;
    }
    
    public String getApellidos(){
        return Apellidos;
    }
    
    public String getNombres(){
        return Nombres;
    }
    
    public byte getEdad(){
        return Edad;
    }
    
    public float getEstatura(){
        return Estatura;
    }
    
    // genera cadena en formato JSON de los valores de los atributos de una persona
    public String TextoJson(EOperacion Opr){
        JSONObject Json;
        try{
            Json = new JSONObject();
            Json.put("Cedula", Cedula);
            Json.put("Apellidos",Apellidos);
            Json.put("Nombres", Nombres);
            Json.put("Edad", Edad);
            Json.put("Estatura", Estatura);
            Json.put("Operacion",Opr + "");
            return Json.toString(1);//identacion (sangria) de 1
        }
        catch(JSONException Error){
            Error.printStackTrace();
            return null;
        }
    }
    
    public boolean LeerPer(JSONObject Json){
        try{
            Cedula = Json.getLong("Cedula");
            Apellidos = Json.getString("Apellidos");
            Nombres = Json.getString("Nombres");
            Edad = (byte)Json.getInt("Edad");
            Estatura =(float)Json.getDouble("Estatura");
            return true;
        }
        catch(JSONException Error){
            Error.printStackTrace();
            return false;
        }
    }
}
