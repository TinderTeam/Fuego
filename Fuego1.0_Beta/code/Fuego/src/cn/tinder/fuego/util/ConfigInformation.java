/**
 * TODO
 * 上午01:07:45
 */
package cn.tinder.fuego.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

}
