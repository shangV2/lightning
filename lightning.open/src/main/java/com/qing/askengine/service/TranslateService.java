package com.qing.askengine.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qing.askengine.result.KVResult;

public class TranslateService {

	private String url = "http://202.201.255.250:9999/translate/moses";
	private String apikey = "EBNOELNIA857PSM6HUMJ94S99G";
	
	public KVResult<String> translate(String from, String to, String text) {

		KVResult<String> result = new KVResult<String>();
		result.setSuccess(false);
		result.setErrCode(1001);
		result.setErrMsg("....");
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("from", from));
		urlParameters.add(new BasicNameValuePair("to", to));
		urlParameters.add(new BasicNameValuePair("corpus", text));
		urlParameters.add(new BasicNameValuePair("apikey", apikey));

		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
			HttpResponse response = client.execute(post);
			
			if ( response.getStatusLine().getStatusCode() == 200 ) {
				HttpEntity entity = response.getEntity();
				String content = EntityUtils.toString(entity, "UTF-8");
				JSONObject jso = JSON.parseObject(content);
				content = jso.getString("translation");
				result.setValue(content);
				result.setSuccess(true);
				return result;
			}
			result.setErrMsg(response.getStatusLine().toString());
		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
			result.setErrMsg(e.getMessage());
		} catch (ParseException e) {
//			e.printStackTrace();
			result.setErrMsg(e.getMessage());
		} catch (IOException e) {
//			e.printStackTrace();
			result.setErrMsg(e.getMessage());
		}
		return result;
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
}
