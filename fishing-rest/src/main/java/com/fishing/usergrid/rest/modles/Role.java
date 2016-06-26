package com.fishing.usergrid.rest.modles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    private String role_name;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;
    
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Permission> permissionList;// 一个角色对应多个权限
    
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName="id")}, 
    inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName="id")})
    private List<User> userList;// 一个角色对应多个用户
    
    public Role() {
    	super();
    }
    
    public Role(String role_name) {
    	this.role_name = role_name;
    }

    @Transient
    public List<String> getPermissionsName() {
        List<String> list = new ArrayList<String>();
        List<Permission> perlist = getPermissionList();
        for (Permission per : perlist) {
            list.add(per.getPermission_name());
        }
        return list;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
    
}
