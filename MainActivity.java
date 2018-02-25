package com.example.anandpatelak.anandpatel_comp304finallabtest_002;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private StockManager stockManager;
    private Button btnInsertStocks,btnDisplayStocks;
    private RadioButton radioBtnCompany1,radioBtnCompany2,radioBtnCompany3;
    private TextView textViewDisplay;
    private Stock stock,stock1,stock2;
    private final static String TABLE_NAME = "StockInfo";
    private static final String tableCreatorString =
            "CREATE TABLE "+ TABLE_NAME + " (stockSymbol TEXT PRIMARY KEY, companyName TEXT, stockQuote INTEGER);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            stockManager = new StockManager(this);
            //create the table
            stockManager.dbInitialize(TABLE_NAME, tableCreatorString);
        }catch(Exception e){
            Toast.makeText(MainActivity.this,
                    e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",e.getMessage());
        }

        radioBtnCompany1 = (RadioButton)findViewById(R.id.radioButtonCompany1);
        radioBtnCompany2 = (RadioButton)findViewById(R.id.radioButtonCompany2);
        radioBtnCompany3 = (RadioButton)findViewById(R.id.radioButtonCompany3);
        textViewDisplay = (TextView)findViewById(R.id.textViewDisplayStocks);
        btnInsertStocks = (Button)findViewById(R.id.buttonInsertStocks);
        btnDisplayStocks = (Button)findViewById(R.id.buttonDisplayStocks);
    }

    public void insertStocks(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put("stockSymbol","APLL");
        contentValues.put("companyName","Apple");
        contentValues.put("stockQuote",1000);

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("stockSymbol","AMNN");
        contentValues1.put("companyName","Amazon");
        contentValues1.put("stockQuote",1200);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("stockSymbol","GGLE");
        contentValues2.put("companyName","Google");
        contentValues2.put("stockQuote",1500);

        try{
            stockManager.addRow(contentValues);
            stockManager.addRow(contentValues1);
            stockManager.addRow(contentValues2);
        }catch (Exception e){
            Toast.makeText(MainActivity.this,
                    e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",e.getMessage());
        }

        //Display RadioButtons
        try {

            stock = stockManager.getStockById( contentValues.get("stockSymbol").toString(), "stockSymbol");
            radioBtnCompany1.setText(stock.getStockSymbol());

            stock1 = stockManager.getStockById(contentValues1.get("stockSymbol").toString(), "stockSymbol");
            radioBtnCompany2.setText(stock1.getStockSymbol());

            stock2 = stockManager.getStockById(contentValues2.get("stockSymbol").toString(), "stockSymbol");
            radioBtnCompany2.setText(stock2.getStockSymbol());


        }catch(Exception e)
        {
            Toast.makeText(MainActivity.this,
                    e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",e.getMessage());
        }
    }
    public void displayStocks(View view){
        Intent i;
        if(radioBtnCompany1.isChecked()){
            textViewDisplay.setText("Company Name: "+stock.getCompanyName()+"\nStockQuote: "+stock.getStockQuote());
            i= new Intent(getBaseContext(),StockService.class);
            i.putExtra("CompanyName",stock.getCompanyName());
            i.putExtra("StockQuote",String.valueOf(stock.getStockQuote()));
            startService(i);
        }
        else if(radioBtnCompany2.isChecked()){
            textViewDisplay.setText("Company Name: "+stock1.getCompanyName()+"\nStockQuote: "+stock1.getStockQuote());
            i= new Intent(getBaseContext(),StockService.class);
            i.putExtra("CompanyName",stock1.getCompanyName());
            i.putExtra("StockQuote",String.valueOf(stock1.getStockQuote()));
            startService(i);
        }
        else if(radioBtnCompany3.isChecked()){
            textViewDisplay.setText("Company Name: "+stock2.getCompanyName()+"\nStockQuote: "+stock2.getStockQuote());
            i= new Intent(getBaseContext(),StockService.class);
            i.putExtra("CompanyName",stock2.getCompanyName());
            i.putExtra("StockQuote",String.valueOf(stock2.getStockQuote()));
            startService(i);
        }
    }
}
