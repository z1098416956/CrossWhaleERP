package com.cross.whale.common;

import lombok.Data;

import java.util.List;

@Data
public class PageUtil <T>{

    public Long total;

    private List<T> pageList;
}
