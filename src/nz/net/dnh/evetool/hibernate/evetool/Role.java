package nz.net.dnh.evetool.hibernate.evetool;

// Generated 10/08/2011 3:49:46 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name = "Role", catalog = "EVETool", uniqueConstraints = @UniqueConstraint(columnNames = "RoleName"))
public class Role implements java.io.Serializable {

	private int roleId;
	private String roleName;
	private Set<Rule> rules = new HashSet<Rule>(0);
	private Set<User> users = new HashSet<User>(0);
	private Role parent;

	public Role() {
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role(String roleName, Set<Rule> rules,
			Set<User> users, Role parent) {
		this.roleName = roleName;
		this.rules = rules;
		this.users = users;
		this.parent = parent;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RoleID", unique = true, nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name = "RoleName", unique = true, nullable = false, length = 64)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<Rule> getRules() {
		return this.rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="RoleParent", joinColumns={@JoinColumn(name="RoleID")}, inverseJoinColumns={@JoinColumn(name="ParentID")})
	public Role getParent() {
		return this.parent;
	}
	
	public void setParent(Role role) {
		this.parent = role;
	}
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="UserRole",
			joinColumns={@JoinColumn(name="RoleID")},
			inverseJoinColumns={@JoinColumn(name = "CorpID"), @JoinColumn(name = "UserName")})
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}