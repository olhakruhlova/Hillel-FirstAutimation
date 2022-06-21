package testdata;

public class Email {
    private String toUser;
    private String emailSubject;
    private String emailBody;

    private String toUserWithoutEmail;

   public Email(String toUser, String emailSubject, String emailBody) {
      this.toUser = toUser;
      this.emailSubject = emailSubject;
      this.emailBody = emailBody;
   }
   public Email(String toUserWithoutEmail) {
      this.toUserWithoutEmail = toUserWithoutEmail;
   }

   public String getToUser() {
      return toUser;
   }

   public String getEmailSubject() {
      return emailSubject;
   }

   public String getEmailBody() {
      return emailBody;
   }

   public String getToUserWithoutEmail() {
      return toUserWithoutEmail;
   }
}


