package cn.fuego.activity.domain.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fuego.activity.domain.po.BasicActivityRecord;
/**
 * 
* @ClassName: BusinessAcitivity 
* @Description: 基本事务活动依赖基本活动记录，是业务流程的基本单元。
* @author Nan Bowen
* @date 2013-12-14 下午05:07:21 
*
 */
@Component ("BusinessAcitivity")
public class BusinessAcitivity{
	@Autowired
	BasicActivityRecord basicActivity;
	List<ActvtNode> actvtNodeList = new ArrayList<ActvtNode>();
}
