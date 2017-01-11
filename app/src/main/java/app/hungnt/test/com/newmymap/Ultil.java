package app.hungnt.test.com.newmymap;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hungnt on 1/10/17.
 */

public class Ultil {
    Context context;
    SharedPreferences sharedpreferences;
    public Ultil(Context context){
        this.context=context;
        sharedpreferences = context.getSharedPreferences(Constant.FILE_CONFIGURATION, Context.MODE_PRIVATE);
    }
public  void save(final String key, final String value){
    SharedPreferences.Editor editor = sharedpreferences.edit();
    editor.putString(key, value);
    editor.commit();
}
    public  String getValueByKey(final String key){
        return sharedpreferences.getString(key,"");

    }

}
