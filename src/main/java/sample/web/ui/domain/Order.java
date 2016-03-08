package sample.web.ui.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table
public class Order extends AutoIDEntity{
	// 预约日期 
	private String p_Appointment_app_date;
	//不动产产权证号
	private String p_Appointment_app_1;
	//网签合同类型
	private String slwq;
	//网签合同号
	private String wqhth;
	//网签合同号 slwq + wqhth
	private String p_Appointment_app_2;
	//完税凭证号
	private String p_Appointment_app_3;
	//卖方是否个人 是:1 否:2 
	private String sx;
	//卖方是否个人 
	private String p_Appointment_app_4;
	//卖方姓名或名称
	private String p_Appointment_app_5;
	//卖方证件类别  身份证:sfz 其他身份证明:qt
	private String p_Appointment_app_6;
	//卖方证件号码
	private String p_Appointment_app_7;
	//卖方委托代理人姓名
	private String p_Appointment_app_8;
	//卖方委托代理人证件类别 身份证:sfz 其他身份证明:qt
	private String p_Appointment_app_9;
	//卖方委托代理人证件号码
	private String p_Appointment_app_10;
	//买方是否个人  是:1 否:2 
	private String s;
	//买方是否个人 
	private String p_Appointment_app_11;
	//买方姓名或名称
	private String p_Appointment_app_12;
	//买方证件类别 身份证:sfz 其他身份证明:qt
	private String p_Appointment_app_13;
	//买方证件号码
	private String p_Appointment_app_14;
	//买方委托代理人姓名
	private String p_Appointment_app_15;
	//买方委托代理人证件类别
	private String p_Appointment_app_16;
	//买方委托代理人证件号码
	private String p_Appointment_app_17;
	//预约人手机号
	private String p_Appointment_phonenumber;
	//请设定个人密码
	private String p_Appointment_testmsg;
	//验证码
	private String j_captcha_response;


}
