package bt.cbs.zrr.card;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.DropdownDTO;
import bt.cbs.zrr.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CardService extends BaseService {

    @Autowired
    private CardDAO cardDAO;

    @Transactional
    public List<CardDTO> search(String cid){
        return cardDAO.search(cid);
    }

    /**
     * Get bank-wise total cards information
     * @return CardDTO list
     */
    @Transactional
    public  List<CardDTO> totalCardsBankWise(){
        return cardDAO.totalCardsBankWise();
    }

    /**
     * Get all the cards information
     * @return CardDTO list
     */
    @Transactional
    public  List<CardDTO> getAllCardInfoList(){
        return cardDAO.getAllCardInfoList();
    }

    @Transactional(readOnly = true)
    public List<DropdownDTO> getCardType() {
        return cardDAO.getCardType();
    }

    @Transactional(readOnly = false)
    public ResponseMessage save(CurrentUser currentUser, CardDTO cardDTO) throws Exception {
        if (Objects.isNull(cardDTO)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Enter all the fields.");
            return responseMessage;
        }
        responseMessage = save(cardDTO, currentUser);
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage save(CardDTO cardDTO, CurrentUser currentUser) {
        CardEntity cardEntity = convertDTOToEntity(cardDTO, currentUser);
        cardDAO.save(cardEntity);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Saved successfully.");
        return responseMessage;
    }

    private CardEntity convertDTOToEntity(CardDTO cardDTO, CurrentUser currentUser) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCid(cardDTO.getCid());
        cardEntity.setName(cardDTO.getName());
        cardEntity.setCardType(cardDTO.getCardType());
        cardEntity.setCurrency(cardDTO.getCurrency());
        cardEntity.setSanctionAmt(cardDTO.getSanctionAmt());
        cardEntity.setCreatedBy(currentUser.getUserName());
        cardEntity.setCreatedDate(new Date());
//        cardEntity.setBank(currentUser.g);
        return cardEntity;
    }

}
