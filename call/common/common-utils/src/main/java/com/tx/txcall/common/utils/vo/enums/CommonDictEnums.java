package com.tx.txcall.common.utils.vo.enums;/**
 * Created by wyh in 2019/4/10 19:49
 **/

/**
 * @program:
 * @description: 字典枚举类
 * @author: forever-wang
 * @create: 2019-04-10 19:49
 **/
public enum CommonDictEnums {
    //delete_status 是否删除
    DELETE_STATUS_YES("1"),   DELETE_STATUS_NOT("-1"),
    // order_status  工单状态
    ORDER_STATUS_NEW("order_status_new"), ORDER_STATUS_WORK_ALLOCATE(
        "order_status_work_allocate"), ORDER_STATUS_WORK_CONTINUE(
        "order_status_work_continue"), ORDER_STATUS_WORK_FAIL(
        "order_status_work_fail"), ORDER_STATUS_WORK_SUCCESS(
        "order_status_work_success"), ORDER_STATUS_QUALITY_NEW(
        "order_status_quality_new"), ORDER_STATUS_QUALITY_ALLOCATE(
        "order_status_quality_allocate"), ORDER_STATUS_QUALITY_SUCCESS(
        "order_status_quality_success"), ORDER_STATUS_QUALITY_FAIL(
        "order_status_quality_fail"), ORDER_STATUS_ADMIN_NEW(
        "order_status_admin_new"), ORDER_STATUS_ADMIN_SUCCESS(
        "order_status_admin_success"), ORDER_STATUS_ADMIN_FAIL(
        "order_status_admin_fail"), ORDER_STATUS_BUSINESS_NEW(
        "order_status_business_new"), ORDER_STATUS_BUSINESS_SUCCESS(
        "order_status_business_success"), ORDER_STATUS_BUSINESS_FAIL("order_status_business_fail"),
    //rule_code
    RULE_CODE_REPEAT_CUSTOMER("REPEAT_CUSTOMER_RULE"),
    //IsEditable是否可以修改（1：是,-1否）
    EDITABLE_STATUS_YES("1"),EDITABLE_STATUS_NOT("-1"),
    //IsRequired是否必填（1：是,-1否）
    REQUIRED_STATUS_YES("1"),REQUIRED_STATUS_NOT("-1"),
    //batch_progress批次进度
    PROGRESS_DOING ("1"),PROGRESS_WAIT_TO_DO("2"),PROGRESS_FINISH("3"),
    //customerStatus    客户状态 1已拨打，2未拨打
    CUSTOMER_STATUS_CALLED("1"),CUSTOMER_STATUS_NO_CALL("2"),
    //addition_type自定义字段-类型
    ADDITION_TYPE_STRING("1"),ADDITION_TYPE_INT("2"),ADDITION_TYPE_DATE("3"),
    ADDITION_TYPE_SINGLESELECTION("4"),ADDITION_TYPE_MULTIPLESELECTION("5"),ADDITION_TYPE_DROPDOWN_BOX("6"),
    //userStatus
    USER_STATUS_SIGN_IN("user_status_sign_in"), USER_STATUS_SIGN_OUT("user_status_sign_out"),
    //atention_type客户意向
    ATENTION_TYPE_SUCCESS("1"),ATENTION_TYPE_FALL("2"),ATENTION_TYPE_RECONSIDER("3"),ATENTION_TYPE_OTHER("4"),
    //task_status 任务状态
    TASK_STATUS_SUCCESS("1"), TASK_STATUS_NOTSUCCESS("2"), TASK_STATUS_NOBELONG("3"),
    //is_Reminder是否提醒
    IS_REMINDER_YES("1"), IS_REMINDER_NO("-1"),
    //是否可以筛选（1：是,0否）
    IS_SCREEN_YES("1"),IS_SCREEN_NO("-1"),
    //处理状态
    HANDLE_STATUS_WAIT("1"),HANDLE_STATUS_CONTINUE("2"),HANDLE_STATUS_FAIL("3"),HANDLE_STATUS_SUCCESS("4")
    ;


    private final String code;

    private CommonDictEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
