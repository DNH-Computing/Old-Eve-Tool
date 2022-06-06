package nz.net.dnh.evetool.hibernate.evetool;

// Generated 10/08/2011 3:49:46 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Permission generated by hbm2java
 */
@Entity
@Table(name = "Permission", catalog = "EVETool")
public class Permission implements java.io.Serializable {

	private int permissionId;
	private String permission;
	private Set<Rule> rules = new HashSet<Rule>(0);

	public Permission() {
	}

	public Permission(int permissionId, String permission) {
		this.permissionId = permissionId;
		this.permission = permission;
	}

	public Permission(int permissionId, String permission, Set<Rule> rules) {
		this.permissionId = permissionId;
		this.permission = permission;
		this.rules = rules;
	}

	@Id
	@Column(name = "PermissionID", unique = true, nullable = false)
	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name = "Permission", nullable = false, length = 64)
	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permission")
	public Set<Rule> getRules() {
		return this.rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

}
