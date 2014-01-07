/**   
* @Title: AllocationInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.allocation 
* @Description: TODO
* @author Tang Jun   
* @date 2013-9-24 下午11:55:04 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.upLoad;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;
import cn.tinder.fuego.webservice.struts.form.UpLoadForm;

/**
 * 
* @ClassName: UpLoadAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-6 上午10:58:58 
*
 */





public class UpLoadAction extends Action
{
    private static final Log log = LogFactory.getLog(UpLoadAction.class);
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"UpLoadAction");
    	
        UpLoadForm upLoadForm = (UpLoadForm) form;
        FormFile file = upLoadForm.getMyFile();
        FileOutputStream fileOutput;
        log.info("[Info]loginForm:" + upLoadForm);
        try {
            fileOutput = new FileOutputStream(OutputFileConst.UPLOAD_FILE_PATH + file.getFileName());
            fileOutput.write(file.getFileData());
            fileOutput.flush();
            fileOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return null; 
    	/**
    	if(null == user)
    	{
    		log.error("the user is null");
    		pageName = PageNameConst.LOGIN_PAGE;
    		 return mapping.findForward(pageName);			
    	}
    	
    	//Form
    	GasAssetsApplyForm gasAssetsApplyForm = (GasAssetsApplyForm)form;
    	//Form Empty test
    	if(gasAssetsApplyForm==null){
    		log.error("cant find form!!");   
    		pageName = PageNameConst.SYSTEM_ERROR_PAGE;
    	}else{
    		log.info(LogKeyConst.PAGE_FORM+gasAssetsApplyForm.toString());    		
    	}
    	/**
    	//Para
    	String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);    	
    	if(null==submitPara){
    		log.error("submit is miss!");
    		pageName = PageNameConst.SYSTEM_ERROR_PAGE;
    	}else{
    		log.info(LogKeyConst.SUBMIT_VALUE+submitPara); 
    	}

    	// Banch select

    	
        log.info(LogKeyConst.NEXT_PAGE+pageName);
        return mapping.findForward(pageName);	
*/
    }

}
