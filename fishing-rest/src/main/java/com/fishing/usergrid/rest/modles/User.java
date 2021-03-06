package com.fishing.usergrid.rest.modles;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "username can not be empty")
	private String username;
	
	@NotEmpty(message = "password can not be empty")
	private String password;
	
	@Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", 
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName="id")}, 
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName="id")})
    private List<Role> roleList;// 一个用户具有多个角色
	
	public User() {
		super();
	}
	
	public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
	
	@Transient
    public Set<String> getRolesName() {
        List<Role> roles = getRoleList();
        Set<String> set = new HashSet<String>();
        for (Role role : roles) {
            set.add(role.getRole_name());
        }
        return set;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
