package com.example.antidoping

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)                // Set the scanner view as the content view
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

    override fun handleResult(rawResult: Result) {
        // Do something with the result here
        // Log.v("tag", rawResult.getText()); // Prints scan results
        // Log.v("tag", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        Log.ERROR
        Log.v("tag", rawResult.barcodeFormat.toString())


        /*val tvresult:TextView = findViewById(R.id.tvresult);
        tvresult.setText(rawResult.text)*/

        var ziffern = rawResult.text
        val liste = ziffern.toCharArray();

        var pzn = ""

        pzn += liste[10]
        pzn += liste[11]
        pzn += liste[12]
        pzn += liste[13]
        pzn += liste[14]
        pzn += liste[15]
        // Neu ist der beschnittene Code

        val intent = Intent(this@ScanActivity, DetailseiteActivity::class.java)
        intent.putExtra("PZN",pzn)
        intent.putExtra("typ","barcode")
        startActivity(intent)
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}