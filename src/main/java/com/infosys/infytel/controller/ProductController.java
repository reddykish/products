package com.infosys.infytel.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.infosys.infytel.dto.productDTO;
import com.infosys.infytel.dto.subscribedproductDTO;
import com.infosys.infytel.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;
	@Autowired
	private Environment environment;
	
	@PostMapping(value = "/add",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProduct(@Valid  @RequestBody  productDTO sdto) throws Exception{
		try {
			
			String sellerid = productService.addproduct(sdto);
			String successMessage = environment.getProperty("PRODUCT.INSERT_SUCCESS") + sellerid ;
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
			
			}
		catch(Exception exception)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}	
	}
	
	
	@GetMapping(value = "/name/{productName}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<productDTO>> findProductByName(@PathVariable String productName) throws Exception{
		try {
			logger.info("Profile request for customer {}", productName);
			List<productDTO> cust= productService.findProductByName(productName);
			return new ResponseEntity<>(cust,HttpStatus.OK);
		} 
		catch (Exception exception) {
			String message="no products available with this name";
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, message, exception);
		}
	}
	
	
	@DeleteMapping(value = "/seller/{sellerId}")
	public ResponseEntity<String> deleteProductbysellerId(@PathVariable String sellerId) throws Exception {
		try 
		{
		String message = productService.deleteProductbysellerId(sellerId);
		
		return new ResponseEntity<>(message, HttpStatus.OK);
		}	
		catch(Exception exception)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
		}
}
	
	
	
	@DeleteMapping(value = "/{prodId}/delete")
	public ResponseEntity<String> deleteProduct(@PathVariable String prodId) throws Exception {
		try 
		{
		productService.deleteProduct(prodId);
		String successMessage = environment.getProperty("PRODUCT.DELETE_SUCCESS")+prodId;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		}	
		catch(Exception exception)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
		}
}
	@GetMapping(value = "/category/{category}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<productDTO>> findproductbycategory(@PathVariable String category) throws Exception{
		try 
		{
			List<productDTO> cust= productService.findproductbyCategory(category);
			return new ResponseEntity<>(cust,HttpStatus.OK);
		} 
		catch (Exception exception)
		{
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}
	
	@PutMapping(value = "/{prodId}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updatestock(@PathVariable String prodId,@RequestBody Integer quantity ) throws Exception{
		try 
		{
		    productService.updateStock(quantity,prodId);
			String successMessage = environment.getProperty("PRODUCT.UPDATE_SUCCESS") ;
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		}
		catch (Exception exception) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
	
		}
	}
	
	@GetMapping(value = "/{prodId}")
	public ResponseEntity<productDTO> getProduct(@PathVariable String prodId) throws Exception {
		try
		{
		   productDTO p=productService.findProduct(prodId);
		   return new ResponseEntity<>(p, HttpStatus.OK);
		}
		catch(Exception exception)
		{
			System.out.println(environment.getProperty(exception.getMessage()));
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(exception.getMessage()),exception);
		}
	}
	
	@GetMapping(value = "/{category}/{subcategory}")
	public ResponseEntity< List<productDTO>> getProductbySubCategory(@PathVariable String category,@PathVariable String subcategory) throws Exception {
		try
		{
		   List<productDTO> p=productService.findproductbySubCategory(category,subcategory);
		   return new ResponseEntity<>(p, HttpStatus.OK);
		}
		catch(Exception exception)
		{
			System.out.println(environment.getProperty(exception.getMessage()));
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(exception.getMessage()),exception);
		}
		
	}
		@GetMapping(value = "/{sellerId}/{prodId}/check")
		public ResponseEntity<String> verifyproductbysellerId(@PathVariable String sellerId,@PathVariable String prodId) throws Exception {
			try
			{	
			   productService.verifyproductbysellerId(sellerId,prodId);
			  
			   String result="correct ";
			   return new ResponseEntity<>(result, HttpStatus.OK);
			}
			catch(Exception exception)
			{
				
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage(),exception);
			}

	}
		@PostMapping(value = "/subscribe",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> ProductSubsribeOrUpdate( @RequestBody subscribedproductDTO sdto) throws Exception{
			try {
				System.out.println(sdto);
				String message=productService.subscribe(sdto);
				
				return new ResponseEntity<>(message, HttpStatus.CREATED);
				
				}
			catch(Exception exception)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage(), exception);
			}	
		}
		@GetMapping(value = "/subscribe/{buyerId}")
		public ResponseEntity<List<subscribedproductDTO>> listsubscribed(@PathVariable String buyerId) throws Exception {
			try
			{	
				List<subscribedproductDTO> list=  productService.listallsubscried(buyerId);
			  
				return new ResponseEntity<>(list, HttpStatus.OK);
			}
			catch(Exception exception)
			{
				
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage(),exception);
			}

	}
	
	
}
