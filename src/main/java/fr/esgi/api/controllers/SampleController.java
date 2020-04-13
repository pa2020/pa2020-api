package fr.esgi.api.controllers;

import fr.esgi.api.DTO.CreateSampleDTO;
import fr.esgi.api.DTO.GetSampleDTO;
import fr.esgi.api.Entities.Sample;
import fr.esgi.api.Entities.Tag;
import fr.esgi.api.exceptions.BadRequestException;
import fr.esgi.api.services.SampleService;
import fr.esgi.api.services.URIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/samples")
public class SampleController {

    private final SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping
    public List<GetSampleDTO> getSample() {
        return sampleService.getSample();
    }

    @PostMapping
    public ResponseEntity<Void> CreateWorkspace(@Validated @RequestBody CreateSampleDTO sample) {

        if (!sample.isValid()) {
            throw new BadRequestException("limitation point are invalid");
        }

        Sample s = Sample.builder()
                .value(sample.getValue())
                .build();

        Tag t = Tag.builder()
                .name("New Tag0")
                .build();

        s.getTags().add(t);

        sample.getTags().forEach(item -> {
            // code
        });

        Integer workspaceId = this.sampleService.createSample(s);
        URI location = URIService.fromParent(workspaceId);
        return ResponseEntity.created(location).build();
    }
}
