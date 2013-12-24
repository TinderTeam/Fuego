package cn.fuego.activity.domain.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: ActvtNode 
* @Description: 活动节点
* 记录一个事物的一个节点。
* @author Nan Bowen
* @date 2013-12-14 下午05:31:43 
*
 */
@Component ("ActvtNode")
public class ActvtNode {
	private String nodeName;		//节点名称，一般为"创建"、"确认"、审批等。即 Create 、 Ensure、等等、
	private String nextNodeName;	//下一个节点名称，用于寻址
	private String backNodeName;	//退回时节点名称
	private List<String> nodeHandler =new ArrayList<String>(); //操作者权限列表
	private String handlerType;	//操作者权限名称类型
	private List<String> nodeViewer = new ArrayList<String>();	//观察这权限列表
	private String viewerType;	//观察者权限类型
	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * @param nodeName the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * @return the nextNodeName
	 */
	public String getNextNodeName() {
		return nextNodeName;
	}
	/**
	 * @param nextNodeName the nextNodeName to set
	 */
	public void setNextNodeName(String nextNodeName) {
		this.nextNodeName = nextNodeName;
	}
	/**
	 * @return the backNodeName
	 */
	public String getBackNodeName() {
		return backNodeName;
	}
	/**
	 * @param backNodeName the backNodeName to set
	 */
	public void setBackNodeName(String backNodeName) {
		this.backNodeName = backNodeName;
	}
	/**
	 * @return the nodeHandler
	 */
	public List<String> getNodeHandler() {
		return nodeHandler;
	}
	/**
	 * @param nodeHandler the nodeHandler to set
	 */
	public void setNodeHandler(List<String> nodeHandler) {
		this.nodeHandler = nodeHandler;
	}
	/**
	 * @return the handlerType
	 */
	public String getHandlerType() {
		return handlerType;
	}
	/**
	 * @param handlerType the handlerType to set
	 */
	public void setHandlerType(String handlerType) {
		this.handlerType = handlerType;
	}
	/**
	 * @return the nodeViewer
	 */
	public List<String> getNodeViewer() {
		return nodeViewer;
	}
	/**
	 * @param nodeViewer the nodeViewer to set
	 */
	public void setNodeViewer(List<String> nodeViewer) {
		this.nodeViewer = nodeViewer;
	}
	/**
	 * @return the viewerType
	 */
	public String getViewerType() {
		return viewerType;
	}
	/**
	 * @param viewerType the viewerType to set
	 */
	public void setViewerType(String viewerType) {
		this.viewerType = viewerType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActvtNode [nodeName=" + nodeName + ", nextNodeName="
				+ nextNodeName + ", backNodeName=" + backNodeName
				+ ", nodeHandler=" + nodeHandler + ", handlerType="
				+ handlerType + ", nodeViewer=" + nodeViewer + ", viewerType="
				+ viewerType + "]";
	}
	
	
}
