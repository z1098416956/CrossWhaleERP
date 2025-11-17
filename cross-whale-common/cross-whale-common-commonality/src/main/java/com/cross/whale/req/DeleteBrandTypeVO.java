package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeleteBrandTypeVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Long> ids;
}
