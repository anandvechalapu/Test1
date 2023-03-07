
Controller Class: 

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordResetController {

	private final PasswordResetService passwordResetService;

	public PasswordResetController(PasswordResetService passwordResetService) {
		this.passwordResetService = passwordResetService;
	}

	@PostMapping("/resetPassword")
	public void resetPassword(@RequestBody Account account) {
		passwordResetService.sendResetPasswordMail(account);
	}

}

Service Class:

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

	public void sendResetPasswordMail(Account account) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(account.getEmail());
		message.setSubject("Password reset request");
		message.setText("Please click on the link below to reset your password \n http://example.com/resetPassword?token="+account.getToken());
		//send email
	}

}

Repository Class: 

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByToken(String token);
}