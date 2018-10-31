package com.projeto.pi2018.azure;

import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.projeto.pi2018.http.ChamadaHttp;
import com.projeto.pi2018.model.DetecFace;

public class Azure {
	
	private String endPoint = "https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/persongroups/";
	private String personGroupId = "34";

	public  String  reconhecerFace(String body) throws Exception, Exception {
		String endPoint2 = "https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true";
		ChamadaHttp http = new ChamadaHttp();
			
			String faceId = EntityUtils.toString(http.chamada(endPoint2,body)).substring(12,48);
         
         
         
         return faceId;     
         
	}

	
	public  String  criarPersonGruop() throws Exception, Exception {
		String body ="{\"name\":\"TesteImagens\"}";
		
		endPoint = endPoint+personGroupId;
		
		ChamadaHttp http = new ChamadaHttp();
		
       String jsonString = EntityUtils.toString(http.chamada(endPoint,body)).trim();
       
       
       
       return jsonString;      
       
	}
	
	public String criarPerson() throws Exception, Exception, Exception{
		String body ="{\"name\":\"Rodrigo2\"}";
		endPoint = endPoint+personGroupId+"/persons";
		ChamadaHttp http = new ChamadaHttp();
		String jsonString = EntityUtils.toString(http.chamada(endPoint,body)).substring(13, 49);
		System.out.println(jsonString);
		return jsonString;   
	}
	
	public String addFace(String pernsonId)throws Exception, Exception, Exception {
		String body ="{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg\"}";
		System.out.println(pernsonId);
		endPoint = endPoint+personGroupId+"/persons/"+pernsonId+"/persistedFaces";
		ChamadaHttp http = new ChamadaHttp();
		String jsonString = EntityUtils.toString(http.chamada(endPoint,body)).trim();
		System.out.println(jsonString);
		
		return jsonString;   
		
	}
	
	public String train()throws Exception, Exception, Exception {
		endPoint = endPoint+personGroupId+"/train";
		ChamadaHttp http = new ChamadaHttp();
		
		String jsonString = EntityUtils.toString(http.chamada(endPoint,null)).trim();
		
		return jsonString;   
		
	}
	
	public String identify(String faceId) throws Exception, Exception, Exception {

		String endpoint3="https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/identify";
		String body ="{\"personGroupId\": \"34\",\"faceId\":\""+faceId+"\"}";
		ChamadaHttp http = new ChamadaHttp();
		System.out.println(faceId);
		String jsonString = EntityUtils.toString(http.chamada(endpoint3,body)).trim();
		
		System.out.println(jsonString);
		
		return jsonString;   
	}

}
