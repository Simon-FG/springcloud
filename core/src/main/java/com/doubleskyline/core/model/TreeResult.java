package com.doubleskyline.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangzhengyang
 * @date 2019/05/29.
 *
 * 树形工具类
 */
@Data
public class TreeResult implements Serializable {
    /**级数*/
    private int level;
    private String value;
    private String label;
    private String parent;
    private List<TreeResult> children;

}
