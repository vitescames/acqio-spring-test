package com.acqio.teste.resource;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acqio.teste.model.TransactionDTO;
import com.acqio.teste.model.TransactionVO;
import com.acqio.teste.repository.TransactionRepository;

@RestController
@RequestMapping(value="/transaction")
public class TransactionResource extends BaseResource{
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@PostMapping("/")
	public ResponseEntity<String> saveTransaction(@RequestBody TransactionDTO transactionDTO) {
		
		TransactionVO transactionVO = convertTransactionDTOtoVO(transactionDTO);
		
		try { 
			System.out.println("Saving transaction");
			transactionRepository.save(transactionVO);	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Transaction ID created: " + transactionVO.getId(), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/all")
	public List<TransactionDTO> getAllTransactions() {
		
		List<TransactionDTO> returnList = null;
		
		try { 
			System.out.println("Getting all transactions");
			List<TransactionVO> VOList = transactionRepository.findAll();	
			returnList = convertTransactionListToDTO(VOList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnList;
		
	}
	
	@PutMapping("/")
	public ResponseEntity<String> updateTransaction(@RequestBody TransactionDTO transactionDTO) {
		
		try { 			
			TransactionVO transactionVO = transactionRepository.findById(transactionDTO.getId()).orElse(null);	
			
			if(transactionVO != null) {
				System.out.println("Updating transaction " + transactionDTO.getId());
				transactionRepository.save(convertTransactionDTOtoVO(transactionDTO));
				return new ResponseEntity<>("Transaction Updated!", HttpStatus.OK);			
			} else {
				return new ResponseEntity<>("Transaction ID not found.", HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		
	}

}
