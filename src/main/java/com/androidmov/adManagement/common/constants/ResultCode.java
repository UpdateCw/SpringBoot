package com.androidmov.adManagement.common.constants;


/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class ResultCode {
    public static final Integer NORMAL = 200;
    public static final Integer FAIL = 100;
    public static final Integer EMPTY_PARAM = 300;
    public static final Integer EMPTY_RESULT = 400;
    /**
     * 插入了重复的数据
     */
    public static final Integer DUPLICATE_DATE = 401;
    /**
     * 文件已被使用不能删除
     */
    public static final Integer FILE_USED = 408;
    public static final Integer UNCONNECT_SERVER = 404;
    public static final Integer SERVER_ERR = 500;
    public static final Integer WRONG_SN = 1001;
    public static final Integer DUPLICATE_ENTRY = 204;
    public static final Integer JSON_CONVERT_ERR = 310;
    public static final Integer TEXT_TO_LONG = 1002;
    public static final Integer USER_NOT_EXISTS = 1003;
    public static final Integer NOT_FOUND_FILE = 490;
    public static final Integer FILE_UPLOAD_FAIL = 110;
    public static final Integer WRONG_FILE_SYNIX = 1004;
    /**
     * 文件解压失败
     */
    public static final Integer COMPRESS_FILE_FAIL=111;
    /**
     * 压缩包文件类型
     */
    public static final Integer COMPRESS_FILE_TYPE_FAIL=112;
    /**
     * 文件下载失败
     */
    public static final Integer DOWNLOAD_FILE_FAIL=113;

    // 发布策略相关返回Code
    public static final Integer ADD_AUDIT_FAIL = 2100;
    public static final Integer ADD_TAG_FAIL = 2101;
    public static final Integer ADD_RS_FAIL = 2102;
    public static final Integer UPDATE_RS_FAIL = 2103;
    public static final Integer ADD_RS_DS_REL_FAIL = 2104;
    public static final Integer UPDATE_WL_FAIL = 2105;
    public static final Integer DELETE_WL_FAIL = 2106;
    public static final Integer PUSH_FAIL = 2107;
    public static final Integer REMOVE_FAIL = 2108;
    public static final Integer UPDATE_AUDIT_FAIL = 2109;
    public static final Integer DUPLICATE_DS_PUSH = 2110;
}
