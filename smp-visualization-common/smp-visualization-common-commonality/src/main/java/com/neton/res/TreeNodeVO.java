package com.neton.res;

import com.neton.utils.tree.TreeNode;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TreeNodeVO implements Serializable, TreeNode<Long>{

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long pId;

    private String name;

    private List<TreeNodeVO> children;

    /**
     * 设置节点的子节点列表
     *
     * @param children 子节点
     */
    @Override
    public void setChildren(List<? extends TreeNode<Long>> children) {
        this.children = (List<TreeNodeVO>) children;
    }
}
