package app.hungnt.test.com.newmymap;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by hungnt on 1/10/17.
 */

public class MapTypeDialogFragment extends DialogFragment {
    ListView listViewMaptype;

    OnListener listener;
    //-------------
    public interface OnListener {
        public void onUpdateMapTypeValue(int textId, String value);
        public void hidden();
    }
    //-------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_map_type_dialog, container, false);
        getDialog().setTitle("Map type");

        listViewMaptype=(ListView)rootView.findViewById(R.id.listMapType);
        MapTypeAdapter customAdapter = new MapTypeAdapter(getActivity(), Constant.LIST_MAP_TYPE);
        listViewMaptype.setAdapter(customAdapter);
        TextView cancel=(TextView)rootView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closeDialog();
            }
        });
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }
void closeDialog()
{
    dismiss();
}
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    //////////////////////////////
    private class MapTypeAdapter extends BaseAdapter {
        Context applicationContext;
        String[] maptypes;
        LayoutInflater inflter;

        public MapTypeAdapter(Context applicationContext, String[] maptypes) {
            this.applicationContext = applicationContext;
            this.maptypes = maptypes;
            inflter = (LayoutInflater.from(applicationContext));
        }
        @Override
        public int getCount()
        {
            return maptypes.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.map_type_item, null);
            String maptye=maptypes[i];
            RadioButton radioMapType = (RadioButton) view.findViewById(R.id.radioMapType);
            radioMapType.setText(maptye);
            String currentMaptype=new Ultil(applicationContext).getValueByKey(Constant.MAP_TYPE_KEY);
            if(currentMaptype.equals(maptye))
            {
                radioMapType.setChecked(true);
            }
            radioMapType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    new Ultil(applicationContext).save(Constant.MAP_TYPE_KEY,buttonView.getText().toString());
                    listener.onUpdateMapTypeValue(1,buttonView.getText().toString());
                    closeDialog();
                }
            });
            return view;
        }
    }

}
