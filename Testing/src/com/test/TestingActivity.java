package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestingActivity extends Activity {
	private BluetoothAdapter bAdapter;
	private BluetoothDevice bDev;
	private UUID uid = UUID.fromString("bd302500-d594-11e0-9572-0800200c9a66");
	public final static UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button enumerate = (Button) findViewById(R.id.enumerate);
        enumerate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				TextView logarea = (TextView) findViewById(R.id.textlog);
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
					bDev = chx;
					Button enumerate = (Button) findViewById(R.id.enumerate);
					enumerate.setText("Found CHX");
					enumerate.setEnabled(false);
					
				}
				logarea.append(" " + bDev.getBondState());
				try {
					logarea.append("trying to create socket");
					BluetoothSocket socket = bDev.createRfcommSocketToServiceRecord(uuid);
					logarea.append("socket created? trying to connect");
					socket.connect();
					logarea.append("connected?, getting OStrean,IStream and trying to send");
					OutputStream os = socket.getOutputStream();
					InputStream is = socket.getInputStream();
					os.write(0);
					logarea.append("done i guess");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logarea.append("CARP");
					System.out.println("can't create RF Comm Socket");
				}	
			}
        });
        Button exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				finish();
			}
        });
    }
}