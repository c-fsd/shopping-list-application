package ca.georgebrown.comp3074.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RemoveItem extends AppCompatActivity {

    DatabaseHelper dbHelper;

    ListView lv;

    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);
        lv = findViewById(R.id.lv);
        arrayList = new ArrayList<>();
        Button btn_remove = findViewById(R.id.btn_remove);
        final TextView txt = findViewById(R.id.txt);
        dbHelper = new DatabaseHelper(this);
        Intent getIntent = getIntent();
        final int itemID = getIntent.getIntExtra("id",-1);
        final String item = getIntent.getStringExtra("name");
        txt.setText(item);

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.removeItem(itemID,item);
                txt.setText("");
                Intent i = new Intent(RemoveItem.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
