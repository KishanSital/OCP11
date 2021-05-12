package chapter6_7.constants;

import chapter6_7.entities.*;
import chapter6_7.repositories.*;

import java.util.*;

public class Constants {
	
	private Constants(){
	super();
	}
	
    public static final void displayWelcomeMessage(){
        System.out.println(WELCOME_MESSAGE);
	}
    public static final int RETRY_LIMIT = 5;
    public static final int NO_TRIES_LEFT = 0;
    public static final int QUESTION_MESSAGE_INDEX = 0;
    public static final String GMAIL_SUFFIX = "@gmail.com";
    public static UserRepository userRepository;
	public static MailRepository mailRepository;
	public static final String COMING_FROM_INBOX = "inbox";
	public static final String COMING_FROM_SENT = "sent";

	static {
		UserEntity userEntity = new UserEntity();
		userRepository = new UserRepository(userEntity);
		userRepository.insertStandardUser();
		mailRepository = new MailRepository();
	}

    public static final String WELCOME_MESSAGE = "                                                                                                                                                                                                                      \n" +
	"                                                                                                                                                                                                                      \n" +
	"                                                                                                                                                                                                                      \n" +
	"  ome t  Gmai  elcom  to GmailWe   me to            e to        lcome    Gmail        to G ailWelcome          elcome to Gm    elcome              me to      Welco       mailW       to        lcome    Gmail        \n" +
	"   Gmai  elco   to G ailWelcome t  GmailW          GmailWelc    to Gmai  elcom       ailWe come to Gmai         to GmailWel   e to Gma             GmailWelc  e to        lcome      ailWe      to Gma  Welcom        \n" +
	"   elco   to   ailW  come    Gmai    com         lWe   me to  mail  lcom   o Gm      com   o Gm    elco        ail  lco   t  Gmai  elco          lWel  me t     ilWe      to         come        lWel     to          \n" +
	"    to    ilW  com     Gm            o          ome     mail  lco    o G   lWelc    to G    Wel                     to       elc    to          ome     mai     ome t    ailW       to Gma        e t     il          \n" +
	"    ilW  come  o      Welcom         lW          Gm           to     lWel  me to    i We    e to G                  ilW     e to    ilWe         Gm              Gmai    c me       ilW lco      Gma      om          \n" +
	"     me  o GmailW     e to Gm        me         We           ailW    me t  GmailW  co e     mailWel                 om      mail    ome         Wel             Welcom  to Gm      come to G      lco      G          \n" +
	"     GmailWelcome     mailWel        Gma        e             ome    Gma   el ome  o  ma    lcome t                  Gm      com     Gm         e t   mailWe    e  o Gm il el      o GmailWe      to      Wel         \n" +
	"      lcome to Gm     lco           Welc    to  ma       me    Gm    elc    t  Gmail  lco   to                      Wel      o G    Wel          ai    come     ma lWelco   to    ilWe   me t     il     me t    ai   \n" +
	"       o G  ilWe      to G    Wel     to    il  lco      Gm   Welc  e to   ail elcom  to    ilWe    e t             e t      lWel  me t          com   o Gm     lco e to  mail    ome     mai    come     mai    co   \n" +
	"       lW   ome      ailWelc me t  GmailWelcom   o GmailWel     to Gmai  elcome to  mailWe come to Gmai            GmailW     e to Gma             GmailWe    e to G ail elcome to Gma   elcome to Gma  Welcome to    \n" +
	"       me    Gm      come to Gmai  elcome to G    Welcome       ilWelc    to Gm il  lcome  o GmailWelco           Welcome      ailWel              elcome t    ailWe co   to Gm ilWelc    to Gm ilWelco e to GmailW   \n" +
	"                                                                                                                                                                                                                      \n" +
	"                                                                                                                                                                                                                      ";
	
	
	
}
