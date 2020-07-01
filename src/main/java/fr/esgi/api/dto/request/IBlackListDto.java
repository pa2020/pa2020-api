package fr.esgi.api.dto.request;

import fr.esgi.api.models.request.BlackList;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */
public interface IBlackListDto {
    List<BlackList> findAll();

    List<BlackList> SearchBySentence(String word);

    BlackList create(BlackList blackList);

    void delete(Long id);
}
