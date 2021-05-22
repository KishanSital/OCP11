package chapter4.views;

import chapter4.constants.Constants;
import chapter4.entities.MailEntity;
import chapter4.repositories.MailRepository;
import chapter4.repositories.UserRepository;

public class MailsView {
	
    private  UserRepository userRepository;
    private  MailRepository mailRepository;
    private String loggedInUser;
	
    public MailsView(UserRepository userRepository,
					 MailRepository mailRepository,
		String loggedInUser) {
        this.userRepository = userRepository;
        this.mailRepository = mailRepository;
        this.loggedInUser = loggedInUser;
	}
	
	
    public void viewMyInbox(){
        if (loggedInUser.equalsIgnoreCase("ksital@gmail.com")){
            mailRepository.print2dMails();
			} else {
            for (MailEntity mailEntity : mailRepository.getMailsList()){
                if (loggedInUser.equalsIgnoreCase(mailEntity.getReceiverUsername())){
                    System.out.println(mailEntity.toString());
				}
			}
		}
	
    }
	
	
	}
		