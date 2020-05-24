package com.acqio.teste.resource;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.acqio.teste.model.TransactionDTO;
import com.acqio.teste.model.TransactionVO;

public class BaseResource {
	
	public TransactionVO convertTransactionDTOtoVO(TransactionDTO transactionDTO) {
		
		try {
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			TransactionVO transactionVO = new TransactionVO();
			
			transactionVO.setId(transactionDTO.getId());
			transactionVO.setStatus(transactionDTO.getStatus());
			transactionVO.setCardApplication(transactionDTO.getCardApplication());
			
			Date data = sdf.parse(transactionDTO.getDate() + " " + transactionDTO.getTime());
			
			transactionVO.setTime(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			transactionVO.setValue(transactionDTO.getValue());
			
			return transactionVO;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public List<TransactionDTO> convertTransactionListToDTO(List<TransactionVO> VOList){
		
		List<TransactionDTO> returnList = new ArrayList<TransactionDTO>();
		
		for (TransactionVO transactionVO : VOList) {
			TransactionDTO transactionDTO = new TransactionDTO();
			
			transactionDTO = convertTransactionVOToDTO(transactionVO);
			returnList.add(transactionDTO);
		}
		
		return returnList;
		
	}
	
	public TransactionDTO convertTransactionVOToDTO(TransactionVO transactionVO) {
		
		TransactionDTO transactionDTO = new TransactionDTO();
		
		transactionDTO.setCardApplication(transactionVO.getCardApplication());
		transactionDTO.setId(transactionVO.getId());
		transactionDTO.setStatus(transactionVO.getStatus());
		transactionDTO.setValue(transactionVO.getValue());
		transactionDTO.setDate(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(transactionVO.getTime()));
		transactionDTO.setTime(DateTimeFormatter.ofPattern("HH:mm:ss").format(transactionVO.getTime()));
		
		return transactionDTO;
		
	}

}
