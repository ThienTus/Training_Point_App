/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.services;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 *
 * @author ThienTu
 */

public interface PdfService {
    ByteArrayInputStream createPdf(Map<String, String> params);
}
