package ca.georgebrown.comp3074.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    DatabaseHelper dbHelper;

    ListView lv;

    ArrayList<String> arrayList;

    ArrayAdapter adapter;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        lv = view.findViewById(R.id.lv);
        dbHelper = new DatabaseHelper(getContext());
        arrayList = new ArrayList<>();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();

                Cursor data = dbHelper.getID(item);
                int dataID = -1;
                while (data.moveToNext()) {
                    dataID = data.getInt(0);
                }
                if (dataID > -1) {
                    Intent i = new Intent(getContext(), RemoveItem.class);
                    i.putExtra("id", dataID);
                    i.putExtra("name", item);
                    startActivity(i);
                }

            }
        });

        displayList();

        return view;


    }

    private void displayList(){
        Cursor cursor = dbHelper.getName();

        while (cursor.moveToNext()){
            arrayList.add(cursor.getString(1));
        }
        adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                arrayList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
