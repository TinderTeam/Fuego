package cn.tinder.fuego.service.exception.msg;

public class ExceptionMsg
{

	public static final String LOGIN_NO_USER = "不存在该用户，请重新输入!";
	public static final String LOGIN_PASSWORD_WRONG = "密码错误，请重新登录!";

	public static final String TRANS_ID_NOEXIST = "该事务不存在!";
	public static final String TRANS_HAS_OVER = "该事务已经完成!";
	public static final String DATE_FOMATE = "时间格式错误!";
	public static final String MODFILE_NOT_EXIST = "无法导入Excel模版!";
	public static final String USER_ALREADY_EXISTED = "该用户已存在!";
	public static final String INPUT_EMPUTY = "输入不能为空!";
	public static final String NEW_PASSWORD_DIFFER = "新密码输入不一致!";
	public static final String OLD_PASSWORD_ERROR = "旧密码输入有误!";
    public static final String FILE_ERR = "输出文件错误!";
    public static final String RE_LOGIN="请重新登录!";
    
    public static final String EXCEL_FORMART_WRONG="导入文件格式错误，请使用2003Excel格式!您上传的文件名为：";
    public static final String FILEPATH_NOT_EXIST = "很抱歉，未能在该路径下找到目标文件：";
	public static final String EXCEL_READ_ERR =  "很抱歉,Excel文件损坏无法导入：";
	
	public static final String ASSETS_LIST_EMPTY = "选择资产的资产为空，请重新选择";
	public static final String NO_DATA = "没有相应数据，请重新查询或选择。";
	
	public static final String ASSETS_DATA_FORMART_WRONG ="资产数据格式错误，请检查修改后重新导入";
	public static final String TYPE_NULL = "导入数据中资产类型不匹配或为空，错误行号：";

	public static final String ASSETS_NAME_EMPTY = "导入数据中资产名称不能为空，错误行号：";
	public static final String UNIT_EMPTY = "导入数据单位不能为空，错误行号：";
	public static final String PURCHASEDATE_ERR = "导入数据购置日期不能为空，错误行号：";
	public static final String TRANSACTION_NOT_FINISHED = "该事务未完成，不能被删除";
	public static final String TRANSACTION_NOT_CREATE_BY_USER = "该事务不是您创建的，不能被删除";
	public static final String GDZC_QUANTITY_ERR = "导入数据中的实物资产数量必须为1!";
	public static final String CELL_WRONG = "导入数据中单元格读取错误";
	public static final String ASSETS_NAME_ISEXIST ="导入数据中资产编号已经存在:错误的资产编号为";
}
