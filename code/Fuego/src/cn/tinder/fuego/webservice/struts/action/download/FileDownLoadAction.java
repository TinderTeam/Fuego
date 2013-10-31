package cn.tinder.fuego.webservice.struts.action.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

public class FileDownLoadAction  extends Action{
	
	private static final Log log = LogFactory.getLog(FileDownLoadAction.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION+"FileDownLoadAction");

	
		
		// Page
		
		String pageName = downLoad(response,request);
		
		log.info(LogKeyConst.NEXT_PAGE + pageName);
		return mapping.findForward(pageName);

	}

	private String downLoad(HttpServletResponse response, HttpServletRequest request) {
		// TODO Auto-generated method stub

		//Session
		File file = new File((String) request.getAttribute(RspBoNameConst.DOWN_LOAD_FILE));
		log.info("File Path is "+ file.getAbsolutePath());
		
		String lastPage = (String)request.getSession().getAttribute(RspBoNameConst.NEXTPAGE);
		
	        try{

	            File downloadFile = file.getAbsoluteFile();
	            
	            InputStream  inputStream = new FileInputStream(downloadFile);	     
	            
	            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
	            
	            OutputStream outputStream = response.getOutputStream();
	            
	            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
	            
	            response.reset();
	            
	            response.setHeader("Content-disposition", "attachment;filename =" + URLEncoder.encode(
	            		downloadFile.getName(), "utf-8")
	            		);
	            
	            int bytesRead = 0;
	            byte[] buffer = new byte[4096];
	            while((bytesRead = bufferedInputStream.read(buffer,0,4096)) != -1){
	                bufferedOutputStream.write(buffer, 0, bytesRead);
	            }
	            bufferedOutputStream.flush();
	            inputStream.close();
	            bufferedInputStream.close();
	            outputStream.close();
	            bufferedOutputStream.close();

	            return lastPage;
	        }catch(FileNotFoundException ex){
	        	
	            log.error(ex.getMessage(),ex);	             
	            return PageNameConst.SYSTEM_ERROR_PAGE;
	            
	        }catch(Exception ex){
	            log.error(ex.getMessage(),ex);	           
	            return PageNameConst.SYSTEM_ERROR_PAGE;
	        }
	}
}
