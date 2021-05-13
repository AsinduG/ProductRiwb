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
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ItemsAPI")
public class ItemsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Product obj = new Product();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static Map getParasMap(HttpServletRequest request)
    {
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
     }
    return map;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 String output = obj.insertOderProduct(
			 request.getParameter("pid"),
			 request.getParameter("researcherId"),
			 request.getParameter("date"),
			 request.getParameter("time"),
			 request.getParameter("totAmount"),
			 request.getParameter("status"));
			 response.getWriter().write(output);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 
			 String output = obj.updateOrderProduct(paras.get("hidItemIDSave").toString(),
			 paras.get("pid").toString(),  
			 paras.get("researcherId").toString(),
			 paras.get("date").toString(),
			 paras.get("time").toString(),
			 paras.get("totAmount").toString(),
			 paras.get("status").toString());
			 response.getWriter().write(output);
			}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
			 Map paras = getParasMap(request);
			 
			 String output = obj.deleteOrder(paras.get("orderId").toString());
			 response.getWriter().write(output);
			}

}