package com.example.adi.callapp;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    private RecyclerView mRV;
    private ContactCursorAdapter adapter;





    private FloatingActionButton fab;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRV = (RecyclerView)findViewById(R.id.rv_ma);
        mRV.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        mRV.setLayoutManager(layoutManager);


        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewContact();
            }
        });

        DBopenHelper helper = new DBopenHelper(this);

        db = helper.getReadableDatabase();


        //adapter = new ContactAdapter(this,contactArr);

        adapter = new ContactCursorAdapter(this,getData());
        mRV.setAdapter(adapter);


    }

    private Cursor getData(){

        return db.query(DBopenHelper.TABLE_CONTACTS, null, null, null, null, null, null);
    }

    public void addNewContact() {

        Contact c = new Contact("adi","yaakov","0524552181","aas","adiyaakov0105@gmail.com");
        //----------------------------------------------------
        // save the contact data to database.
        DBopenHelper helper = new DBopenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBopenHelper.COLUMN_NAME, c.getFirstName());
        values.put(DBopenHelper.COLUMN_PHONE, c.getPhoneNumber());
        values.put(DBopenHelper.COLUMN_LASTNAME,c.getLastName());
        values.put(DBopenHelper.COLUMN_EMAIL,c.getEmail());
        values.put(DBopenHelper.COLUMN_IMAGE_URI,c.getImageUri());
        db.insert(DBopenHelper.TABLE_CONTACTS, null, values);
        Toast.makeText(MainActivity.this, "contact is in db", Toast.LENGTH_SHORT).show();
        
        db.close();



    }
}
