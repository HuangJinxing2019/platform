package cn.lubang.lubangcommon.entity;

import cn.lubang.lubangcommon.utils.Tree;
import lombok.Data;

@Data
public class SysMenuEntity extends Tree {

    /**
     * 菜单ID
     */
    private Long menu_id;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parent_id;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer order_num;

    /**
     * 状态
     */
    private Integer status;
}
