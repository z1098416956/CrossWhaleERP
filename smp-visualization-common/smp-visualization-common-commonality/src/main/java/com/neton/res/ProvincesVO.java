package com.neton.res;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProvincesVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String pId;

    private List<ProvincesVO> children;
}
