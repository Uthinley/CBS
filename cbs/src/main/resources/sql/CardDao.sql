CardDao.getCardDetails= SELECT * from cardinfo WHERE cid=:cid
CardDao.getTotalCardsBankWise= call sp_get_total_cards_bank_wise()
CardDao.getCardList = SELECT *, CASE WHEN (C.status=1) THEN 'Active' ELSE 'Inactive' END AS statusName FROM card_type_master C

CardDao.delete = DELETE FROM card_type_master WHERE id=:id

CardDao.getCardType= SELECT A.card_name as text , A.id as value FROM card_db.card_type_master A

CardDao.getAllCardInfoList=SELECT cid, name, cardType, sanctionAmt, currency, bank from cardinfo

