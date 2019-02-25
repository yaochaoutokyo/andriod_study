package com.example.androidlearning16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

	private Button button2, button3;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2_layout);
		bindView();
	}

	private void bindView() {
		textView = findViewById(R.id.textview_xml);
		button2 = findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Main2Activity.this, Main3Activity.class));
				finish();
			}
		});
		button3 = findViewById(R.id.button3);
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//sendHttpRequest();
				sendHttpWithOkHttp();
			}
		});
	}

	private void sendHttpRequest() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection conn = null;
				BufferedReader reader = null;
				try {
					URL url = new URL("http://192.168.56.1/get_data.xml");
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setReadTimeout(8000);
					conn.setConnectTimeout(8000);
					InputStream in = conn.getInputStream();
					reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder builder = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
					showXML(builder.toString());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (conn != null) {
						conn.disconnect();
					}
				}
			}
		}).start();
	}

	private void sendHttpWithOkHttp() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder().url("http://192.168.56.1/get_data.xml").build();
				try {
					Response response = client.newCall(request).execute();
					String xmlString = response.body().string();
					showXML(xmlString);
					//parseXmlWithPull(xmlString);
					parseXmlWithSax(xmlString);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void showXML(final String string) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				textView.setText(string);
			}
		});
	}

	private void parseXmlWithPull(String xmlString) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xmlString));
			int eventType = parser.getEventType();
			String id = "";
			String name = "";
			String version = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String nodeName = parser.getName();
				switch (eventType) {
					case XmlPullParser.START_TAG: {
						if ("id".equals(nodeName)) {
							id = parser.nextText();
						} else if ("name".equals(nodeName)) {
							name = parser.nextText();
						} else if ("version".equals(nodeName)) {
							version = parser.nextText();
						}
						break;
					}
					case XmlPullParser.END_TAG: {
						if ("app".equals(nodeName)) {
							Log.d("xml", "id = " + id);
							Log.d("xml", "name = " + name);
							Log.d("xml", "version = " + version);
						}
						break;
					}
				}
				parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseXmlWithSax(String xmlString) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			XMLReader reader = factory.newSAXParser().getXMLReader();
			SAXHandler handler = new SAXHandler();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(new StringReader(xmlString)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
