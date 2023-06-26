/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.KeyRecources;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IKeyRecourcesService {
    public KeyRecources save(KeyRecources keyRecources);
    public KeyRecources update(KeyRecources keyRecources, Long id);
    public Optional<KeyRecources> findById(Long id);
}
