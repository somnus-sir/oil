package com.whn.hellospring.common;

/**
 * @author lfx
 * 返回客户端结果定义
 */
public enum StateMessage {

    SUCCESS(true, "000", "成功"),
    PHONE_ERROR(false, "001", "手机号不匹配"),
    LOSE_PARAM(false, "002", "缺少参数"),
    USER_NOT_EXIST(false, "003", "用户不存在"),
    CAPTCHA_OVERDUE(false, "004", "验证码过期"),
    CAPTCHA_FAILURE(false, "005", "验证码不匹配"),
    SIGN_IN_FAILED(false, "006", "签到失败"),
    LOGIN_OTHER_DEVICE(false, "007", "异常登录"),
    CREDENTIALS_ERROR(false, "008", "用户凭证错误"),
    OPERATION_FAILED(false, "009", "操作失败"),
    OUT_PERMISSION(false, "010", "权限不足"),
    PARAMETER_FORMAT_ERROR(false, "011", "参数格式不匹配"),
    BASIC_DATE_ERROR(false, "012", "基础数据获取失败"),
    COMPANY_EXIST_ERROR(false, "013", "公司已存在"),
    FACTORY_NOT_EXIST_ERROR(false, "014", "工厂不存在"),
    FACTORY_EXIST_ERROR(false, "015", "工厂已存在"),
    PHONE_EXIST(false, "016", "手机已存在"),
    LOSE_CJBZQSSL(false, "017", "成检包装缺少数量"),
    LOSE_TECHNOLOGY(false, "018", "卡里缺少工艺"),
    ORDER_EXIST_ERROR(false, "019", "订单不存在"),
    ORDER_PARAM_ERROR(false, "020", "参数类型错误"),
    PARAM_TOO_BIG(false, "021", "退仓数量不能大于坯布库存"),
    SYSTEM_LOGIN_FALSE(false, "022", "登录失败"),
    SYSTEM_SAVE_FALSE(false, "023", "保存失败"),
    SYSTEM_DELETE_FALSE(false, "024", "删除失败"),
    CAN_NOT_DELETE_BOSS(false, "025", "不能删除老板"),
    COMPANY_USER_EXIST(false, "026", "公司已存在员工"),
    UPDATE_FALSE(false, "027", "更新失败"),
    LAZY_WEIGHT(false, "028", "成检数量不能少于发货数量"),
    OPERATE_TOO_SMALL(false, "029", "匹数或者操作数量不能为0"),
    WIDTH_OR_WEIGHT_TOO_SMALL(false, "030", "门幅或者克重不能为0"),
    CAT_NOT_CANCEL_EXIT_CARD_ORDER(false, "031", "已开卡，不能取消订单，请刷新列表"),
    QUANTITY_NOT_SUFFICIENT_CAT_NOT_NULL(false, "032", "请填写数量不合格数量"),
    QUANTITY_DISQUALIFICATION_CAT_NOT_NULL(false, "033", "请填写质量不合格数量"),
    PROCESS_EXIST(false, "034", "卡已添加流程，不能删除"),
    LAZY_VOLUME(false, "035", "成检匹数不能少于发货匹数"),
    FACTORY_ABBREVIATION_EXIST_ERROR(false, "036", "请仔细检查，防止重复添加此厂"),
    USER_EXIST(false, "037", "您已通过审核，可直接使用手机号登陆"),
    ORDER_CANCEL(false, "038", "订单已取消，请勿操作"),
    NOT_EXIST_COLOR_PRINT_NO(false, "039", "该任务无此色号或花号底色，请重新填写"),
    INSUFFICIENT_TASK(false, "040", "所发任务不足"),
    CODELIST_INCOMPLETE(false, "041", "码单不全，请补码单"),
    PROCESS_REPEAT(false, "042", "自定义工序重复"),
    FACTORY_COMPANY_EXIST(false, "043", "加工厂已经存在，请编辑"),
    FINANCE_HOST_CUSTOMER_EXIST(false, "044", "客户已存在，请勿重复添加"),
    FINANCE_HOST_SUPPLIER_EXIST(false, "045", "供应商已存在，请勿重复添加"),
    FINANCE_HOST_FACTORY_EXIST(false, "046", "加工厂已存在，请勿重复添加"),
    NO_ADD_COUNT(false, "047", "未新增数量，请重新操作"),
    COMPANIES_IS_FULL(false, "048", "公司人数已满"),
    WORD_IS_FULL(false, "049", "标签数字超过12个字，请重新填写"),
    CODE_EXPIRED(false, "050", "code已过期"),
    CAT_NOT_CANCEL(false, "051", "不能取消订单，请刷新列表"),
    COMPANY_CLOSE(false, "052", "系统已关闭，不可登录"),
    COMPANY_CLOSE_TO_OPEN(false, "053", "系统已关闭，请开启"),
    APPLY_INVALID(false, "054", "该手机号审核不通过。若有疑问，请拨打18058682630"),
    APPLY_PROCESSING(false, "055", "正在审核当中，请耐心等待。若有疑问，请拨打18058682630"),
    APPLY_PASS(false, "056", "手机号已注册，请下载并登录纺织超人管理版。若有疑问，请拨打18058682630"),
    FINANCE_ACCOUNT_EXIST(false, "057", "财务账户已存在，请勿重复添加"),
    FINANCE_PROJECT_EXIST(false, "058", "财务项目已存在，请勿重复添加"),
    DRAFT_NO_EXIST(false, "059", "草稿不存在，不可编辑"),
    FACTORY_EXIST(false, "060", "已经存在该加工厂"),
    SUPPLIER_EXIST(false, "061", "已经存在该供应商"),
    INVALID_CODE(false, "062", "该码不在仓库或无效"),
    VAT_NO_LOSE(false, "063", "缺少缸号"),
    KNITTING_WOVEN_EXIST(false, "064", "针梭织都存在，操作失败"),
    COMPANY_CLOSE_NO_MONEY(false, "065", "系统已到期停用，请联系客服续费开通"),
    UN_KNOW_REASON(false, "999", "请重试"),
    SAVE_FAILED(false, "1000", "保存不成功"),
    CANCEL_SCAN_UNIQUE_NO_FAILED(false, "1001", "取消操作失败"),
    CLOTH_STATE_ERROR(false, "1002", "该布匹的状态不对为:已出仓"),
    CLOTH_IS_OCCUPIED(false, "1003", "该布匹被出货任务占用"),
    CLOTH_NOT_EXIST(false, "1004", "唯一码不存在"),
    CLOTH_REPEATED_ADDITION(false, "1005", "布匹重复添加"),
    ORDER_FINISH_OR_NOT_EXIST(false, "1006", "盘仓订单未创建或已完成"),
    NOT_IN_WAREHOUSE(false, "1007", "布匹不在该仓库"),
    NOT_IN_AREA(false, "1008", "布匹不在该分区"),
    NOT_IN_POSITION(false, "1009", "布匹不在该仓位"),
    NOT_IN_SHIPPING_OR_DELETE(false, "1010", "布匹被删除或已出货"),

    APPLY_CODE_LIST_IS_EMPTY(false, "101", "电子码单不存在"),
    APPLY_CODE_LIST_PAY_IS_SUCCESS(false, "102", "电子码单订单已经支付"),
    PAY_SERVICE_FAIL_SIGN(false, "103", "支付中心下单验签失败"),
    PAY_SERVICE_FAIL_OHTER(false, "104", "支付中心下单失败"),


    REPEAT_SUBMIT(false, "9999", "重复请求！");
    private boolean state;
    private String errorCode;
    private String message;


    StateMessage(boolean state, String errorCode, String message) {
        this.state = state;
        this.errorCode = errorCode;
        this.message = message;
    }

    public boolean getState() {
        return state;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public StateMessage appendMessage(String append) {
        this.message += append;
        return this;
    }

}
