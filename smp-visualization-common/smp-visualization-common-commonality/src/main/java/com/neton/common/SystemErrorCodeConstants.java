package com.neton.common;

/**
 * @author TheSunshine
 * @date 2024-10-29 11:39:20
 */
public interface SystemErrorCodeConstants {

    // ========== OAUTH2 模块 ==========
    ErrorCode OAUTH2_UNKNOWN = new ErrorCode(1001001000, "未知错误"); // 预留
    // 预留 1001001001 ~ 1001001099 错误码，方便前端
    ErrorCode OAUTH2_ACCESS_TOKEN_NOT_FOUND = new ErrorCode(1001001001, "访问令牌不存在");
    ErrorCode OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED = new ErrorCode(1001001002, "访问令牌已过期");
    ErrorCode OAUTH2_ACCESS_TOKEN_INVALID = new ErrorCode(1001001003, "访问令牌已失效");
    ErrorCode OAUTH2_REFRESH_TOKEN_NOT_FOUND = new ErrorCode(1001001005, "刷新令牌不存在");
    ErrorCode OAUTH2_REFRESH_TOKEN_EXPIRED = new ErrorCode(1001001006, "访问令牌已过期");
    ErrorCode OAUTH2_REFRESH_TOKEN_INVALID = new ErrorCode(1001001007, "刷新令牌已失效");
    ErrorCode OAUTH2_TOKEN_SMS_ERROR = new ErrorCode(1001001008,"验证码不匹配");
    ErrorCode OAUTH2_TOKEN_PWD_ERROR = new ErrorCode(1001001009,"密码不匹配");
    ErrorCode OAUTH2_TOKEN_ACCOUNT_ISNULL = new ErrorCode(1001001010,"账户或密码不能为空");
    ErrorCode OAUTH2_ACCESS_CLIENT_NOT_FOUND = new ErrorCode(1001001011,"client不存在");
    ErrorCode OAUTH2_REFRESH_TOKEN_IS_NULL = new ErrorCode(1001001012,"刷新令牌不能为空");
    ErrorCode OAUTH2_ACCOUNT_IS_ENABLE = new ErrorCode(1001001013,"账号已被禁用");
    ErrorCode OAUTH2_TOKEN_ACCOUNT_ERROR = new ErrorCode(1001001014,"账户或密码不匹配");
    //================系统模块===================1001002001
    ErrorCode SYSTEM_ACCOUNT_NO_ERR = new ErrorCode(1001002001,"账号不能为空");
    ErrorCode SYSTEM_USERNAME_ERR = new ErrorCode(1001002002,"用户名称不能为空");
    ErrorCode SYSTEM_ACCOUNT_NO_REPEAT = new ErrorCode(1001002003,"账号重复");
    ErrorCode SYSTEM_USERNAME_REPEAT = new ErrorCode(1001002004,"用户名称重复");
    ErrorCode SYSTEM_USER_ID_IS_NULL = new ErrorCode(1001002005,"ID不能为空");
    ErrorCode SYSTEM_USER_ID_IS_ERR = new ErrorCode(1001002006,"用户不存在");
    ErrorCode SYSTEM_SYSTEM_ID_IS_ERR = new ErrorCode(1001002007,"父id不能为空");
    ErrorCode SYSTEM_P_ID_IS_ERR = new ErrorCode(1001002008,"父id不存在");
    ErrorCode SYSTEM_DEPT_NAME_IS_NULL = new ErrorCode(1001002009,"部门名称不能为空");
    ErrorCode SYSTEM_DEPT_IS_NULL = new ErrorCode(1001002010,"部门不存在");
    ErrorCode SYSTEM_MENU_ID_IS_NULL = new ErrorCode(1001002011,"菜单id不能为空");
    ErrorCode SYSTEM_MENU_IS_NULL = new ErrorCode(1001002012,"菜单不存在");
    ErrorCode SYSTEM_ROLE_ID_IS_NULL = new ErrorCode(1001002013,"角色ID不能为空");
    ErrorCode SYSTEM_ID_IS_NULL = new ErrorCode(1001002014,"ID不能为空");
    ErrorCode SYSTEM_ROLE_NAME_IS_NULL = new ErrorCode(1001002015,"角色名称不能为空");
    ErrorCode SYSTEM_ROLE_IS_NULL = new ErrorCode(1001002016,"角色不存在");
    ErrorCode SYSTEM_ROLE_CODE_IS_NULL = new ErrorCode(1001002017,"角色编码不能为空");
    ErrorCode SYSTEM_ROLE_CODE_IS_EXIST = new ErrorCode(1001002018,"角色编码已存在");
    ErrorCode SYSTEM_USER_ROLE_IS_NULL = new ErrorCode(1001002019,"用户权限不能为空");
    ErrorCode SYSTEM_ROLE_IS_BEEN_USED = new ErrorCode(1001002020,"角色已被分配,不可删除");
    //================文件模块===============1003001000
    ErrorCode FILE_IS_NULL = new ErrorCode(1003001000, "文件不存在或上传的文件内容为空");
    //================仓库模块===============1003003000
    ErrorCode PROVINCES_IS_NULL = new ErrorCode(1003003000, "城市代码为空");
    ErrorCode PRODUCT_TYPE_PID_IS_NULL = new ErrorCode(1003003001, "分类父id不能为空");
    ErrorCode PRODUCT_TYPE_PID_IS_ERR = new ErrorCode(1003003002, "分类父id不存在");
    ErrorCode PRODUCT_TYPE_ID_IS_NULL = new ErrorCode(1003003003, "分类id不能为空");
    ErrorCode STORAGE_NAME_IS_NULL = new ErrorCode(1003003004, "仓库名称不能为空");
    ErrorCode STORAGE_NAME_ADMIN_IS_NULL = new ErrorCode(1003003005, "管理员id不能为空");
    ErrorCode STORAGE_CITY_ID_IS_NULL = new ErrorCode(1003003006, "城市id不能为空");
    ErrorCode STORAGE_ID_IS_NULL = new ErrorCode(1003003007, "id不能为空");
    ErrorCode STORAGE_ID_IS_ERR = new ErrorCode(1003003008, "id错误");
    ErrorCode BRAND_NAME_IS_NULL = new ErrorCode(1003003009, "品牌名称不能为空");
    ErrorCode BRAND_IPD_IS_NULL = new ErrorCode(1003003010, "分类父id不能为空");
    ErrorCode BRAND_IPD_IS_ERR = new ErrorCode(1003003011, "分类父id不存在");
    ErrorCode BRAND_ID_IS_ERR = new ErrorCode(1003003012, "id不能为空");
    //================商品模块===============1003004000
    ErrorCode GOOD_UNIT_NAME_IS_NULL = new ErrorCode(1003004000, "单位名称不能为空");
    ErrorCode GOOD_UNIT_ID_IS_NULL = new ErrorCode(1003004001, "单位ID不能为空");
    ErrorCode GOOD_UNIT_ID_IS_ERR = new ErrorCode(1003004002, "单位不存在");
    ErrorCode GOOD_UNIT_TYPE_IS_ERR = new ErrorCode(1003004003, "操作类型错误");
    ErrorCode GOOD_ATT_VALUE_IS_NULL = new ErrorCode(1003004004, "属性名或值不能为空");
    ErrorCode GOOD_ATT_ID_NULL = new ErrorCode(1003004004, "属性ID不能为空");
    ErrorCode GOOD_ATT_ID_ERR = new ErrorCode(1003004005, "属性不存在");
    ErrorCode GOOD_INFO_ATT_ERR = new ErrorCode(1003004006, "多单位和多属性不能同时勾选");
    ErrorCode GOOD_INFO_INVENTORY_ERR = new ErrorCode(1003004007, "商品仓库信息不能为空");
    ErrorCode GOOD_INFO_MORE_ATT_ERR = new ErrorCode(1003004008, "商品多属性不能为空");
}
