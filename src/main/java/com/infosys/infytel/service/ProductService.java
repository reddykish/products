package com.infosys.infytel.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.infytel.dto.productDTO;
import com.infosys.infytel.dto.subscribedproductDTO;
import com.infosys.infytel.entity.product;
import com.infosys.infytel.entity.subscribedproduct;
import com.infosys.infytel.repository.productRepository;
import com.infosys.infytel.repository.subproductRepository;
@Service
@Transactional
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	productRepository productRepo;

	@Autowired
	subproductRepository SubproductRepository;
	
	public String deleteProductbysellerId(String sellerId) throws Exception
	{
		int result=productRepo.deleteAllBySellerId(sellerId);
		if(result==0)
		{
			throw new Exception("no product avaialable with this sellerId"+sellerId);
		}
		else
		{
			String message="sucessfully deleted all the reecods in the database"+sellerId;
			return message;
		}
	}
	
	public String  addproduct(productDTO pdto) throws Exception
	{
		product p= productRepo.findTopByOrderByProdIdDesc();
		String productid=p.getProdId();
		String id =productid.substring(1);
        int num = Integer.parseInt(id);
        num=num+1;
         id="P"+num;
		product Product=obgtorecord(pdto);
		Product.setProdId(id);
		productRepo.save(Product);
		return Product.getSellerId();
	}
	
	public List<productDTO> findProductByName(String productname) throws Exception
	{
		Optional<List<product>> optional= productRepo.findAllByProdName(productname);
		List<product> Products = optional.orElseThrow(() -> new Exception("not product prsent with this name"));
		List<productDTO> list=new ArrayList<>();
		for(product Product: Products)
		{
		productDTO ProductDTO=recordtoobj(Product);
		list.add(ProductDTO);
		}
		if(list.isEmpty())
		{
			throw new Exception("\"not product prsent with this name");
		}
		return list;
	}
	
	public void deleteProduct(String ProductId) throws Exception
	{
		Optional<product> optional= productRepo.findById(ProductId);
		product Product =optional.orElseThrow(() -> new Exception("PRODUCT_NOT_FOUND"));
		productRepo.delete(Product);
	}
	
	public void updateStock(Integer quantity,String ProductId) throws Exception {
		Optional<product> optional= productRepo.findById(ProductId);
		product Product =optional.orElseThrow(() -> new Exception("PRODUCT_NOT_FOUND"));
		Product.setStock(quantity);
	}
	
	public List<productDTO> findproductbyCategory(String category) throws Exception {
		
		Optional<List<product>> optional = productRepo.findAllByCategory(category);
		List<product> Products =optional.orElseThrow(() -> new Exception("CATEGORY_NOT_FOUND"));
		List<productDTO> ProductDTOList=new ArrayList<productDTO>();
		for(product Product : Products)
		{
			productDTO ProductDTO=recordtoobj(Product);
			ProductDTOList.add(ProductDTO);
		}
		if(ProductDTOList.isEmpty())
		{
			throw new Exception("CATEGORY_NOT_FOUND");
		}
		return ProductDTOList;
	}
		public List<productDTO> findproductbySubCategory(String category,String Subcategory) throws Exception {
			Optional<List<product>> optional = productRepo.findAllByCategoryAndSubCateg(category,Subcategory);
				List<product> Products =optional.orElseThrow(() -> new Exception("SubCATEGORY_NOT_FOUND"));
				List<productDTO> ProductDTOList=new ArrayList<productDTO>();
		
		for(product Product : Products)
		{
			productDTO ProductDTO=recordtoobj(Product);
			ProductDTOList.add(ProductDTO);
		}
		if(ProductDTOList.isEmpty())
		{
			throw new Exception("CATEGORY_NOT_FOUND");
		}
		return ProductDTOList;
	}
	
	
	
	public productDTO findProduct(String ProductId) throws Exception {
		
		Optional<product> optional= productRepo.findByProdId(ProductId);
		product Product =optional.orElseThrow(() -> new Exception("PRODUCT_NOT_FOUND"));
		productDTO ProductDTO=recordtoobj(Product);
		return ProductDTO;
		
	}
	public static product obgtorecord(productDTO ProductDTO)
	{
		product P=new product();
	
		P.setProdId(ProductDTO.getProdId());
		P.setCategory(ProductDTO.getCategory());
		P.setDescription(ProductDTO.getDescription());
		P.setImage(ProductDTO.getImage());
		P.setPrice(ProductDTO.getPrice());
		P.setProdName(ProductDTO.getProdName());
		P.setProdRating(ProductDTO.getProdRating());
		P.setSubCateg(ProductDTO.getSubCateg());
		P.setStock(ProductDTO.getStock());
		P.setSellerId(ProductDTO.getSellerId());
		return P;
		
	}
	public static productDTO recordtoobj(product Product)
	{
		productDTO ProductDTO=new productDTO();
		ProductDTO.setProdId(Product.getProdId());
		ProductDTO.setCategory(Product.getCategory());
		ProductDTO.setDescription(Product.getDescription());
		ProductDTO.setImage(Product.getImage());
		ProductDTO.setPrice(Product.getPrice());
		ProductDTO.setProdName(Product.getProdName());
		ProductDTO.setProdRating(Product.getProdRating());
		ProductDTO.setSubCateg(Product.getSubCateg());
		ProductDTO.setStock(Product.getStock());
		ProductDTO.setSellerId(Product.getSellerId());
		return ProductDTO;
		
	}
	
	public void verifyproductbysellerId(String sellerId,String prodId) throws Exception
	{
		
		Optional<product> p =productRepo.findBySellerIdAndProdId(sellerId,prodId);
		if(p.isEmpty()) {
			throw new Exception("this product not belongs to this seller");
		}
	
	}
	
	public String  subscribe(subscribedproductDTO SPDTO) throws Exception
	{
		Optional<product> product2=productRepo.findByProdId(SPDTO.getProdId());
		if(product2.isEmpty())
			throw new Exception("no product available withthisproducId");
		subscribedproduct sp=SubproductRepository.findByBuyerIdAndProdId(SPDTO.getBuyerId(),SPDTO.getProdId());
		if(sp==null)
		{	subscribedproduct sp1=new subscribedproduct();
			sp1.setBuyerId(SPDTO.getBuyerId());
			sp1.setProdId(SPDTO.getProdId());
			sp1.setQuantity(SPDTO.getQuantity());
			SubproductRepository.save(sp1);
			
			return "Successfully added the product with productId"+SPDTO.getProdId();
		}
		else
		{
		sp.setQuantity(SPDTO.getQuantity());
		return "Successfully updated the product with productId"+SPDTO.getProdId();
		}
		
	}
	public List<subscribedproductDTO> listallsubscried(String buyerId) throws Exception
	{
		List<subscribedproductDTO> list =new ArrayList<>();
		List<subscribedproduct> sp=SubproductRepository.findAllByBuyerId(buyerId);
		if(sp.isEmpty())
		{
			throw new Exception("no products atre subscribed");
		}
		else
		{
			for(subscribedproduct record:sp)
			{
				subscribedproductDTO sdto=SubProdEntityToDTO(record);
				list.add(sdto);
			}
		}
		return list;
	}
	public  subscribedproductDTO  SubProdEntityToDTO( subscribedproduct record)
	{
		subscribedproductDTO sdto=new subscribedproductDTO();
		sdto.setBuyerId(record.getBuyerId());
		sdto.setProdId(record.getProdId());
		sdto.setQuantity(record.getQuantity());
		return sdto;
	}
	
	
}
