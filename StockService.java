package com.example.anandpatelak.anandpatel_comp304finallabtest_002;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class StockService extends Service {

    public StockService(){

    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Here you do the job
    //Create other Service methods
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.

        String companyName = intent.getStringExtra("CompanyName");
        String stockQuote = intent.getStringExtra("StockQuote");

        Toast.makeText(this, "Stock Info Received:"+"\nCompany Name: "+companyName+"\nStock Quote: "+stockQuote, Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    //Called when the service is stopped
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
