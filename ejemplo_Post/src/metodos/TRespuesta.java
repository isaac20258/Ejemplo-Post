/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
/**
 *
 * @author USER
 */
public class TRespuesta {
    
    private JSONObject Res;
    
    public TRespuesta(String Json){
        try{
            Res = new JSONObject(Json);
            System.out.println(Json);
        }
        catch(JSONException Error){
            Res = null;
            Error.printStackTrace();
            
        }
    }
    
    public boolean getResultado(){
        try{
          return Res.getBoolean("Resultado");
        }
        catch(JSONException Error){
            Error.printStackTrace();
            return false;
            
        }
    }
    
    public String getMensaje(){
        try{
            return Res.getString("Mensaje");
        }
        catch(JSONException Error){
            Error.printStackTrace();
            return Error.getMessage();
            
        }
    }
    
    public JSONArray getPersonas(){
        String Campo;
        JSONArray Vec;
        Vec = null;
        try{
            Campo ="Personas";
            if(Res.has(Campo)){
                if(Res.get(Campo)instanceof JSONArray){
                    Vec = Res.getJSONArray(Campo);
                }
            }
        }
        catch(JSONException Error){
            Error.printStackTrace();
            
        }
        return Vec;
    }
    
    public int CantidadPer(){
        JSONArray Vec;
        Vec = getPersonas();
        if(Vec!= null){
            return Vec.length();
        }
        else{
            return 0;
        }
    }
    
    public TPersona ExtraerPer(){// equivalente a TPersona getPersona(int pos)
        TPersona Per;
        JSONArray Vec;
        Vec = getPersonas();
        if(Vec!= null){
            Per = new TPersona();
            if(Per.LeerPer((JSONObject)Vec.remove(0))){
                return Per;
            }
            else{
                return null;
            }
      
        }
        else{
            return null;
        }
        
    }
    
}
