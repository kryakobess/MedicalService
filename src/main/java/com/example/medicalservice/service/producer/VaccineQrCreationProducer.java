package com.example.medicalservice.service.producer;

import com.example.medicalservice.model.dto.VaccineQrMessageDto;

public interface VaccineQrCreationProducer {
    void sendCreateQr(VaccineQrMessageDto message);
}
