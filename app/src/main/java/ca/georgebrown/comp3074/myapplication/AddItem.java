package ca.georgebrown.comp3074.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItem extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Button btn_save = findViewById(R.id.btn_save);
        final EditText item = findViewById(R.id.item);
        dbHelper = new DatabaseHelper(this);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = item.getText().toString();
                if(item.length() == 0){
                    Toast.makeText(AddItem.this, "Please enter item name! ", Toast.LENGTH_LONG).show();
                }else{
                    addItem(newItem);
                    item.setText("");
                    Intent i = new Intent(AddItem.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    public void addItem(String item) {
        boolean addItem = dbHelper.insertItem(item);

        if (addItem) {
            Toast.makeText(this,"List updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Please try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
