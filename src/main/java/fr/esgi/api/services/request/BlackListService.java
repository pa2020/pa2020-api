package fr.esgi.api.services.request;

import fr.esgi.api.dto.request.BlackListDto;
import fr.esgi.api.dto.request.IBlackListDto;
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
public class BlackListService implements IBlackListService {
    private final BlackListDto blackListDto;

    /**
     * The Logger for this class.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BlackList> AllBlackList() {
        return blackListDto.findAll();
    }

    @Override
    public BlackList addBlackListSentence(BlackList blackList) {
        return blackListDto.create(blackList);
    }

    @Override
    public void deleteBlackList(Long id) {
        blackListDto.delete(id);
    }

}
