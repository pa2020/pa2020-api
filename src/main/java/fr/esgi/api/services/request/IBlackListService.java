package fr.esgi.api.services.request;

import fr.esgi.api.models.request.BlackList;

import java.util.List;

/**
 * Created by Zakaria FAHRAOUI.
 */

public interface IBlackListService {
    List<BlackList> AllBlackList();

    List<BlackList> SearchBySentence(String word);

    BlackList addBlackListSentence(BlackList blackList);

    void deleteBlackList(Long id);
}
