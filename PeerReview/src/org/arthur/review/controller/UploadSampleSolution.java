package org.arthur.review.controller;

import java.io.IOException;
//import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.arthur.review.service.ProblemService;

/**
 * Servlet implementation class UploadSampleSolution
 */
@WebServlet("/UploadSampleSolution")
@MultipartConfig
public class UploadSampleSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadSampleSolution() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		StringBuffer fileLocation = new StringBuffer();
		String problemNumber = null;
		String solutionNumber = null;
		String problemSolutionPair = null;

		/*
		 //boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		 if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				// Parse the request
				List  items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();

					if (item.isFormField()){

						if (item.getFieldName().equals("problemNumber")){
							problemNumber = item.getString();
							System.out.println("Problem number is: " + problemNumber);
						}

						if (item.getFieldName().equals("solutionNumber")){
							solutionNumber = item.getString();
						}
					}
					
					if (!item.isFormField()) {
						String fileName = item.getName();
						int index = fileName.lastIndexOf('\\');

						fileName = fileName.substring(index + 1);
						//String root = getServletContext().getRealPath("/");
						File path = new File("C://Users/Joel/workspace/PeerReview/WebContent/Code Samples");
						if (!path.exists()) {
							path.mkdirs();
						}

						File uploadedFile = new File(path + "/" + fileName);
						System.out.println(uploadedFile.getAbsolutePath());
						item.write(uploadedFile);
						fileLocation.append(uploadedFile.getAbsolutePath());
					}


				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		
		try {
			
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	            	if (item.getFieldName().equals("problemNumber")){
						problemNumber = item.getString();
						System.out.println("Problem number is: " + problemNumber);
					}

					if (item.getFieldName().equals("solutionNumber")){
						solutionNumber = item.getString();
					}
	            	
	            } 
	            
	            else {
	                // Process form file field (input type="file").
	                //String fieldName = item.getFieldName();
	                String fileName = FilenameUtils.getName(item.getName());
	                //InputStream fileContent = item.getInputStream();
	                
	                
	                int index = fileName.lastIndexOf('\\');

					fileName = fileName.substring(index + 1);
					//String root = getServletContext().getRealPath("/");
					File path = new File("C://Users/Joel/workspace/PeerReview/WebContent/Code Samples");
					if (!path.exists()) {
						path.mkdirs();
					}

					File uploadedFile = new File(path + "/" + fileName);
					System.out.println(uploadedFile.getAbsolutePath());
					item.write(uploadedFile);
					fileLocation.append(uploadedFile.getAbsolutePath());
	            }
	        }
	    } catch (FileUploadException e) {
	    	e.printStackTrace();
	    } catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Write code here to get value of solution number 

		String uploadLocation = fileLocation.toString();
		//then concatenate it with problem number
		//send sol_number, p_number, prob_sol_pair, uploadLocation as a record to be added
		problemSolutionPair = problemNumber + solutionNumber;
		ProblemService psr = new ProblemService();

		//db code to insert new sample solution record into database
		psr.addNewSampleSolution(solutionNumber, problemNumber, problemSolutionPair, uploadLocation);

		//psr.addNewSampleSolution(solutionNumber, problemNumber, uploadLocation);

		response.sendRedirect("viewProblemsList.jsp");
	}

}
