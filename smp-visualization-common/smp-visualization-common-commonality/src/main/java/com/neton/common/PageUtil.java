package com.neton.common;

import lombok.Data;

import java.util.List;

@Data
public class PageUtil <T>{

    public Long total;

    private List<T> pateList;
}
