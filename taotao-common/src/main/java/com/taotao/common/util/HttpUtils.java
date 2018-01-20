package com.taotao.common.util;

import java.util.Map;
import java.util.Set;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.taotao.common.pojo.HttpResult;

public class HttpUtils {
	
	
	
	/**
	 * @param url 地址
	 * @param params 参数
	 * @throws Exception
	 */
	public static HttpResult doGet(String url,Map<String, Object> params) throws Exception{
		//打开浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//设置请求URL
		URIBuilder uriBuilder = new URIBuilder(url);
		
		if(params != null){
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				uriBuilder.setParameter(key, params.get(key).toString());
			}
		}
		
		//默认请求失败
		int statusCode = 500;
		String body = "";
		
		//封装请求参数
		//创建get请求
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		//响应对象
		//发送请求 
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			statusCode = response.getStatusLine().getStatusCode();
			
			//如果响应码为200
			if( statusCode == 200){
				//响应内容编码为utf-8
				body = EntityUtils.toString((response.getEntity()), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(response != null){
				response.close();
			}
			httpClient.close();
		}
		
		return new HttpResult(statusCode, body);
	}
	
	/**
	 * 无请求参数
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public HttpResult doGet(String url) throws Exception{
		return doGet(url,null);
	}
	
	/**
	 * post请求
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static HttpResult doPost(String url,Map<String, Object> params) throws Exception{
		//获取httpclient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//创建uribuilder
		URIBuilder uriBuilder = new URIBuilder(url);
		
		Set<String> keySet = params.keySet();
		
		for (String key : keySet) {
			uriBuilder.addParameter(key, params.get(key).toString());
		}
		
		//封装请求
		HttpPost httpPost = new HttpPost(uriBuilder.build());
		
		
		//设置默认返回值
		int code = 500;
		String body = "";
		
		//执行请求
		CloseableHttpResponse httpResponse = null;
		
		try {
			httpResponse = httpClient.execute(httpPost);
			
			if(httpResponse != null){
				//响应内容
				body = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
				//获取请求响应码
				code = httpResponse.getStatusLine().getStatusCode();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(httpResponse!= null){
				httpResponse.close();
			}
			httpClient.close();
		}
		
		return  new HttpResult(code, body);
	}
	
	/**
	 * 无参post
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static HttpResult doPost(String url) throws Exception{
		return doPost(url,null);
	}
	
}
