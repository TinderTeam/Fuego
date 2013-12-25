/**
 * TODO
 * 上午01:07:45
 */
package cn.tinder.fuego.util;

import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

/**
 * @author Administrator
 * 
 */
public class ConfigInformation
{
    // private static ConfigInformation instance = new ConfigInformation();
    private static final Log log = LogFactory.getLog(ConfigInformation.class);

    private static Properties prop;

    private ConfigInformation()
    {

        try
        {
            /* 而采用类加载器的话，能够更具有通用性 */
            /* 使用文件的读写的方式，文件的路径的相对路径确定了，不能修改 */
            prop = new Properties();
            InputStream inStream = ConfigInformation.class.getClassLoader()
                    .getResourceAsStream("Config.properties");
            prop.load(inStream);
            
            log.info(prop.getProperty("VERSION_NUM"));

        } catch (Exception e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static String getPropertyByName(String name)
    {
        if (prop == null)
        {

            try
            {
                /* 而采用类加载器的话，能够更具有通用性 */
                /* 使用文件的读写的方式，文件的路径的相对路径确定了，不能修改 */
                prop = new Properties();
                InputStream inStream = ConfigInformation.class.getClassLoader()
                        .getResourceAsStream("Config.properties");
                prop.load(inStream);

            } catch (Exception e)
            {
                throw new ExceptionInInitializerError(e);
            }
        }

        return prop.getProperty(name);
    }

    
    /**
	 * 类路径获取
	 * @author Winter Lau
	 * @date 2009-12-4 下午03:29:43
	 */
	
	public static String getResourcePath() 
	{
			String path = OutputFileConst.class.getClassLoader().getResource(File.separator).getPath();
			path=path.substring(1);
			path=URLDecoder.decode(path);
			path=path.replace("/WEB-INF/classes/", "/files/");
			return path;
	}
	
	public static String getToolPath()
	{
		return getWebAppPath() + File.separator + "tools";
	}
	
	private static String getWebAppPath()
	{
		String path = OutputFileConst.class.getClassLoader().getResource(File.separator).getPath();
		path=URLDecoder.decode(path);
		path += ".."+File.separator + ".."; 
		
		
		return path;
 	}

}
