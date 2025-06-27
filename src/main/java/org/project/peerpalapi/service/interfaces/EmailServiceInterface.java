package org.project.peerpalapi.service.interfaces;

import org.project.peerpalapi.entity.EmailDetails;

public interface EmailServiceInterface {

    void sendMail(EmailDetails emailDetails);

}
