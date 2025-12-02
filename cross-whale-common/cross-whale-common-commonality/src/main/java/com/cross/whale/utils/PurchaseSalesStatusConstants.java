package com.cross.whale.utils;

/**
 * 请购单与采购单的状态
 */
public class PurchaseSalesStatusConstants {
    
    /**
     * 请购单状态枚举
     */
    public enum RequisitionStatus {
        /** 未审核 */
        NOT_REVIEWED(0, "未审核"),
        /** 已审核 */
        REVIEWED(1, "已审核"),
        /** 采购强制结单 */
        PURCHASE_FORCE_CLOSED(2, "采购强制结单"),
        /** 采购完成 */
        PURCHASE_COMPLETED(3, "采购完成"),
        /** 部分采购完成 */
        PARTIALLY_PURCHASE_COMPLETED(4, "部分采购完成");
        
        private final int code;
        private final String description;
        
        RequisitionStatus(int code, String description) {
            this.code = code;
            this.description = description;
        }
        
        public int getCode() {
            return code;
        }
        
        public String getDescription() {
            return description;
        }
        
        /**
         * 根据状态码获取枚举
         */
        public static RequisitionStatus getByCode(int code) {
            for (RequisitionStatus status : values()) {
                if (status.getCode() == code) {
                    return status;
                }
            }
            throw new IllegalArgumentException("无效的请购单状态码: " + code);
        }
    }
    
    /**
     * 采购单状态枚举
     */
    public enum PurchaseOrderStatus {
        /** 未审核 */
        NOT_REVIEWED(0, "未审核"),
        /** 已审核 */
        REVIEWED(1, "已审核"),
        /** 强制结单 */
        FORCE_CLOSED(2, "强制结单"),
        /** 采购完成 */
        PURCHASE_COMPLETED(3, "采购完成"),
        /** 部分采购完成 */
        PARTIALLY_PURCHASE_COMPLETED(4, "部分采购完成");
        
        private final int code;
        private final String description;
        
        PurchaseOrderStatus(int code, String description) {
            this.code = code;
            this.description = description;
        }
        
        public int getCode() {
            return code;
        }
        
        public String getDescription() {
            return description;
        }
        
        /**
         * 根据状态码获取枚举
         */
        public static PurchaseOrderStatus getByCode(int code) {
            for (PurchaseOrderStatus status : values()) {
                if (status.getCode() == code) {
                    return status;
                }
            }
            throw new IllegalArgumentException("无效的采购单状态码: " + code);
        }
    }
}
