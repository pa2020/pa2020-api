package fr.esgi.api.dto.request;

import fr.esgi.api.exception.ResourceNotFoundException;
import fr.esgi.api.models.request.BlackList;
import fr.esgi.api.repositories.request.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by Zakaria FAHRAOUI.
 */

@Component
@RequiredArgsConstructor
public class BlackListDto implements IBlackListDto {
    private final BlackListRepository blackListRepository;

    /**
     * The Logger for this class.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BlackList> findAll() {
        List<BlackList> blackLists = blackListRepository.findAll();
        return blackLists;
    }

    @Override
    public List<BlackList> SearchBySentence(String word) {
        return blackListRepository.listOfSentence(word);
    }

    @Override
    public BlackList create(BlackList blackList) {
        return blackListRepository.save(blackList);
    }

    @Override
    public void delete(Long id) {
        if (id <= 0)
            throw new ResourceNotFoundException("The given id must not be null!");
        blackListRepository.deleteById(id);
    }

}
