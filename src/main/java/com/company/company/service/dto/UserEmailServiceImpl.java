package com.company.company.service.dto;

import com.company.company.model.dto.Mail;
import com.company.company.repository.UserRepository;
import com.company.company.util.NotNullByDefault;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@NotNullByDefault

@Log4j
@Service
public class UserEmailServiceImpl implements EmailService {

    private JavaMailSender emailSender;
    private Configuration configuration;
    private Mail mail;

    private UserRepository repository;

    @Lazy
    @Autowired
    public UserEmailServiceImpl(
            Mail mail,
            JavaMailSender emailSender,
            UserRepository repository,
            @Qualifier("getFreeMarkerConfiguration") Configuration freemarkerConfiguration)
    {
        this.mail           = mail;
        this.emailSender    = emailSender;
        this.repository     = repository;
        this.configuration  = freemarkerConfiguration;

        configuration.setClassForTemplateLoading(this.getClass(), "/templates/");
    }


    public void sendMessageToAdmins(String username) throws Exception {
        //mail.setTo("usrmbtask@gmail.com");

        mail.setFrom("admmbtask@gmail.com");
        mail.setSubject("TestTask System");

        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("signature", "TestTask");
        mail.setModel(model);

        sendMessageToAdmins(mail);
    }


    private void sendMessageToAdmins(Mail mail) throws Exception {

        MimeMessage message  = getMessage();
        String adresses[]    = getAdresses();

        log.debug(adresses);

        MimeMessageHelper helper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

        Template t = configuration.getTemplate("addUserMessage.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

        setParams(helper, adresses, mail.getFrom(), mail.getSubject(), html, true);

        emailSender.send(message);
    }


    private String[] getAdresses() {
        List<String> adr = repository.getAdminsEmails();
        return adr.toArray(new String[adr.size()]);
    }


    private MimeMessage getMessage() {
        return emailSender.createMimeMessage();
    }


    private void setParams(
                           MimeMessageHelper helper,
                           String adresses[],
                           String from,
                           String subject,
                           String text,
                           boolean isHTML)
                                          throws MessagingException {
        helper.setTo(adresses);
        helper.setSubject(subject);
        helper.setFrom(from);
        helper.setText(text, isHTML);
    }
}
