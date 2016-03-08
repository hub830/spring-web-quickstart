package sample.web.ui.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


@MappedSuperclass
public class AutoIDEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private Integer optimistic;
	/** 创建时间  */
	private Date createtime = new Date();
	/** 更新时间 */
	private Date updatetime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
      return this.id;
    }

    @Version
    public Integer getOptimistic() {
      return this.optimistic;
    }

	protected void setId(Long id) {
		this.id = id;
	}

	protected void setOptimistic(Integer optimistic) {
		this.optimistic = optimistic;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AutoIDEntity))
			return false;
		AutoIDEntity castOther = (AutoIDEntity) other;
		return new EqualsBuilder().append(id, castOther.getId()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}