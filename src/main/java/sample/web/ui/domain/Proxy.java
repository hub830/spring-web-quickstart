package sample.web.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import sample.web.ui.enums.Available;
import sample.web.ui.enums.Used;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"ip"})})
public class Proxy extends AutoIDEntity{
	private static final long serialVersionUID = -4180819418847018545L;
//	private Long id;
	/** IP地址 */
	private String ip;
	/** 端口 */
	private Integer port;
	/** 使用次数 */
	private Integer times;
	/** 是否可用 */
	private Available available;
	/** 是否已经使用 */
	private Used used;

	public Proxy() {
	}
/*
	public Proxy(Long id) {
		this.id = id;
	}*/

	public Proxy(String ip, Integer port) {
		super();
		this.ip = ip;
		this.port = port;
		this.times = 0;
		this.available = Available.YES;
		this.used = Used.NO;
	}
/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/


	@Column(nullable = false)
	@NotNull
	@NotEmpty
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Column(nullable = false)
	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Available getAvailable() {
		return available;
	}

	public void setAvailable(Available available) {
		this.available = available;
	}

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Used getUsed() {
		return used;
	}

	public void setUsed(Used used) {
		this.used = used;
	}
	
}
