package app.hungnt.test.com.newmymap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.inputmethod.InputMethodManager;

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
    public static void share(String txt,String title,Context context){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,txt);
        context.startActivity(Intent.createChooser(sharingIntent,title));
    }
    public static void hiddenKeyboard(Activity activity){
        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        if(activity.getCurrentFocus()!=null )
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
