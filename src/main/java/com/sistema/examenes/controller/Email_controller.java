package com.sistema.examenes.controller;

import com.sistema.examenes.entity.EmailDTO;
import com.sistema.examenes.entity.EmailFileDTO;
import com.sistema.examenes.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = { "https://apps.tecazuay.edu.ec" })
@RestController
@RequestMapping("/aseguramiento")
public class Email_controller {
@Autowired
private JavaMailSender mail;
    @Autowired
    private IEmailService emailService;

    /*@PostMapping("/send-email")
    public ResponseEntity<?>enviarcorreo(){
        SimpleMailMessage email= new SimpleMailMessage();
        email.setTo("claudio.velecela.est@tecazuay.edu.ec");
        email.setFrom("jhonloja771@gmail.com");
        email.setSubject("HABLANDO SERIO");
        email.setText("SACA DEL GRUOO A LOS DOS ZHUNIO Y GUITAMA ");
        mail.send(email);
        return  new ResponseEntity<>(true, HttpStatus.OK);


    }*/

    @PostMapping("/send-email")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDTO emailDTO){
        System.out.println("Mensaje Recibido " + emailDTO);
        emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("estado", "Enviado");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/sendMessageFile")
    public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute EmailFileDTO emailFileDTO){

        try {
            String fileName = emailFileDTO.getFile().getOriginalFilename();
            Path path = Paths.get("src/mail/resources/files/" + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(emailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File file = path.toFile();
            emailService.sendEmailWithFile(emailFileDTO.getToUser(), emailFileDTO.getSubject(), emailFileDTO.getMessage(), file);
            Map<String, String> response = new HashMap<>();
            response.put("estado", "Enviado");
            response.put("archivo", fileName);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
        }
    }

}
