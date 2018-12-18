package com.renbaojia.blog.model;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer permissionId;

    private String url;

    private String authc;

    private String desc;

    private static final long serialVersionUID = 1L;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAuthc() {
        return authc;
    }

    public void setAuthc(String authc) {
        this.authc = authc == null ? null : authc.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}