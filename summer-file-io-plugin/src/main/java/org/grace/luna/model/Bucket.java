package org.grace.luna.model;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Artest
 * Created on 2022/8/31 8:13:05
 */
@Data
@Accessors(chain = true)
public class Bucket implements Serializable {

    private static final long serialVersionUID = 8865901147900919154L;

    /**
     * name
     */
    private String name = null;

    /**
     * The details on the owner of this bucket
     */
    private Owner owner = null;

    /**
     * The date this bucket was created
     */
    private Date creationDate = null;

    private String location = null;
}
