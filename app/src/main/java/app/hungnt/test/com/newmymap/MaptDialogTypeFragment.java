package app.hungnt.test.com.newmymap;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by hungnt on 1/10/17.
 */

public class MaptDialogTypeFragment extends DialogFragment {
    ListView listViewMaptype;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_map_type_dialog, container, false);
        getDialog().setTitle("Map type");
        String questions[]={"Normal","Terrain","Satellite","Hybrid"};
        listViewMaptype=(ListView)rootView.findViewById(R.id.listMapType);
        MapTypeAdapter customAdapter = new MapTypeAdapter(getActivity().getApplicationContext(), questions);
        listViewMaptype.setAdapter(customAdapter);
        listViewMaptype.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
     //   getDialog().getWindow().requestFeature(Win)
        return rootView;
    }
}
