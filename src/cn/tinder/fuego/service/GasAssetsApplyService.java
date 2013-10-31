package cn.tinder.fuego.service;

import cn.tinder.fuego.webservice.struts.bo.apply.GasAssetsApplyInitPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;

public interface GasAssetsApplyService {

	GasAssetsApplyInitPageBo getPageBoByUser(SystemUserBo user);

}
