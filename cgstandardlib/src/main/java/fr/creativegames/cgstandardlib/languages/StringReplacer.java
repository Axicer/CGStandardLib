package fr.creativegames.cgstandardlib.languages;

import fr.creativegames.cgstandardlib.exceptions.ErrorHandler;
import fr.creativegames.cgstandardlib.languages.error.MalformedJSONError;
import fr.creativegames.cgstandardlib.utils.Validate;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StringReplacer {

    public static Map<String, String> getGlobalVars(AbstractLanguage lang){
        Map<String, String> vars = new HashMap<>();
        try {
            if(!Validate.notNull(lang.getLangJSON().getJSONObject("lang"))){
                ErrorHandler.throwError(new MalformedJSONError());
                return null;
            }
            JSONObject root = lang.getLangJSON().getJSONObject("lang");
            return child(lang, root, "lang");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String replaceGlobal(String text, AbstractLanguage lang){
        String[] elements = text.split("@");
        Map<String, String> map = getGlobalVars(lang);
        for(String key : getGlobalVars(lang).keySet()){
            for(int i = 0 ; i < elements.length ; i++){
                String str = elements[i].replaceFirst("@","");
                if(str.startsWith(key)){
                    str.replace(key,map.get(key));
                }
                elements[i] = str;
            }
        }
        StringBuilder build = new StringBuilder();
        build.append(elements);
        return build.toString();
    }

    private static Map<String, String> child(AbstractLanguage lang, JSONObject obj, String text){
        Map<String, String> val = new HashMap<>();
        try{
            Iterator<String> it = obj.keys();
            StringBuilder path = new StringBuilder();
            path.append(text);
            while(it.hasNext()){
                String key = it.next();
                path.append("."+key);
                Object o = lang.get(path.toString(), new Object());
                if(o instanceof JSONObject){
                    val.putAll(child(lang, obj, path.toString()));
                }else{
                    if(o instanceof String && key.startsWith("@")){
                        val.put(key, (String)o);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
