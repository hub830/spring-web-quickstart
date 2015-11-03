package sample.web.ui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 账户详情表
 * 
 * @author toyota
 *
 */
@Entity
public class TestB {

	private Long id;
	private Long testAId;
	private int a;
	private int b;
	private String c;

	public TestB() {
		super();
		a = 0;
		b = 0;
		c = "";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 16)
	public Long getTestAId() {
		return testAId;
	}

	public void setTestAId(Long testAId) {
		this.testAId = testAId;
	}

	@Column(length = 4)
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	@Column(length = 4)
	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@Column(length = 32)
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}