package com.example.antidoping

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        var id = ""
        id += liste[9]
        id += liste[10]
        id += liste[11]
        id += liste[12]
        id += liste[13]
        // Neu ist der beschnittene Code

        val intent = Intent(this@ScanActivity, DetailseiteActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}