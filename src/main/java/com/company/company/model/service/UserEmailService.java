package com.company.company.model.service;

import com.company.company.NotNullByDefault;
import com.company.company.entity.Mail;
import com.company.company.model.data.UserRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@NotNullByDefault

@Service
public class UserEmailService {

    private JavaMailSender emailSender;
    private Configuration configuration;
    private Mail mail;

    private UserRepository repository;

    @Lazy
    @Autowired
    public UserEmailService(
            Mail mail,
            JavaMailSender emailSender,
            UserRepository repository,
            @Qualifier("getFreeMarkerConfiguration") Configuration freemarkerConfiguration)
    {
        this.mail = mail;
        this.emailSender = emailSender;
        this.repository = repository;
        this.configuration = freemarkerConfiguration;

        configuration.setClassForTemplateLoading(this.getClass(), "/templates/");
    }


    public void sendMessageToAdmins(String email, String username) throws Exception {
        //mail.setTo("usrmbtask@gmail.com");

        mail.setSubject("TestTask System");

        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("signature", "TestTask");
        mail.setModel(model);

        sendSimpleMessage(mail);
    }


    private void sendSimpleMessage(Mail mail) throws Exception {

        List<String> adr = repository.getAdminsEmails();
        System.out.println(adr);
        String addresses[] = adr.toArray(new String[adr.size()]);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

        //helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

        Template t = configuration.getTemplate("addUserMessage.ftl");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

        helper.setTo(addresses);
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }
}
