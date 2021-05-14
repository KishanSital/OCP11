package chapter6_7.services;

import chapter6_7.constants.Constants;
import chapter6_7.entities.*;
import chapter6_7.interfaces.*;
import java.util.Scanner;
import java.util.function.Consumer;

public class SendMailService{

    private String loggedInUser;
    private Scanner scanner;
    private MailInterface mailInterface = () -> scanner = new Scanner(System.in);
    private Consumer <String> println = (p) -> System.out.println(p);
    private MailEntity mailEntity;

    public SendMailService(String loggedInUser,
                           MailEntity mailEntity) {
        this.loggedInUser = loggedInUser;
        this.mailEntity = mailEntity;
        mailInterface.init();
    }


    public void sendMail() {
        println.accept("Enter the recipient G-mail address");
        mailEntity.setReceiverUsername(scanner.next());
        mailEntity.setSenderUsername(loggedInUser);
        println.accept("Enter the subject");
        mailEntity.setSubject(scanner.next());
        println.accept("Enter the message");
        mailEntity.setMessage(scanner.next());
        println.accept("Mail has been sent successfully");
        Constants.mailRepository.addMailInterface.add(mailEntity);
    }
}
