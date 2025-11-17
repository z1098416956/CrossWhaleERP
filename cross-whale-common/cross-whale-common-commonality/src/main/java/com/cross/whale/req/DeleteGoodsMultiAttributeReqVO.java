package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeleteGoodsMultiAttributeReqVO implements Serializable {

    private static final long serialVersionUID = -6155520593458223103L;

    private List<Long> ids;
}
