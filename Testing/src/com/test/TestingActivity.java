package com.test;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestingActivity extends Activity {
	private BluetoothAdapter bAdapter;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button enumerate = (Button) findViewById(R.id.enumerate);
        enumerate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				bAdapter = BluetoothAdapter.getDefaultAdapter();
				Set<BluetoothDevice> bDevices = bAdapter.getBondedDevices();
				Iterator<BluetoothDevice> i = bDevices.iterator();
				BluetoothDevice chx;
				System.out.println("enumerating bt devices");
				while( i.hasNext()){
					chx = i.next();
					System.out.println(chx.getName());
					if (chx.getName().equals( "CHX")){
						System.out.println("Found CHX");
					}
					

				}
			}
        });
    }
}