package com.musala.drones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private byte[] data;
    private String fileName;
    private String fileExtension;
}
