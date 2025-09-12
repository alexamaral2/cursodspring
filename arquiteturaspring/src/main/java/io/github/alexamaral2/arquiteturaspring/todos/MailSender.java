package io.github.alexamaral2.arquiteturaspring.todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void enviar(String mensagem) {
        System.out.println("Enviado email: " + mensagem);
    }
}
