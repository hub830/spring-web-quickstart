package sample.web.ui.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Administrator {
	private Long id;
	/** 用户名 */
	private String name;
	/** 邮箱 */
	private String email;
	/** 是否系统用户 */
	private Boolean issys;
	/** 是否锁定 */
	private Boolean block;
	/** 密码 */
	private String password;
	/** 盐 */
	private String salt;
	/** 创建时间 */
	private Date createtime=new Date();
	
	private Set<Role> roles;

	
	
	public Administrator() {
	}

	public Administrator(Long id) {
		this.id = id;
	}

	public Administrator(String name,String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.issys= false;
		this.block=true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 32, nullable = false)
	@NotEmpty(message = "error.name.empty")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 64)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public Boolean getIssys() {
		return issys;
	}

	public void setIssys(Boolean issys) {
		this.issys = issys;
	}

	@Column(nullable = false)
	@NotNull(message = "error.block.empty")
	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	@Column(length = 64, nullable = false)
	@NotEmpty(message = "error.password.empty")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 64, nullable = false)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "admin_roles", joinColumns = { @JoinColumn(name = "adminid") }, inverseJoinColumns = { @JoinColumn(name = "roleid") })
	@OrderBy("id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
