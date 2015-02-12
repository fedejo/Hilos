package com.example.hilos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText entrada;
	private TextView salida;
	
	class MiThread extends Thread {
		private int n, res;
		
		public MiThread(int n) {
			this.n = n;
		}
		
		@Override
		public void run() {
			res = factorial(n);
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					System.out.println("2 - " + n);
					System.out.println("Res - " + res);
					salida.append(n + "! = ");
					salida.append(res + "\n");
					
				}
			});
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		entrada = (EditText) findViewById(R.id.entrada);
		salida = (TextView) findViewById(R.id.salida);
	}
	
	public void calcularOperacion(View view) {
		int n = Integer.parseInt(entrada.getText().toString());
		salida.setText("");
		for (int i=n; i>=(n-4) && i>0; i--) {
			MiThread thread = new MiThread(i);
			thread.start();
		}
	}
	
	public int factorial(int n) {
		int res = 1;
		for (int i=1; i<=n; i++) {
			res *= i;
		}
		SystemClock.sleep(500);
		return res;
	}
}
