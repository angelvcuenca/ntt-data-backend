package com.api.client.person.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class MsgResponse  implements Serializable {
    private String msg;
    private Object object;
}
