package fr.esgi.api.services;

import fr.esgi.api.DTO.GetSampleDTO;
import fr.esgi.api.Entities.Sample;
import fr.esgi.api.repositories.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SampleService {

    private final SampleRepository sampleRepository;

    @Autowired
    public SampleService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Transactional(readOnly = true)
    public List<GetSampleDTO> getSample() {
        return sampleRepository
                .findAll()
                .stream()
                .map(Sample::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public Integer createSample(@Validated Sample w) {
        Sample created = sampleRepository.save(w);
        return created.getSampleId();
    }
}
