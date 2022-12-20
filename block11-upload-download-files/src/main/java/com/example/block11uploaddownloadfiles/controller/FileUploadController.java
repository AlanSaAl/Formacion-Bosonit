package com.example.block11uploaddownloadfiles.controller;

import com.example.block11uploaddownloadfiles.application.DatosFicheroService;
import com.example.block11uploaddownloadfiles.application.StorageService;
import com.example.block11uploaddownloadfiles.controller.dto.DatosFicheroInputDto;
import com.example.block11uploaddownloadfiles.controller.dto.DatosFicheroOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@RestController
public class FileUploadController  {
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    DatosFicheroService datosFicheroService;

    @PostMapping("upload")
    public ResponseEntity<DatosFicheroOutputDto> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                                  RedirectAttributes redirectAttributes) {
        storageService.store(file);

        // Guardar datos del fichero
        Date date = new Date();
        DatosFicheroInputDto datosFicheroInputDto = new DatosFicheroInputDto(file.getOriginalFilename(), date);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return ResponseEntity.ok().body(datosFicheroService.addMetadatos(datosFicheroInputDto));
    }

    @GetMapping("/files/filename/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/files/fileid/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serverFileById(@PathVariable int id) {
        Resource file = storageService.loadAsResourceById(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("setpath")
    public ResponseEntity<String> setDirectorio(@RequestParam String path) {
        return ResponseEntity.ok().body(storageService.setDirectorio(path));
    }
}
