package sample.web.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sample.web.ui.enums.TransferStatus;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Table
public class Transfer extends AutoIDEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2044802096304368680L;
	// 预约日期 
	private String p_Appointment_app_date;
	// 预约时间 上午:1 下午:2 
	private String amorpm;
	//不动产产权证号
	@Column(length=64)
	private String p_Appointment_app_1;
	//网签合同类型
	@Column(length=23)
	private String slwq;
	//网签合同号
	@Column(length=23)
	private String wqhth;
	//网签合同号 slwq + wqhth
	@Column(length=24)
	private String p_Appointment_app_2;
	//完税凭证号
	@Column(length=24)
	private String p_Appointment_app_3;
	//卖方是否个人 是:1 否:2 
	@Column(length=12)
	private String sx;
	//卖方是否个人 
	@Column(length=12)
	private String p_Appointment_app_4;
	//卖方姓名或名称
	@Column(length=64)
	private String p_Appointment_app_5;
	//卖方证件类别  身份证:sfz 其他身份证明:qt
	@Column(length=12)
	private String p_Appointment_app_6;
	//卖方证件号码
	@Column(length=24)
	private String p_Appointment_app_7;
	//卖方委托代理人姓名
	@Column(length=64)
	private String p_Appointment_app_8;
	//卖方委托代理人证件类别 身份证:sfz 其他身份证明:qt
	@Column(length=12)
	private String p_Appointment_app_9;
	//卖方委托代理人证件号码
	@Column(length=24)
	private String p_Appointment_app_10;
	//买方是否个人  是:1 否:2 
	@Column(length=12)
	private String s;
	//买方是否个人 
	@Column(length=32)
	private String p_Appointment_app_11;
	//买方姓名或名称
	@Column(length=64)
	private String p_Appointment_app_12;
	//买方证件类别 身份证:sfz 其他身份证明:qt
	@Column(length=12)
	private String p_Appointment_app_13;
	//买方证件号码
	@Column(length=24)
	private String p_Appointment_app_14;
	//买方委托代理人姓名
	@Column(length=64)
	private String p_Appointment_app_15;
	//买方委托代理人证件类别
	@Column(length=12)
	private String p_Appointment_app_16;
	//买方委托代理人证件号码
	@Column(length=24)
	private String p_Appointment_app_17;
	//预约人手机号
	@Column(length=12)
	private String p_Appointment_phonenumber;
	//请设定个人密码
	@Column(length=16)
	private String p_Appointment_testmsg;
	//验证码
	@Lob
	@Column(columnDefinition="CLOB")
	private String j_captcha_response;
	//servierId
	@Column(length=32)
	private String servierId;
	//proxy
	@Column(length=32)
	private String proxy;
	//cookie
	@Column(length=64)
	private String cookie;
	//状态
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private TransferStatus status;
	@Lob
	@Column(columnDefinition="CLOB")
	private String result;
	/**
	 * 
	 */
	public Transfer(String p_Appointment_app_1,String slwq,String wqhth,String p_Appointment_app_3, String p_Appointment_app_5, String p_Appointment_app_7,String p_Appointment_app_12,String p_Appointment_app_14,String p_Appointment_phonenumber) {
		super();
		this.p_Appointment_app_1 = p_Appointment_app_1;
		this.slwq = slwq;
		this.wqhth = wqhth;
		this.p_Appointment_app_2 = slwq+wqhth;
		this.p_Appointment_app_3 = p_Appointment_app_3;
		this.p_Appointment_app_4 = "1";
		this.p_Appointment_app_5 = p_Appointment_app_5;
		this.p_Appointment_app_6 = "sfz";
		this.p_Appointment_app_7 = p_Appointment_app_7;
		this.p_Appointment_app_11 = "1";
		this.p_Appointment_app_12 = p_Appointment_app_12;
		this.p_Appointment_app_13 = "sfz";
		this.p_Appointment_app_14 = p_Appointment_app_14;
		this.p_Appointment_phonenumber = p_Appointment_phonenumber;
		this.status=TransferStatus.INIT;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
