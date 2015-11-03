
package sample.web.ui.mvc.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class SignupCommand {

	@NotEmpty
	@NotNull
	@Size(max = 16, min = 1, message = "姓名错误！")
	private String name;

	@NotEmpty
	@NotNull
	@Email(message = "邮箱填写错误！")
	@Size(max = 16, min = 1, message = "{test.REFUSE}")
	private String email;

	@Pattern(regexp = "[a-zA-z0-9]{6}", message = "密码为6为字母或数字！")
	@NotNull
	@NotEmpty(message = "密码不能为空！")
	private String password;

	private Boolean block = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}
	
}
