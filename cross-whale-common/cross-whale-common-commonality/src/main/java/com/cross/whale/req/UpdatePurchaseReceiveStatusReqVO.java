package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdatePurchaseReceiveStatusReqVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private List<Long> ids;

    /**
     * 入库状态
     * 0 草稿 刚创建，未提交审核 可编辑、删除、提交
     * 1 待收货 已审核通过，等待收货 可开始收货、取消
     * 2 收货中 正在接收实物 可录入收货数量、完成收货、暂停
     * 3 待质检 收货完成，等待质检 可开始质检、退回收货
     * 4 质检中 正在质量检验 可录入质检结果
     * 5 待上架 质检合格，等待上架 可开始上架、退回质检
     * 6 上架中 正在上架到库位 可录入上架库位、完成上架
     * 7 已完成 整个入库流程完成 只读状态，可关闭
     * 8 已取消 入库单被取消 只读状态
     * 9 已关闭 已完成并财务结算 只读归档状态
     */
    private Integer receiveStatus;
}
