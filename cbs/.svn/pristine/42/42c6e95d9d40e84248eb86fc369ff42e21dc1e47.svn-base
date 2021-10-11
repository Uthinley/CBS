package com.springapp.mvc.setup.card;

import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.DropdownDTO;
import com.springapp.mvc.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardSetupService extends BaseService {

    @Autowired
    private CardSetupDao cardSetupDao;

    @Transactional(readOnly = false)
    public ResponseMessage save(CardSetupDTO cardSetupDTO, CurrentUser currentUser, Character actionType) throws Exception {
        switch (actionType) {
            case CMD_FLAG_CREATE:
                responseMessage = save(cardSetupDTO, currentUser);
                break;
//            case CMD_FLAG_MODIFY:
//                responseMessage = update(branchSetupDTO, currentUser);
////                responseMessage = update(groupSetupDTO, currentUser);
//                break;
            default:
                responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                responseMessage.setText(MSG_DEFAULT_UNSUCCESSFUL);
        }
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage save(CardSetupDTO cardSetupDTO, CurrentUser currentUser) {
        CardSetup cardSetup = convertDTOToEntity(cardSetupDTO, currentUser);
        cardSetupDao.save(cardSetup);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Saved successfully.");
        return responseMessage;
    }

    private CardSetup convertDTOToEntity(CardSetupDTO cardSetupDTO, CurrentUser currentUser) {
        CardSetup cardSetup = new CardSetup();
        cardSetup.setCardName(cardSetupDTO.getCard_name());
        cardSetup.setCardType(cardSetupDTO.getCard_type());
        cardSetup.setStatus(cardSetupDTO.getStatus());
        cardSetup.setBank(cardSetupDTO.getBank());
        cardSetup.setCreatedBy(currentUser.getUserName());
        cardSetup.setCreatedDate(currentUser.getServerDate());
        return cardSetup;
    }

    public List<CardSetupDTO> getCardList() {
        return cardSetupDao.getCardList();
    }

    public ResponseMessage delete(CardSetupDTO cardSetupDTO, CurrentUser currentUser) {
        cardSetupDao.delete(cardSetupDTO.getId());
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Deleted successfully.");
        return responseMessage;
    }

}
