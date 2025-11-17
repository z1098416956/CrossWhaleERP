package com.cross.whale.res;

import com.cross.whale.utils.tree.TreeNode;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 11:23
 **/
@Data
public class SystemDeptTree implements Serializable, TreeNode<Long> {
    /**
     * 部门id
     */
    private Long id;
    /**
     * 父id
     */
    private Long pId;
    /**
     * 部门名称
     */
    private String deptName;

    private List<SystemDeptTree> children;


    /**
     * 设置节点的子节点列表
     *
     * @param children 子节点
     */
    @Override
    public void setChildren(List<? extends TreeNode<Long>> children) {
        this.children = (List<SystemDeptTree>) children;
    }


}
