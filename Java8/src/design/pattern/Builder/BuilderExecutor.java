package design.pattern.Builder;

class Email {
	String subject;
	String receiver;
	String body;
	String signature;
	
	static class   EmailBuilder {
		String subject;
		String receiver;
		String body;
		String signature;
		
		EmailBuilder(String subject, String receiver) {
			this.subject = subject;
			this.receiver = receiver;
		}
		
		EmailBuilder setBody(String body) {
			this.body = body;
			return this;
		}
		
		EmailBuilder setSignature(String sigString) {
			this.signature = sigString;
			return this;
		}
		
		Email build()  {
			Email   email = new Email();
			email.subject = this.subject;
			email.receiver = this.receiver;
			email.body = this.body;
			email.signature = this.signature;
			return email;
		}
	}

	@Override
	public String toString() {
		return "Email [subject=" + subject + ", receiver=" + receiver + ", body=" + body + ", signature=" + signature
				+ "]";
	}
	
	
}


public class BuilderExecutor {

	public static void main(String[] args) {
		Email email  =new Email.EmailBuilder("ABC", "test@builder.com")
				.setBody("Body of email").build();
		System.out.println(email);
	}

}
