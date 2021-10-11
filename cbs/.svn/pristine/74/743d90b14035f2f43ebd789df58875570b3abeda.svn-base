package com.springapp.mvc.data.db.bankDeposit;

import com.springapp.mvc.data.FileUploadDTO;
import com.springapp.mvc.data.db.dto.BankDepositDTO;
import com.springapp.mvc.data.db.model.CardTransaction;
import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.global.common.CommonService;
import com.springapp.mvc.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Dechen Wangdi on 3/25/2020.
 */

@Service
public class BankDepositService extends BaseService {

    //region private variables.
    @Autowired
    private CommonService commonService;

    @Autowired
    private BankDepositDao bankDepositDao;
    //endregion

    //region public methods.

    /**
     * To save bank deposit information.
     *
     * @param fileUploadDTO FileUploadDTO
     * @return ResponseMessage
     * @throws Exception
     */
    @Transactional
    public ResponseMessage save(FileUploadDTO fileUploadDTO) throws Exception {

        if (Objects.isNull(fileUploadDTO.getUploadFile())) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose at least one file.");
            return responseMessage;
        }

        List<BankDepositDTO> bankDepositDTOList = commonService.readExcel(fileUploadDTO.getUploadFile(),
                BankDepositDTO.class, 0, new Date(), fileUploadDTO.getUsername());

        if (CollectionUtils.isEmpty(bankDepositDTOList)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please choose at least one file.");
            return responseMessage;
        }

//        BigInteger nextId = bankDepositDao.getNextId().toBigInteger();
        for (BankDepositDTO dto : bankDepositDTOList) {
            CardTransaction cardTransaction = new CardTransaction();
            cardTransaction.setName(dto.getName());
            cardTransaction.setCid(dto.getCid());
            cardTransaction.setCardType(dto.getCardType());
            cardTransaction.setTxnDate(dto.getTxnDate());
            cardTransaction.setMerchantName(dto.getMerchantName());
            cardTransaction.setCountry(dto.getCountry());
            cardTransaction.setMcc(dto.getMcc());
            cardTransaction.setTxnAmount(dto.getTxnAmount());
            cardTransaction.setBank(dto.getBank());
            cardTransaction.setCurrency(dto.getCurrency());
            cardTransaction.setCreatedBy(fileUploadDTO.getUsername());
            cardTransaction.setCreatedDate(new Date());
            bankDepositDao.save(cardTransaction);
        }

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Transaction information uploaded successfully.");
        return responseMessage;
    }
}
