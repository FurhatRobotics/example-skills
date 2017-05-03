package iristk.app.quiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;

import iristk.furhat.server.FreemarkerHandler;
import iristk.furhat.server.WebServer;
import iristk.furhat.skill.FormFileResource;
import iristk.furhat.skill.Skill;
import iristk.project.Project;
import iristk.util.Pair;
import iristk.util.Record;
import iristk.util.ZipUtils;

public class QuestionResource extends FormFileResource {
	private static final MultipartConfigElement MULTI_PART_CONFIG = new MultipartConfigElement(System.getProperty("java.io.tmpdir"));

	public QuestionResource(Skill skill, String name, File file) {
		super(skill, name, file);
	}
	
	
	private class TextFileHandler extends FreemarkerHandler {

		public TextFileHandler() throws IOException {
			super(webData, Project.main.getPackage(QuestionResource.class).getPath("resources"));
		}

		@Override
		public void handle(String target, Request baseRequest,
				HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			switch(target){
				case "/" : 
					serveTemplate("QuestionResource.ftl", baseRequest, response);
					break;
				case "/save" : 
					WebServer.serveRecord(saveResource(request), baseRequest, response);
					break;
				case "/export":
					response.setStatus(HttpServletResponse.SC_OK);
					response.setContentType("text/plain");
					response.setHeader("Content-Disposition", "attachment; filename=\"questions.txt\"");
					File path = getFile();
					FileInputStream inputStream = null;
					try {
						inputStream = new FileInputStream(path);
						byte[] buffer = new byte[1024];
				        int bytesRead = 0;
				        
				        do {
				            bytesRead = inputStream.read(buffer, 0, buffer.length);
				            response.getOutputStream().write(buffer, 0, bytesRead);
				        } while (bytesRead == buffer.length);

				        response.getOutputStream().flush();
					} finally {
				        if(inputStream != null) {
				            inputStream.close();
				        }
				    }
					baseRequest.setHandled(true);
					break;
				case "/import":
					if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
						baseRequest.setAttribute(Request.__MULTIPART_CONFIG_ELEMENT, MULTI_PART_CONFIG);
						Part part = request.getPart("file");
						if (part != null) {
							File destpath = getFile().getParentFile();
							File file = new File(destpath, "questions.txt");
							file.getParentFile().mkdirs();
							FileOutputStream fos = new FileOutputStream(file);
							try {
								IOUtils.copy(part.getInputStream(), fos);
								fos.close();
								WebServer.serveRecord(new Record("status", "Uploaded"), baseRequest, response);
							} catch (Exception e) {
								//Handle 
								WebServer.serveRecord(new Record("status", "Failed"), baseRequest, response);
							}
						}
					}
				default :
					super.handle(target, baseRequest, request, response);
			}
		}
	}
	
	private static void unpack(File path, InputStream inputStream) throws IOException {
		ZipInputStream zip = new ZipInputStream(inputStream);
		ZipEntry entry = null;
		byte[] buffer = new byte[1024];
		while ((entry = zip.getNextEntry()) != null) {
			if (!entry.isDirectory()) {
				File file = new File(path, "questions.txt");
				file.getParentFile().mkdirs();
				FileOutputStream fos = new FileOutputStream(file);
				int len;
				while ((len = zip.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			}
		}
	}

	@Override
	public Handler getWebHandler() throws IOException {
		return new TextFileHandler();
	}

}
