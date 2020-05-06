package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class paymentAPI
 */
@WebServlet("/paymentAPI")
public class paymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// object
	payment paymentObj = new payment();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paymentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	            //not use
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = paymentObj.insertPayment(request.getParameter("pname"),   
				request.getParameter("ano"),  
				request.getParameter("adate"),
				request.getParameter("amount"),  
				request.getParameter("cname"),
				request.getParameter("cardno"),
				request.getParameter("expmonth"),
				request.getParameter("expyear"),  
				request.getParameter("cvv")
			
				
				);
		
		 response.getWriter().write(output); 
	
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  Map paras = getParasMap(request); 
		  
			 String output = paymentObj.updatePayment(paras.get("hidpidSave").toString(),  
					 paras.get("pname").toString(), 
					 paras.get("ano").toString(),    
					 paras.get("adate").toString(),
					 paras.get("amount").toString(),    
					 paras.get("cname").toString(), 
					 paras.get("cardno").toString(),
					 paras.get("expmonth").toString(),    
					 paras.get("expyear").toString(), 
					 paras.get("cvv").toString()
					 
					 );  
			 
			 response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        Map paras = getParasMap(request);  
		 
		 String output = paymentObj.deletePayment(paras.get("pid").toString());  
		 
		 response.getWriter().write(output); 
		
	}
	
	
	private static Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Map<String, String> map = new HashMap<String, String>();  
		try  
		{   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?          
					scanner.useDelimiter("\\A").next() : "";  
					scanner.close(); 
	 
	  String[] params = queryString.split("&");   
	  for (String param : params)   
	  { 

 
	   String[] p = param.split("=");    
	   map.put(p[0], p[1]);   
	   }  
	  }  
		catch (Exception e)  
		{  
			
		}  return map; }
		

}



