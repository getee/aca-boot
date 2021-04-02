package com.aca.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 6293264541285354649L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "role_name")
    private String name;

    @Column(name = "role_desc")
    private String desc;

    /*
     * @Column(name = "perms")
     *
     * @Type(type = "org.hibernate.type.ArrayType", parameters =
     * { @Parameter(name = "type", value = "java.lang.String") }) private
     * String[] perms;
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
