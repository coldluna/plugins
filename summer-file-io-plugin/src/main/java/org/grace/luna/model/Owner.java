package org.grace.luna.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Artest
 * Created on 2022/8/31 8:14:30
 */
@Data
@Accessors(chain = true)
public class Owner implements Serializable {
    private static final long serialVersionUID = -4156388137324902707L;
    private String displayName;
    private String id;
}
